package models;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by tangweiqun on 2014/11/12.
 */
@Entity
@Table(name = "F_activity_report")
public class ActivityReport extends IdEntity {
    @Column(name = "active_report_desc")
    private String reportDesc;
    @Column(name = "active_report_type")
    private String reportType;
    @Column(name = "channel")
    private String channel;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;



    public static enum ActivityReportType {
        REGISTE_TYPE("0","注册"),
        REWARD_TYPE("1","成交");

        private String type;
        private String desc;

        private ActivityReportType(String type, String desc) {
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
            for(ActivityReportType temp : ActivityReportType.values()) {
                if(status.equals(temp.getType())) {
                    return temp.getDesc();
                }
            }
            return null;
        }
    }


    public String getReportDesc() {
        return reportDesc;
    }

    public void setReportDesc(String reportDesc) {
        this.reportDesc = reportDesc;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
