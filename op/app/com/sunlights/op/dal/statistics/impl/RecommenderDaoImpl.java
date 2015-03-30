package com.sunlights.op.dal.statistics.impl;

import com.google.common.collect.Maps;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.dal.PageDao;
import com.sunlights.common.dal.impl.PageDaoImpl;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.statistics.RecommenderDao;
import com.sunlights.op.vo.statistics.CustomerInOutSummaryVo;
import com.sunlights.op.vo.statistics.PurchaseInfoVo;
import com.sunlights.op.vo.statistics.RecommenderInfoVo;
import play.Logger;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/1/14.
 */
public class RecommenderDaoImpl extends EntityBaseDao implements RecommenderDao {

    private PageDao pageDao = new PageDaoImpl();


    @Override
    public List<PurchaseInfoVo> getCustomerPurchaseInfos(PageVo pageVo) {
        StringBuilder sb = new StringBuilder();
        String keys = "customerId,realName,recommendPhone,cbMobile, mobile, cbRealName,tradeNo,tradeType ";
        String columns = " c.customer_id, c1.real_name as tRealName, c.recommend_phone,c.mobile as cbMobile,c1.mobile,c.real_name, t.trade_no, t.type ";
        sb.append("select ").append(columns)
                .append(" from c_customer c left join c_customer c1 on c.recommend_phone=c1.mobile left join t_trade t on c.customer_id = t.cust_id ");

        sb.append(" where 1 = 1 ");
        sb.append(" and trade_no is null ");
        sb.append("  /~and c.create_time >= {beginTime}~/ ");
        sb.append("  /~and c.create_time < {endTime}~/ ");
        sb.append("  /~and c1.real_name like {recommender}~/ ");
        sb.append("  /~or c1.mobile like {recommender}~/ ");

        List<Object[]> resultRows = pageDao.findNativeXsqlBy(sb.toString(), pageVo);
        List<PurchaseInfoVo> purchaseInfoVos = ConverterUtil.convert(keys, resultRows, PurchaseInfoVo.class);

        Logger.debug("purchaseInfoVos = " + purchaseInfoVos.size());

        return purchaseInfoVos;
    }

    @Override
    public List<PurchaseInfoVo> getCustomerPurchaseItems(PageVo pageVo) {
        StringBuilder selectSql = new StringBuilder();
        selectSql.append("from (");
        selectSql.append(" select c.customer_id, c.real_name, c.recommend_phone,c.mobile as cbMobile, c1.mobile, ");
        selectSql.append(" c1.real_name as tRealName, t.trade_no, t.type, t.create_time,t.trade_amount,t.bank_name,t.bank_Card_No, ");
        selectSql.append(" row_number() over (partition by c.customer_id order by t.create_time) as num, ");
        selectSql.append(" sum(t.trade_amount) over (partition by c.customer_id order by t.create_time desc) as totalAmount ");
        selectSql.append(" from c_customer c left join c_customer c1 on c.recommend_phone=c1.mobile left join t_trade t on c.customer_id = t.cust_id ");
        selectSql.append(" where trade_no is not null and type = 'FP.TRADE.TYPE.1'");
        selectSql.append(") a");

        StringBuilder condition = new StringBuilder();
        condition.append(" where a.num = 1 ");
        condition.append("  /~and a.create_time >= {beginTime}~/ ");
        condition.append("  /~and a.create_time < {endTime}~/ ");
        condition.append("  /~and a.real_name like {recommender}~/ ");
        condition.append("  /~or a.mobile like {recommender}~/ ");

        StringBuilder sb = new StringBuilder();
        String keys = "customerId,cbRealName,recommendPhone,cbMobile, mobile, realName,tradeNo,tradeType,createTime,tradeAmt,bankName,cardNo,totalAmt ";
        String columns = " customer_id,real_name, recommend_phone,cbMobile, mobile, tRealName, trade_no, type, create_time ,trade_amount,bank_name,bank_Card_No,totalAmount ";
        sb.append("select ").append(columns)
                .append(selectSql);
        sb.append(condition);


        StringBuilder countSb = new StringBuilder();
        countSb.append("select count(*) ")
                .append(selectSql);
        countSb.append(condition);

        Query countNativeQuery = createNativeQueryByMap(countSb.toString(), pageVo.getFilter());
        Query nativeQuery = createNativeQueryByMap(sb.toString(), pageVo.getFilter());
        int count = Integer.valueOf(String.valueOf(countNativeQuery.getResultList().get(0)));
        int first = pageVo.getIndex();
        int pageSize = pageVo.getPageSize();
        if (first > 0) {
            nativeQuery.setFirstResult(first);
        }
        if (pageSize > 0) {
            nativeQuery.setMaxResults(pageSize);
        }
        List list = nativeQuery.getResultList();
        pageVo.setCount(count);

        //List<Object[]> resultRows = pageDao.findNativeXsqlBy(sb.toString(), pageVo);
        List<PurchaseInfoVo> purchaseInfoVos = ConverterUtil.convert(keys, list, PurchaseInfoVo.class);

        Logger.debug("purchaseInfoVos = " + purchaseInfoVos.size());
        return purchaseInfoVos;
    }

    @Override
    public List<CustomerInOutSummaryVo> CustomerInOutSummaryVos(PageVo pageVo) {
        StringBuilder sb = new StringBuilder();
        String keys = "eventTime,cumulativeInCustomer,totalCumulativeInCash,cumulativeOutCustomer,totalCumulativeOutCash," +
                "dayAddCustomer,dayAddInCash,dayAddOutCash,registeredCustomer ";
        //SQL比较复杂，后面用with cite改造
        sb.append("select s.dt as eventTime ,sr.cumulativeInCustomer ,sum (s.inCash)  over (order by  s.dt) as totalCumulativeInCash,sr.cumulativeOutCustomer ,sum (s.outCash)  over (order by  s.dt) as totalCumulativeOutCash,sr.dayAddCustomer ,s.inCash as dayAddInCash, s.outCash as dayAddOutCash,s2.registeredCustomer from " +
                "(select date(t.create_time) dt,sum(case when t.type = 'FP.TRADE.TYPE.2' then 1 else 0 end) as outperson,sum(case when t.type = 'FP.TRADE.TYPE.2' then t.trade_amount else 0 end ) as outCash,sum(case when t.type = 'FP.TRADE.TYPE.1' then t.trade_amount else 0 end) as inCash from t_trade t  group by date(t.create_time) order by date(t.create_time) desc) s " +
                "left join ( select sl.dt, sum(sl.dayAddCustomer) over(order by sl.dt) as cumulativeInCustomer ,sum(sl.cumulativeOutCustomer) over(order by sl.dt) as cumulativeOutCustomer ,sl.dayAddCustomer from (select dt,sum(inperson) as dayAddCustomer ,-sum(outperson) as cumulativeOutCustomer from( " +
                "select * from (select m.cust_id, row_number() over (partition by m.cust_id,m.outperson,m.inperson order by m.dt) as num,m.dt,m.outperson,m.inperson from (select distinct h.cust_id,h.dt,h.outperson,h.inperson from (select  f.cust_id, date(f.create_time) dt,case when f.type = 'FP.TRADE.TYPE.2' then -1 else 0 end as outperson,case when f.type = 'FP.TRADE.TYPE.1' then 1 else 0 end as inperson " +
                "from t_trade f order by date(f.create_time) desc)h order by h.dt desc)m)n where n.num =1 order by n.dt desc)pc group by dt order by dt desc)sl order by sl.dt ) sr on s.dt=sr.dt left join (select l2.dateTime, " +
                " sum (l2.dayadd)  over (order by  l2.dateTime) as registeredCustomer from (select date(c.create_time) as dateTime, count(c.*) as dayadd from c_customer c group by date(c.create_time) order by date(c.create_time) desc)l2)s2 on s.dt=s2.dateTime order by s.dt desc");
        Map<String, Object> filterMap = Maps.newHashMapWithExpectedSize(5);

        List<Object[]> resultRows = createNativeQueryByMap(sb.toString(), filterMap).getResultList();
        List<CustomerInOutSummaryVo> customerInOutSummaryVos = ConverterUtil.convert(keys, resultRows, CustomerInOutSummaryVo.class);
        Logger.debug("recommenderInfoVos = " + customerInOutSummaryVos.size());
        if (customerInOutSummaryVos != null) {
            for (CustomerInOutSummaryVo c : customerInOutSummaryVos) {
                process(c, customerInOutSummaryVos);
                processCumulativeInCustomer(c,customerInOutSummaryVos);
                processCumulativeOutCustomer(c,customerInOutSummaryVos);

            }
        }

        return customerInOutSummaryVos;

    }

    private int processCumulativeInCustomer(CustomerInOutSummaryVo customerInOutSummaryVo, List<CustomerInOutSummaryVo> customerInOutSummaryVos) {
        if (customerInOutSummaryVo.getCumulativeInCustomer() == null || customerInOutSummaryVo.getCumulativeInCustomer() <= 0) {
            int index = customerInOutSummaryVos.indexOf(customerInOutSummaryVo);
            if (index == customerInOutSummaryVos.size() - 1) {
                customerInOutSummaryVo.setCumulativeInCustomer(1);
            } else {
                CustomerInOutSummaryVo next = customerInOutSummaryVos.get(++index);
                if (next.getCumulativeInCustomer() == null || next.getCumulativeInCustomer().intValue() <= 0) {
                    customerInOutSummaryVo.setCumulativeInCustomer(this.processCumulativeInCustomer(next, customerInOutSummaryVos));
                }
                customerInOutSummaryVo.setCumulativeInCustomer(next.getCumulativeInCustomer());
                return next.getCumulativeInCustomer();

            }
        }
        return customerInOutSummaryVo.getCumulativeInCustomer();
    }
    private int processCumulativeOutCustomer(CustomerInOutSummaryVo customerInOutSummaryVo, List<CustomerInOutSummaryVo> customerInOutSummaryVos) {
        if (customerInOutSummaryVo.getCumulativeOutCustomer() == null || customerInOutSummaryVo.getCumulativeOutCustomer() <= 0) {
        int index = customerInOutSummaryVos.indexOf(customerInOutSummaryVo);
            if (index == customerInOutSummaryVos.size() - 1) {
                customerInOutSummaryVo.setCumulativeOutCustomer(1);
            } else {

                CustomerInOutSummaryVo next = customerInOutSummaryVos.get(++index);
                if (next.getCumulativeOutCustomer() == null || next.getCumulativeOutCustomer().intValue() <= 0) {
                    customerInOutSummaryVo.setCumulativeOutCustomer(this.processCumulativeOutCustomer(next, customerInOutSummaryVos));
                }
                customerInOutSummaryVo.setCumulativeOutCustomer(next.getCumulativeOutCustomer());
                return next.getCumulativeOutCustomer();

            }
        }
        return customerInOutSummaryVo.getCumulativeOutCustomer();
    }
    /**
     * 递归把 RegisteredCustomer 属性值改为实际值
     * @param customerInOutSummaryVo
     * @param customerInOutSummaryVos
     * @return
     */
    private int process(CustomerInOutSummaryVo customerInOutSummaryVo, List<CustomerInOutSummaryVo> customerInOutSummaryVos) {
        int index = customerInOutSummaryVos.indexOf(customerInOutSummaryVo);

            if (index == customerInOutSummaryVos.size() - 1) {
                customerInOutSummaryVo.setRegisteredCustomer(1);
            } else {
                if (customerInOutSummaryVo.getRegisteredCustomer() == null || customerInOutSummaryVo.getRegisteredCustomer() <= 0) {
                    CustomerInOutSummaryVo next = customerInOutSummaryVos.get(++index);
                if (next.getRegisteredCustomer() == null || next.getRegisteredCustomer().intValue() <= 0) {
                    customerInOutSummaryVo.setRegisteredCustomer(this.process(next, customerInOutSummaryVos));
                }
                customerInOutSummaryVo.setRegisteredCustomer(next.getRegisteredCustomer());
                return next.getRegisteredCustomer();

            }
        }
        return customerInOutSummaryVo.getRegisteredCustomer();
    }


    @Override
    public List<RecommenderInfoVo> getRecommenderInfo() {
        StringBuilder sb = new StringBuilder();
        String keys = "recommenderNo,realName,recommondPhone,count ";
        String columns = " customer_id, real_name, mobile, countP ";
        sb.append("select ").append(columns)
                .append("from c_customer c join (select recommend_phone, count(*) countP from c_customer where recommend_phone is not null group by recommend_phone) a on  c.mobile = a.recommend_phone ");
        sb.append(" union select '-1000000', '没有推荐人', '', count(*) countP from c_customer where recommend_phone is null ");
        Map<String, Object> filterMap = Maps.newHashMapWithExpectedSize(5);

        List<Object[]> resultRows = createNativeQueryByMap(sb.toString(), filterMap).getResultList();
        List<RecommenderInfoVo> recommenderInfoVos = ConverterUtil.convert(keys, resultRows, RecommenderInfoVo.class);

        Logger.debug("recommenderInfoVos = " + recommenderInfoVos.size());
        return recommenderInfoVos;
    }
}
