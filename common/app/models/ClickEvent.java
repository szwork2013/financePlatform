package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by tangweiqun on 2014/11/12.
 */
@Entity
@Table(name = "F_ACTIVITY_EVENT")
public class ClickEvent extends BaseEntity {
    @Column(name = "ACTIVITY_ID")
    private String activityId;
    @Column(name = "CLICK_EVENT_CODE")
    private String code;
    @Column(name = "RESOURCE_ID")
    private String activitySourceId;
    @Column(name = "SORT_NO")
    private Integer sortNo;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getActivitySourceId() {
        return activitySourceId;
    }

    public void setActivitySourceId(String activitySourceId) {
        this.activitySourceId = activitySourceId;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
}
