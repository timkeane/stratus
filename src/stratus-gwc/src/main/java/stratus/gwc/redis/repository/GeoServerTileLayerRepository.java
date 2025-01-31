/* (c) Planet Labs Inc. - all rights reserved
 * This code is licensed under the GPL 2.0 license, available at the root
 * application directory.
 */
package stratus.gwc.redis.repository;

import stratus.gwc.redis.data.GeoServerTileLayerRedisInfoImpl;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * SpringData repository for TileLayer objects
 *
 * @see GeoServerTileLayerRedisInfoImpl
 * @author smithkm
 */
public interface GeoServerTileLayerRepository extends PagingAndSortingRepository<GeoServerTileLayerRedisInfoImpl, String> {
    GeoServerTileLayerRedisInfoImpl findByName(String name);
}
