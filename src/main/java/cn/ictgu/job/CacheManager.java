package cn.ictgu.job;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class CacheManager {

    @Bean
    public Cache init() {
        return CacheBuilder
                .newBuilder()
                .expireAfterWrite(24, TimeUnit.HOURS)
                .concurrencyLevel(10)
                .initialCapacity(1000)
                .build();
    }
}
