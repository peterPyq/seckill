package com.seckill.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seckill.dto.Exposer;
import com.seckill.entity.Seckill;

/**
 * @describe SeckillDao单元测试类 配置spring和junit整合，junt启动时加载springIOC容器
 * @author pyq 2017年11月2日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application-dao.xml" })
public class SeckillDaoTest {

	@Resource
	private SeckillDao seckillDao;

	@Test
	public void testReduceNumber() {
		Date killTime = new Date();
		int updateCount = seckillDao.reduceNumber(1000L, killTime);
		System.out.println(updateCount);
	}

	@Test
	public void testQueryById() {
		Long id = 1000L;
		Seckill seckill = seckillDao.queryById(id);
		System.out.println(seckill);
	}
	
	@Test
	public void testQuery() {
		Long seckillId = 1000L;
		Seckill seckill = seckillDao.queryById(seckillId);
		if(null == seckill) {
			System.out.println(new Exposer(false, seckillId));
			return;
		}
		long startTime = seckill.getStartTime().getTime();
		long endTime = seckill.getEndTime().getTime();
		long nowTime = (new Date()).getTime();

		if (nowTime >= startTime && nowTime <= endTime) {
			String md5 = "真的MD5";
			System.out.println(new Exposer(true, md5, seckillId));
			return;
		}
		System.out.println(new Exposer(false, nowTime, startTime, endTime));
	}

	@Test
	public void testQueryAll() {
		List<Seckill> seckills = seckillDao.queryAll(0, 100);
		for (Seckill seckill : seckills) {
			System.out.println(seckill);
		}
	}

}
