package models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2015/3/26.
 */
@Entity
@Table(name = "f_show_statistics")
public class ShowStatistics extends IdEntity {

    @Column(name = "stat_count")
    private Long statCount;
    @Column(name = "product_Code")
    private String productCode;
    @Column(name = "stat_type")
    private String statType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;


    public static enum StatType {
        REGISTE_STAT("0","注册"),
        TRADE_STAT("1","成交");

        private String type;
        private String desc;

        private StatType(String type, String desc) {
            this.type = type;
            this.desc = desc;
        }

        public String getType() {
            return type;
        }

        public String getDesc() {
            return desc;
        }

        public static String getDescByStatus(String status) {
            if(status == null) {
                return null;
            }
            for(StatType temp : StatType.values()) {
                if(status.equals(temp.getType())) {
                    return temp.getDesc();
                }
            }
            return null;
        }
    }

    public Long getStatCount() {
        return statCount;
    }

    public void setStatCount(Long statCount) {
        this.statCount = statCount;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getStatType() {
        return statType;
    }

    public void setStatType(String statType) {
        this.statType = statType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
