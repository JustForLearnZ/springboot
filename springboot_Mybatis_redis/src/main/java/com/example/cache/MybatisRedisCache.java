package com.example.cache;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Resource;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.DependsOn;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.holder.SpringContextHolder;

public class MybatisRedisCache implements Cache{

	
    private static final Logger logger = LoggerFactory.getLogger(MybatisRedisCache.class);
    // 读写锁
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    private RedisTemplate<String, Object> template = SpringContextHolder.getBean("redisTemplate");
    private String id;
    
    public MybatisRedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        logger.info("Redis Cache id " + id);
        this.id = id;
    }
    public String getId() {
    	return this.id;
    }


	@Override
	public void putObject(Object key, Object value) {
		// TODO Auto-generated method stub
		if(value!=null) {
            // 向Redis中添加数据，有效时间是2天
			template.opsForValue().set(key.toString(), value, 2, TimeUnit.DAYS);
			
		}
	}
	@Override
	public Object getObject(Object key) {
		// TODO Auto-generated method stub
		try {
			if(key!=null) {
				Object obj = template.opsForValue().get(key.toString());
				return	obj;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			 logger.error("redis getObject error");
		}
		return null;
	}
	@Override
	public Object removeObject(Object key) {
		// TODO Auto-generated method stub
		try {
            if (key != null) {
            	template.delete(key.toString());
            }
        } catch (Exception e) {
        }
		return null;
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		logger.debug("清空缓存");
        try {
            Set<String> keys = template.keys("*:" + this.id + "*");
            if (!CollectionUtils.isEmpty(keys)) {
            	template.delete(keys);
                
            }
        } catch (Exception e) {
        }

		
	}
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		 Long size = (Long) template.execute(new RedisCallback<Long>() {
	            @Override
	            public Long doInRedis(RedisConnection connection) throws DataAccessException {
	                return connection.dbSize();
	            }
	        });
	        return size.intValue();
	
	}
	@Override
	public ReadWriteLock getReadWriteLock() {
		// TODO Auto-generated method stub
		return this.readWriteLock;
	}

}
