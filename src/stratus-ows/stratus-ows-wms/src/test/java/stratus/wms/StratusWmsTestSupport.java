/* (c) Planet Labs Inc. - all rights reserved
 * This code is licensed under the GPL 2.0 license, available at the root
 * application directory.
 */
package stratus.wms;

import stratus.redis.RedisFacadeTestSupport;
import stratus.redis.index.LayerIndexListener;
import org.geoserver.catalog.Catalog;
import org.geoserver.ows.StratusOwsTestSupport;
import stratus.ows.OWSCachingCallback;
import org.geoserver.test.GeoServerTestApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StratusWmsTestSupport {

    @Autowired
    private WMSHandler handler;
    @Autowired
    private OWSCachingCallback owsCachingCallback;
    @Autowired
    private LayerIndexListener layerIndexListener;
    @Autowired
    private RedisFacadeTestSupport redisTestSupport;

    public void configureWmsTestPreSetup(GeoServerTestApplicationContext applicationContext, Catalog catalog) {
        catalog.addListener(layerIndexListener);
    }
    public void configureWmsTestPostSetup(GeoServerTestApplicationContext applicationContext, Catalog catalog) throws NoSuchFieldException, IllegalAccessException {
        applicationContext.getBeanFactory().registerSingleton("wmsHandler", handler);
        redisTestSupport.repository.flush();
        redisTestSupport.setCatalogFacade(applicationContext);
        redisTestSupport.setGeoServerFacade(applicationContext);

        StratusOwsTestSupport.configureOwsCachingCallback(applicationContext, owsCachingCallback);
    }
}
