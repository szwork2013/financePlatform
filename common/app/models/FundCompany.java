package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "P_FUND_COMPANY")
public class FundCompany extends IdEntity {

    @Column(name = "COMPANY_NAME", length = 50)
    private String companyName;//公司名称

    @Column(name = "COMPANY_CODE", length = 20)
    private String companyCode;//公司代码

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
