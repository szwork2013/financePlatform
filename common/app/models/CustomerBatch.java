package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Edward.tian on 2015/5/8 0008.
 */
@Entity
@Table(name = "t_customer_batch")
public class CustomerBatch extends IdEntity {

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "customer_total")
    private int customerTotal;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getCustomerTotal() {
        return customerTotal;
    }

    public void setCustomerTotal(int customerTotal) {
        this.customerTotal = customerTotal;
    }
}
