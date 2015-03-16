package com.sunlights.core.service.impl;

import com.sunlights.common.AppConst;
import com.sunlights.core.service.AppVersionService;
import org.junit.Test;
import play.db.jpa.JPA;
import play.libs.F;
import play.test.WithApplication;

import static org.junit.Assert.assertNotNull;

public class AppVersionServiceImplTest extends WithApplication  {

    @Test
    public void testGetLatestVersionFromAppStore() throws Exception {

        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                AppVersionService appVersionService = AppVersionServiceImpl.getInstance();
                String version = appVersionService.getLatestVersionFromAppStore(AppConst.PLATFORM_IOS);
                assertNotNull(version);

            }
        });


    }
}
