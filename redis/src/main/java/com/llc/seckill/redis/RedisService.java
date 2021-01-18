package com.llc.seckill.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;

@Service
public class RedisService {

    private final JedisPool jedisPool;

    @Autowired
    public RedisService(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /***
     * Deduct value by 1 in a single transaction
     * @param key
     * @return whether deduction success or not
     */
    public boolean deductValueByOne(String key)  {
        try (Jedis jedisClient = jedisPool.getResource()) {
            String script = "if redis.call('exists',KEYS[1]) == 1 then\n" +
                    " local value = tonumber(redis.call('get', KEYS[1]))\n" +
                    " if( value <=0 ) then\n" +
                    " return -1\n" +
                    " end;\n" +
                    " redis.call('decr',KEYS[1]);\n" +
                    " return value - 1;\n" +
                    " end;\n" +
                    " return -1;";
            Long value = (Long) jedisClient.eval(script,
                    Collections.singletonList(key), Collections.emptyList());

            return value >= 0;
        }
        catch (Throwable throwable) {
            return false;
        }
    }

    public void setValue(String key, Long value) {
        Jedis jedisClient = jedisPool.getResource();
        jedisClient.set(key, value.toString());
        jedisClient.close();
    }

    public String getValue(String key) {
        Jedis jedisClient = jedisPool.getResource();
        String value = jedisClient.get(key);
        jedisClient.close();
        return value;
    }
}
