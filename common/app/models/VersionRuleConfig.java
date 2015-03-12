package models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: AcctChangFlow.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */

public class VersionRuleConfig {

    private String minSupportVersion;

    private String maxSupportVersion;

    private String channel;



    public String getMinSupportVersion() {
        return minSupportVersion;
    }

    public void setMinSupportVersion(String minSupportVersion) {
        this.minSupportVersion = minSupportVersion;
    }

    public String getMaxSupportVersion() {
        return maxSupportVersion;
    }

    public void setMaxSupportVersion(String maxSupportVersion) {
        this.maxSupportVersion = maxSupportVersion;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }


}
