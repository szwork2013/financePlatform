package com.sunlights.core.vo;

/**
 * Created by Administrator on 2014/9/19.
 */
public class IdentifierVo {
  public String ResponseCode;
  public String ResponseText;
  public com.sunlights.core.vo.Identifier Identifier = new com.sunlights.core.vo.Identifier();
  public String RawXml;

  public IdentifierVo() {
  }

  public String getResult() {
    return Identifier.Result;
  }

}

class Identifier {
  public String IDNumber;//身份证号
  public String Name;//姓名
  public String FormerName;// 曾用名
  public String Sex;//  “男性”, “女性”
  public String Nation;// 民族
  public String Birthday;//生日  YYYY-MM-DD
  public String Company;//服务场所（工作单位）
  public String Education;//文化程度  博士/本科等
  public String MaritalStatus;//婚姻状况  已婚/未婚等
  public String NativePlace;//籍贯
  public String BirthPlace;// 出生地
  public String Address;// 住址
  public String Photo;//照片  以 Base64  编
  public String QueryTime;
  public String IsQueryCitizen;
  public String Result = "";

  public Identifier() {

  }

}
