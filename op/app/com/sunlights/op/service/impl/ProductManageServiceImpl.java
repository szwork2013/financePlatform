package com.sunlights.op.service.impl;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.service.CommonService;
import com.sunlights.common.service.PageService;
import com.sunlights.common.vo.DictVo;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.core.service.ProductService;
import com.sunlights.core.service.impl.ProductServiceImpl;
import com.sunlights.crawler.service.FundProfitHistoryService;
import com.sunlights.crawler.service.impl.FundProfitHistoryServiceImpl;
import com.sunlights.op.dal.ProductManageDao;
import com.sunlights.op.dal.impl.ProductManageDaoImpl;
import com.sunlights.op.service.ProductManageService;
import com.sunlights.op.vo.*;
import models.ProductManage;
import org.apache.commons.lang3.StringUtils;
import play.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2014/10/30.
 */
public class ProductManageServiceImpl implements ProductManageService {

	private EntityBaseDao entityBaseDao = new EntityBaseDao();

    private PageService pageService = new PageService();

    private ProductManageDao productManageDao = new ProductManageDaoImpl();

    private ProductService productService = new ProductServiceImpl();

    private CommonService commonService = new CommonService();


    //==========================================Yuan==========================================//

    @Override
    public List<ProductManageVo> findProductManagesBy(PageVo pageVo) {
        List<ProductManageVo> productManageVos = new ArrayList<ProductManageVo>();
        String columns = " pm.id,pm.product_code,pm.product_name,pm.product_type,pm.product_status,pm.begin_date,pm.end_date,pm.temp_stop_date, pm.priority_level,pm.recommend_type,pm.recommend_flag,pm.recommend_desc," +
                "pm.supplier_code, pm.url,pm.is_grab,pm.up_begin_time,pm.down_end_time,pm.product_desc,pm.init_buyed_count,pm.one_month_buyed_count, pm.create_time,pm.update_time, pm.create_by,pm.update_by,f.create_time as f_create_time";
        //24
        StringBuilder xsql = new StringBuilder();
        xsql.append(" select");
        xsql.append(columns).append(" from p_product_manage pm");
        xsql.append(" left join fundnav f on pm.product_code = f.fundcode");
        xsql.append(" where 1=1");
        xsql.append(" /~ and pm.product_code like {product_code} ~/ ");
        xsql.append(" /~ and pm.product_name like {product_name} ~/ ");
        xsql.append(" /~ and pm.product_type = {product_type} ~/ ");
        xsql.append(" order by pm.create_time,f.fundcode");
        List<Object[]> rows = pageService.findNativeXsqlBy(xsql.toString(), pageVo);
        for (Object[] row : rows) {
            productManageVos.add(new ProductManageVo(row));
        }
        return productManageVos;
    }

    @Override
    public void create(ProductManageVo productManageVo) {
        if (hasProductManage(productManageVo)) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.PRODUCT_MANAGE_EXIST_ERROR));
        }
        ProductManage productManage = productManageVo.convertToProductManage();
        productManage.setCreateTime(new Date());
        productManage.setUpdateTime(new Date());
		entityBaseDao.create(productManage);

        grabProfitHistoryByCode(productManage.getProductCode());

        refreshProduct();
    }

    public void refreshProduct() {
        productService.refreshProductIndexCache();
        productService.refreshProductListCache();
    }

    @Override
    public void update(ProductManageVo productManageVo) {
        if (hasProductManage(productManageVo)) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.PRODUCT_MANAGE_EXIST_ERROR));
        }
        ProductManage productManage = productManageVo.convertToProductManage();
        productManage.setUpdateTime(new Date());
		entityBaseDao.update(productManage);

        refreshProduct();
    }

    @Override
    public void delete(ProductManageVo productManageVo) {
        ProductManage productManage = entityBaseDao.find(ProductManage.class, productManageVo.getId());
		entityBaseDao.delete(productManage);

        refreshProduct();
    }

    @Override
    public List<String> findSynchronousProductCodes() {
        StringBuffer jpql = new StringBuffer();
        jpql.append(" select pm.productCode from ProductManage pm");
        jpql.append(" where pm.isGrab = ?1");
        return entityBaseDao.find(jpql.toString(), "Y");
    }

    @Override
    public List<CodeVo> findCodes(PageVo pageVo) {
        StringBuilder xsql = new StringBuilder();
        xsql.append(" select new com.sunlights.op.vo.CodeVo(c) from Code c");
        xsql.append(" where 1=1");
        xsql.append(" /~ and c.category = {category} ~/ ");
        xsql.append(" /~ and c.code like {code} ~/ ");
        xsql.append(" /~ and c.value like {value} ~/ ");
        xsql.append(" order by c.category");
        List<CodeVo> codeVos = pageService.findXsqlBy(xsql.toString(), pageVo);
        return codeVos;
    }

    @Override
    public void grabProfitHistoryByCode(String code) {
        System.out.println("[code]" + code);
        if(StringUtils.isEmpty(code)) return;
        FundProfitHistoryService service = new FundProfitHistoryServiceImpl();
        List codes = new ArrayList();
        codes.add(code);
        service.saveAllFundProfitHistory(codes);
    }

	@Override
	public void grabAllProfitHistory () {
		ProductManageService productManageService = new ProductManageServiceImpl();
		List<String> productCodes = productManageService.findSynchronousProductCodes();
		Logger.info(" begin Grab All ProfitHistory " + new Date());
		Logger.info("[productCodes]" + productCodes.size());
		FundProfitHistoryService service = new FundProfitHistoryServiceImpl();
		service.saveAllFundProfitHistory(productCodes);
		Logger.info(" end Grab All ProfitHistory " + new Date());
	}

	private boolean hasProductManage(ProductManageVo productManageVo) {
        StringBuffer jpql = new StringBuffer();
        jpql.append(" select pm from ProductManage pm");
        jpql.append(" where pm.productCode = '" + productManageVo.getProductCode().trim() + "'");
        jpql.append(" and pm.productType = '" + productManageVo.getProductType().trim() + "'");
        if (productManageVo.getId() != null) {
            jpql.append(" and pm.id <> ").append(productManageVo.getId());
        }
        List<ProductManage> productManages = entityBaseDao.find(jpql.toString());
        return !productManages.isEmpty();
    }

    //==========================================Yuan==========================================//


    @Override
    public List<ProductTypeVo> loadPrdType() {
        return productManageDao.loadPrdTypeWithPrds();
    }

    @Override
    public List<KeyValueVo> loadPrdTypes() {
        List<DictVo> list = commonService.findDictsByCat("FP.PRODUCT.TYPE");
        List<KeyValueVo> result = new ArrayList<KeyValueVo>();
        for (DictVo vo : list) {
            KeyValueVo kv = new KeyValueVo(vo.getCodeKey(), vo.getCodeVal());
            result.add(kv);
        }
        return result;
    }

    @Override
    public List<KeyValueVo> loadProducts(String prdType) {
        List<ProductVo> productVos = productManageDao.loadProducts(prdType);
        List<KeyValueVo> result = new ArrayList<KeyValueVo>();
        for (ProductVo vo : productVos) {
            KeyValueVo kv = new KeyValueVo(vo.getCode(), vo.getName());
            result.add(kv);
        }
        return result;
    }
}
