package models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tangweiqun on 2014/11/12.
 */
@Entity
@Table(name = "F_REWARD_TYPE")
public class RewardType extends IdEntity {
    @Column(name = "CODE")
    private String code;
    @Column(name = "NAME")
    private String name;
    @Column(name = "UNIT")
    private Integer unit;
    @Column(name = "RULE_URL")
    private String ruleUrl;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Column(name = "CREATE_BY", length = 30)
    private String createBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name = "UPDATE_BY", length = 30)
    private String updateBy;

    public static enum RewardTypeConstant {
        JINDOU("ART00J","送金豆"),
        REDPACKET("ART00H","送红包");

        private String type;
        private String desc;

        private RewardTypeConstant(String type, String desc) {
            this.type = type;
            this.desc = desc;
        }

        public String getType() {
            return type;
        }

        public String getDesc() {
            return desc;
        }

        public static String getDescByScene(String type) {
            if(type == null) {
                return null;
            }
            for(RewardTypeConstant temp : RewardTypeConstant.values()) {
                if(type.equals(temp.getType())) {
                    return temp.getDesc();
                }
            }
            return null;
        }
    }

    public String getRuleUrl() {
        return ruleUrl;
    }

    public void setRuleUrl(String ruleUrl) {
        this.ruleUrl = ruleUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}
