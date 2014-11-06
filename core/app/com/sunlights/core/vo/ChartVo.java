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
}
