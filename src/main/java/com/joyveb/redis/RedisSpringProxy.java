package com.joyveb.redis;

import javax.annotation.Resource;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @Title: RedisSpringProxy.java
 * @Package com.joyveb.redis
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 杨其桔
 * @date 2013-5-31 上午12:03:03
 * @version V1.0
 */
@Component
public class RedisSpringProxy {

	@Resource
	private StringRedisTemplate redisTemplate;

	public void save(final String key, final Object value) {
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) {
				@SuppressWarnings("unchecked")
				RedisSerializer<Object> redisSerializer = (RedisSerializer<Object>) redisTemplate
						.getValueSerializer();
				connection.set(
						redisTemplate.getStringSerializer().serialize(key),
						redisSerializer.serialize(value));
				return null;
			}
		});
	}
	
	public Object read(final String key){
		return redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection){
				byte[] keyBytes = redisTemplate.getStringSerializer().serialize(key);
				if(connection.exists(keyBytes)){
					byte[] valueBytes = connection.get(keyBytes);
					RedisSerializer<Object> redisSer = (RedisSerializer<Object>) redisTemplate.getValueSerializer();
					return redisSer.deserialize(valueBytes);
				}
				return null;
			}
		});
	}
	
	
}
