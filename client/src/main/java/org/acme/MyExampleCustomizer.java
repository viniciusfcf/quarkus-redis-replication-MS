package org.acme;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import io.quarkus.redis.client.RedisOptionsCustomizer;
import io.quarkus.redis.runtime.client.config.RedisConfig;
import io.vertx.redis.client.RedisClientType;
import io.vertx.redis.client.RedisOptions;

@ApplicationScoped
public class MyExampleCustomizer implements RedisOptionsCustomizer {

    @Inject
    Logger log;

    @Override
    public void customize(String clientName, RedisOptions options) {
        log.info("VAI");
        options.setType(RedisClientType.REPLICATION);
        log.infof("VAI %s", options);
        if (clientName.equalsIgnoreCase("my-redis")
                || clientName.equalsIgnoreCase(RedisConfig.DEFAULT_CLIENT_NAME)) {
            // modify the given options
        } else {
            throw new IllegalStateException("Unknown client name: " + clientName);
        }
    }
}
