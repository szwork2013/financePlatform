package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Edward.tian on 2015/5/7 0007.
 */
@Entity
@Table(name="t_sync_batch_log")
public class SyncBatchLog extends IdEntity{

    @Column(name="task_name")
    private String taskName;

    @Column(name="start_time")
    private Date startDate;

    @Column(name="end_time")
    private Date endDate;

    @Column(name="task_status")
    private String taskStatus;

    @Column(name="error_msg")
    private String errorMsg;

    @Column(name="create_time")
    private Date createTime;

    @Column(name="update_time")
    private Date updateTime;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
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