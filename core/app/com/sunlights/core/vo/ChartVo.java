package com.sunlights.core.vo;


import java.util.ArrayList;
import java.util.List;

/**
 * <p>Project: financePlatform</p>
 * <p>Title: ChartVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class ChartVo {

//    prdName:"工银货币",

    private String prdName;
    //    prdCode:"GYHB",

    private String prdCode;
    //    chartType:"1",

    private String chartType;
    //    chartName:"万份收益走势"
    private String chartName;

    private List<Point> points = new ArrayList<>();

    public String getPrdName() {
        return prdName;
    }

    public void setPrdName(String prdName) {
        this.prdName = prdName;
    }

    public String getPrdCode() {
        return prdCode;
    }

    public void setPrdCode(String prdCode) {
        this.prdCode = prdCode;
    }

    public String getChartType() {
        return chartType;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
    }

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public enum ChartType {
        MILLON_OF_PROFIT(1, "万份收益走势"),
        ONE_WEEK_PROFIT(2, "七日年化走势");
        private int type;
        private String title;

        ChartType(int type, String title) {
            this.type = type;
            this.title = title;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChartVo)) return false;

        ChartVo chartVo = (ChartVo) o;

        if (chartName != null ? !chartName.equals(chartVo.chartName) : chartVo.chartName != null) return false;
        if (chartType != null ? !chartType.equals(chartVo.chartType) : chartVo.chartType != null) return false;
        //  if (points != null ? !points.equals(chartVo.points) : chartVo.points != null) return false;
        if (prdCode != null ? !prdCode.equals(chartVo.prdCode) : chartVo.prdCode != null) return false;
        if (prdName != null ? !prdName.equals(chartVo.prdName) : chartVo.prdName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = prdName != null ? prdName.hashCode() : 0;
        result = 31 * result + (prdCode != null ? prdCode.hashCode() : 0);
        result = 31 * result + (chartType != null ? chartType.hashCode() : 0);
        result = 31 * result + (chartName != null ? chartName.hashCode() : 0);
        result = 31 * result + (points != null ? points.hashCode() : 0);
        return result;
    }
}
