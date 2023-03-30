package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;

@Path("/hello")
public class GreetingResource {


    private ValueCommands<String, Long> countCommands; 

    public GreetingResource(RedisDataSource ds, ReactiveRedisDataSource reactive) { 
        countCommands = ds.value(Long.class); 
        // keyCommands = reactive.key();  

    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("ok")
    public String hellook() {
        return "OK";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        set("chave1", 2L);
        // get("chave1");
        increment("chave1", 2L);
        // long v = get("chave1");
        
        return "Hello from RESTEasy Reactive ";
    }

    long get(String key) {
        Long value = countCommands.get(key); 
        if (value == null) {
            return 0L;
        }
        return value;
    }

    void set(String key, Long value) {
        countCommands.set(key, value); 
    }

    void increment(String key, Long incrementBy) {
        countCommands.incrby(key, incrementBy); 
    }
}