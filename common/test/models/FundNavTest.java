package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;
import org.junit.Test;
import play.libs.Json;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class FundNavTest {

    @Test
    public void testScale() {

        Map<String , String> keys = new HashMap<String, String>();
        keys.put("yield1M", "yield_1m");

        String jsonString = "{\n" +
                "    \"guid\": \"97F0E2E2-79BE-4B43-9C21-44BEB38D370D\",\n" +
                "    \"invest_advisor_guid\": \"e21ae253ec2443dfa2315501ba1fdc48\",\n" +
                "    \"name\": \"景顺长城基金管理有限公司\",\n" +
                "    \"abbr_name\": \"景顺长城\",\n" +
                "    \"pin_yin_code\": \"JSCC\",\n" +
                "    \"background\": \"    名  称：景顺长城基金管理有限公司\\r\\n　　住  所：深圳市中心四路1号嘉里建设广场第1座21层\\r\\n　　设立日期：2003年6月12日\\r\\n　　法定代表人：赵如冰\\r\\n　　批准设立文号：证监基金字［2003］76号\\r\\n　　办公地址：深圳市中心四路1号嘉里建设广场第1座21层\\r\\n　　电  话：0755-82370388\\r\\n　　客户服务电话：400 8888 606\\r\\n　　传  真：0755-22381355\\r\\n　　联系人：杨皞阳\\r\\n　　二、基金管理人基本情况\\r\\n　　本基金管理人景顺长城基金管理有限公司（以下简称\\\"公司\\\"或\\\"本公司\\\"）是经中国证监会证监基金字［2003］76号文批准设立的证券投资基金管理公司，由长城证券有限责任公司、景顺资产管理有限公司、开滦（集团）有限责任公司、大连实德集团有限公司共同发起设立，并于2003年6月9日获得开业批文，注册资本1.3亿元人民币，目前，各家出资比例分别为49%、49%、1%、1%。\\r\\n　　公司下设7个一级部门，分别是：投资部、市场部、运营部、人力资源部、财务行政部、法律监察稽核部、总经理办公室。其中投资部下设投资研究部、固定收益部、专户理财部、国际投资部四个二级部门；市场部下设渠道销售部、机构客户部、市场服务部、产品开发部四个二级部门；运营部下设基金事务部、信息技术部、交易管理部三个二级部门。各部门的职责如下：\\r\\n　　1、投资部\\r\\n　　投资研究部：负责根据投资决策委员会制定的投资原则进行国内股票选择和组合的投资管理并完成对宏观经济、行业公司及市场的研究。\\r\\n　　固定收益部：负责根据投资决策委员会制定的投资原则进行国内债券选择和组合的投资管理并完成固定收益的研究。\\r\\n　　专户理财部：负责完成一对多、特定资产客户等的投资管理。\\r\\n　　国际投资部：主要从事与QDII、QFII等国际业务相关的基金产品设计、投资管理、国际合作和培训等业务。\\r\\n　　2、市场部\\r\\n　　渠道销售部：负责公司产品在各银行、券商等渠道的销售。\\r\\n　　机构客户部：负责开发机构客户，并使其认识并了解我公司在企业年金、社保等方面所提供的基金产品及服务。\\r\\n　　市场服务部：负责公司市场营销策略、计划制定，及客户服务管理等工作。\\r\\n　　产品开发部：负责基金产品及其他投资产品的设计、开发、报批等工作。\\r\\n　　3、运营保障部\\r\\n　　基金事务部：负责公司产品的注册登记、清算和估值核算等工作。\\r\\n　　信息技术部：负责公司的计算机设备维护、系统开发及网络运行和维护。\\r\\n　　交易管理部：负责完成投资部下达的交易指令，并进行事前的风险控制。\\r\\n　　4、人力资源部：负责公司各项人力资源管理工作，包括招聘、薪资福利、绩效管理、培训、员工关系、从业资格管理、高管及基金经理资格管理等。\\r\\n　　5、财务行政部：负责公司财务管理及日常行政事务管理。\\r\\n　　6、法律监察稽核部：负责对公司管理和基金运作合规性进行全方位的监察稽核，并向公司管理层和监管机关提供独立、客观、公正的法律监察稽核报告。\\r\\n　　7、总经理办公室：主要受总经理委托，协调各部门的工作，并负责公司日常办公秩序监督、工作项目管理跟进等，并负责基金风险的评估、控制及管理。\\r\\n　　公司现有员工102人，其中61人具有硕士以上学历。所有人员在最近三年内均没有受到所在单位或有关管理部门的处罚。\\r\\n　　公司已经建立了健全的内部风险控制制度、内部稽核制度、财务管理制度、人事管理制度、信息披露制度和员工行为准则等公司管理制度体系。\",\n" +
                "    \"contact_addr\": \"深圳市福田区中心四路1号嘉里建设广场第一座21层\",\n" +
                "    \"direct_sell_url\": \"https://www.invescogreatwall.com/etrading/\",\n" +
                "    \"email\": \"distributor@invescogreatwall.com\",\n" +
                "    \"establishment_date\": \"2003-06-12\",\n" +
                "    \"fax\": \"0755-22381339\",\n" +
                "    \"general_manager\": \"许义明\",\n" +
                "    \"legal_repr\": \"赵如冰\",\n" +
                "    \"link_man\": \"杨皞阳\",\n" +
                "    \"office_addr\": \"深圳市福田区中心四路1号嘉里建设广场第一座21层\",\n" +
                "    \"reg_addr\": \"深圳市福田区中心四路1号嘉里建设广场第一座21层\",\n" +
                "    \"reg_capital\": \"130.0000\",\n" +
                "    \"service_line\": \"4008888606\",\n" +
                "    \"tel\": \"0755-82370388 4008888606\",\n" +
                "    \"web_site\": \"www.invescogreatwall.com\",\n" +
                "    \"zip_code\": \"518048\",\n" +
                "    \"is_fund_company\": \"True\",\n" +
                "    \"yield_1m\": 0.000123456,\n" +
                "    \"updatetime\": \"2014-07-25\"\n" +
                "}\n";

        JsonNode jsonNode = Json.parse(jsonString);

        jsonNode = filter(jsonNode, keys);

        FundNavHistory fundNav = Json.fromJson(jsonNode, FundNavHistory.class);
        System.out.println(fundNav.getYield1M());

    }

    public JsonNode filter(JsonNode jsonNode, Map<String, String> keysMap) {
        Map<String, Object> mapObj = Maps.newHashMap();
        for (String key : keysMap.keySet()) {
            String name = keysMap.get(key);
            JsonNode value = jsonNode.findValue(name);
            mapObj.put(key, value);
        }

        return Json.toJson(mapObj);
    }

}