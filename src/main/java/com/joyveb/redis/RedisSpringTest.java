package com.joyveb.redis;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**   
 * @Title: RedisSpringTest.java 
 * @Package com.joyveb.redis 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 杨其桔
 * @date 2013-5-31 上午12:21:03 
 * @version V1.0   
 */
public class RedisSpringTest {

	private final static Logger log = LoggerFactory.getLogger(RedisSpringTest.class);
	ApplicationContext context = null;
	RedisSpringProxy springProxy = null;
	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("application.xml");
		springProxy = context.getBean(RedisSpringProxy.class);
	}

	@Test
	public void save() {
		springProxy.save("key1", "hello");
		log.debug(springProxy.read("key1").toString());
	}

}

