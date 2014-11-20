package com.sunlights.common;

/**
 * ALL 	        显示所有基金
 * OPEN 	    开放式基金（fundtype为2的基金，排除货币、短期理财、QDII、创新型）
 * BOND 	    债券型基金
 * INDEX 	    指数型基金
 * STOCK 	    股票型
 * QDII	        QDII
 * GUARANTEED 	保本型(Guaranteed Captical)
 * INNOVATION 	创新型
 * HYBIRD 	    混合型
 * MONETARY	    货币型(不含短期理财)
 * STF 	        短期理财(不含集合理财)(Short Term Financial Product)
 * CFP 	        集合理财(Collection Financial Product)
 */
public enum FundCategory {
    ALL("","",""),
    OPEN("2", "", ""),
    BOND("2", "6", ""),
    INDEX("2","24", ""),
    STOCK("2", "1", ""),
    QDII("2", "25", ""),
    GUARANTEED("2", "9", ""),
    INNOVATION("6", "", ""),
    HYBIRD("2", "3", ""),
    MONETARY("7", "", ""),
    STF("","", "9910"),
    ETF("","","9901"),
    LOF("", "", "9902"),
    CFP("8","","");

    private String fundType;
    private String investmentType;
    private String feature;

    FundCategory(String fundType, String investmentType, String feature) {
        this.fundType = fundType;
        this.investmentType = investmentType;
        this.feature = feature;
    }

    /**
     * 获取基金类型
     * 1:封闭式基金 2:开放式基金 6:创新型 7:货币基金 8:集合理财
     * @return
     */
    public String getFundType() {
        return fundType;
    }

    /**
     * 获取基金投资类型
     * 1:股票 3:混合 6:债券 9:保本 24:指数 25:QDII
     * @return
     */
    public String getInvestmentType() {
        return investmentType;
    }

    public String getFeature() {
        return feature;
    }
}