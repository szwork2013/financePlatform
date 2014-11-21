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


    ALL(0,0, ""),
    OPEN(2, 0, "开放式基金"),
    BOND(2, 6, "债券型基金"),
    INDEX(2,24, "指数型基金"),
    STOCK(2, 1, "股票型"),
    QDII(2, 25, "QDII"),
    GUARANTEED(2, 9, "保本型"),
    INNOVATION(6, 0, "创新型"),
    HYBIRD(2, 3, "混合型"),
    MONETARY(7, 0, "货币型"),
    LOF(0, FundCategory.LOF_INVESTMENT_TYPE, "短期理财"),
    CFP(8, 0, "集合理财");

    public static final String SPLITER = "|";

    private int fundType;
    private int investmentType;
    private String description;
    public static final int LOF_INVESTMENT_TYPE = 9910;

    FundCategory(int fundType, int investmentType, String description) {
        this.fundType = fundType;
        this.investmentType = investmentType;
        this.description = description;
    }

    /**
     * 获取基金类型
     * 1:封闭式基金 2:开放式基金 6:创新型 7:货币基金 8:集合理财
     * @return
     */
    public int getFundType() {
        return fundType;
    }

    /**
     * 获取基金投资类型
     * 1:股票 3:混合 6:债券 9:保本 24:指数 25:QDII
     * @return
     */
    public int getInvestmentType() {
        return investmentType;
    }

    public String getDescription() {
        return description;
    }

    private String concatValue(){
        return this.fundType+ SPLITER +this.investmentType+ SPLITER;
    }


    /**
     * 根据基金类型，基金投资类型和feature找到对应的category。
     * @param fundType
     * @param investmentType
     * @return
     */
    public static FundCategory from(String fundType, String investmentType){
        if(fundType==null){
            fundType = "";
        }
        if(investmentType==null){
            investmentType = "";
        }



        FundCategory[] categories = FundCategory.values();
        String value = fundType+ SPLITER +investmentType+ SPLITER;
        for (FundCategory category : categories) {
            if(category.concatValue().equals(value)){
                return category;
            }
        }

        throw new IllegalArgumentException("Cannot find fund category for:"+ value);
    }
}