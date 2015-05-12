package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Edward.tian on 2015/5/8 0008.
 */
@Entity
@Table(name="t_cust_batch_detail")
public class CustBatchDetail extends  IdEntity{

    @Column(name="customer_id" )
    private String customerId;

    @Column(name="customer_batch_id")
    private long customerBatchId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public long customerBatchId() {
        return customerBatchId;
    }

    public void setCustomerBatchId(long customerBatchId) {
        this.customerBatchId = customerBatchId;
    }
}
