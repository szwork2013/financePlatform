package models;

import javax.persistence.*;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: AutherTication.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "c_authentication")
@NamedQueries({
        @NamedQuery(name = "findAuthentication", query = "select a from Authentication a where a.mobile = :mobile and a.password = :password")
})
public class Authentication extends BaseEntity {
    @Column(length = 20, name = "mobile")
    private String mobile;
    @Column(length = 50)
    private String password;
    @Column(length = 10)
    private String channel;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
