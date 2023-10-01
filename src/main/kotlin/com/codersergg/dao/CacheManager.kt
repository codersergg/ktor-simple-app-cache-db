package com.codersergg.dao

import org.ehcache.PersistentCacheManager
import org.ehcache.config.builders.CacheConfigurationBuilder
import org.ehcache.config.builders.CacheManagerBuilder
import org.ehcache.config.builders.ResourcePoolsBuilder
import org.ehcache.config.units.EntryUnit
import org.ehcache.config.units.MemoryUnit
import org.ehcache.impl.config.persistence.CacheManagerPersistenceConfiguration
import java.io.File

object CacheManager {
    fun <T> init(storagePath: File, alias: String, clazz: Class<T>): PersistentCacheManager {
        return CacheManagerBuilder.newCacheManagerBuilder()
            .with(CacheManagerPersistenceConfiguration(storagePath))
            .withCache(
                alias,
                CacheConfigurationBuilder.newCacheConfigurationBuilder(
                    Int::class.javaObjectType,
                    clazz,
                    ResourcePoolsBuilder.newResourcePoolsBuilder()
                        .heap(1000, EntryUnit.ENTRIES)
                        .offheap(10, MemoryUnit.MB)
                        .disk(100, MemoryUnit.MB, true)
                )
            )
            .build(true)
    }
}

interface Cacheable