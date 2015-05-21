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

    @Column(name="shumi_tokenkey" )
    private String shumiTokenKey;

    @Column(name="shumi_tokensecret" )
    private String shumiTokenSecret;

    @Column(name="customer_batch_id")
    private long customerBatchId;

    public String getShumiTokenKey() {
        return shumiTokenKey;
    }

    public void setShumiTokenKey(String shumiTokenKey) {
        this.shumiTokenKey = shumiTokenKey;
    }

    public String getShumiTokenSecret() {
        return shumiTokenSecret;
    }

    public void setShumiTokenSecret(String shumiTokenSecret) {
        this.shumiTokenSecret = shumiTokenSecret;
    }

    public long getCustomerBatchId() {
        return customerBatchId;
    }

    public void setCustomerBatchId(long customerBatchId) {
        this.customerBatchId = customerBatchId;
    }
}
