/* (c) Planet Labs Inc. - all rights reserved
 * This code is licensed under the GPL 2.0 license, available at the root
 * application directory.
 */
package org.geoserver.wfs;

import stratus.redis.config.GeoServerWithEmbeddedRedisConfig;
import stratus.redis.config.SimpleImportResourcesConfig;
import stratus.wfs.redis.geoserver.info.WFSInfoClassRegisteringBean;
import stratus.redis.index.CacheProperties;
import stratus.redis.index.RedisLayerIndexFacade;
import stratus.redis.repository.RedisRepositoryImpl;
import stratus.wfs.StratusWfsTestSupport;
import stratus.wfs.WFSConfig;
import org.geoserver.data.test.SystemTestData;
import stratus.ows.OWSCachingCallback;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GeoServerWithEmbeddedRedisConfig.class, RedisRepositoryImpl.class, WFSConfig.class,
        RedisLayerIndexFacade.class, CacheProperties.class, OWSCachingCallback.class, StratusWfsTestSupport.class,
        SimpleImportResourcesConfig.class, WFSInfoClassRegisteringBean.class},
        properties = {"spring.main.allow-bean-definition-overriding=true"})
public class RedisResourceAccessManagerWFSTest extends ResourceAccessManagerWFSTest {

    @Autowired
    private StratusWfsTestSupport testSupport;

    @Override
    protected void onSetUp(SystemTestData testData) throws Exception {
        testSupport.configureWfsTestPreSetup(applicationContext, getCatalog());
        super.onSetUp(testData);
        testSupport.configureWfsTestPostSetup(applicationContext, getCatalog());
    }
}
