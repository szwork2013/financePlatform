package models;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Edward.tian on 2015/5/7 0007.
 */

@Table(name="t_sync_batch_log")
public class SyncBatchLog extends IdEntity{

    @Column(name="task_name")
    private String taskName;

    @Column(name="start_time")
    private Date startTime;

    @Column(name="end_time")
    private Date endTime;

    @Column(name="task_status")
    private String taskStatus;

    @Column(name="error_msg")
    private String errorMsg;

    @Column(name="error_msg")
    private Date createTime;

    @Column(name="update_time")
    private Date updateTime;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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