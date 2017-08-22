package cn.itcast.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.mybatis.dao.OrderMapper;
import cn.itcast.mybatis.pojo.Order;

public class OrderMapperTest {
	private SqlSessionFactory sqlSessionFactory;
	/**
	 * 会话工厂初始化函数
	 * @throws Exception 
	 */
	//初始化函数
	@Before
	public void setUp() throws Exception{
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		InputStream stream = Resources.getResourceAsStream("MyBatisConfig.xml");
		this.sqlSessionFactory = builder.build(stream);
	}
	/**
	 * 根据id查询资料
	 */
	@Test
	public void test1(){
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
		Order order = mapper.findById(1);
		System.out.println(order);
		sqlSession.close();
	}
	/**
	 * 模糊查询
	 */
	@Test
	public void test2(){
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
		mapper.findByMajor("管理");
		sqlSession.close();
	}
	@Test
	public void test3(){
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
		Order order = new Order();
		order.setStu_name("熊熊");
		order.setStu_mobile("14444444444");
		order.setStu_major("接着吃");
		order.setStu_gender(1);
		order.setStu_age(22);
		order.setStu_addr("蛮荒");
		mapper.saveOrder(order);  
		sqlSession.commit();
		sqlSession.close();
		
	}
	
	
}
