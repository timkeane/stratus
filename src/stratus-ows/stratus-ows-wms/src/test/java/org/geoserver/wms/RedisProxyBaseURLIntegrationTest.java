/* (c) Planet Labs Inc. - all rights reserved
 * This code is licensed under the GPL 2.0 license, available at the root
 * application directory.
 */
package org.geoserver.wms;

import stratus.redis.config.GeoServerWithEmbeddedRedisConfig;
import stratus.redis.config.SimpleImportResourcesConfig;
import stratus.wms.redis.geoserver.info.WMSInfoClassRegisteringBean;
import stratus.redis.index.CacheProperties;
import stratus.redis.index.LayerIndexListener;
import stratus.redis.index.RedisLayerIndexFacade;
import stratus.redis.repository.RedisRepositoryImpl;
import stratus.wms.StratusWmsTestSupport;
import stratus.wms.WMSConfig;
import org.geoserver.data.test.SystemTestData;
import stratus.ows.OWSCachingCallback;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GeoServerWithEmbeddedRedisConfig.class, RedisRepositoryImpl.class,
        WMSConfig.class, RedisLayerIndexFacade.class, LayerIndexListener.class,
        CacheProperties.class, OWSCachingCallback.class, StratusWmsTestSupport.class,
        SimpleImportResourcesConfig.class, WMSInfoClassRegisteringBean.class},
        properties = {"spring.main.allow-bean-definition-overriding=true"})
public class RedisProxyBaseURLIntegrationTest extends ProxyBaseURLIntegrationTest {

    @Autowired
    private StratusWmsTestSupport testSupport;

    @Override
    protected void onSetUp(SystemTestData testData) throws Exception {
        testSupport.configureWmsTestPreSetup(applicationContext, getCatalog());
        super.onSetUp(testData);
        testSupport.configureWmsTestPostSetup(applicationContext, getCatalog());
    }
}
