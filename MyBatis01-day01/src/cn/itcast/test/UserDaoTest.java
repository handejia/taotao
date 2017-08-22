package cn.itcast.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.mybatis.dao.UserDao;
import cn.itcast.mybatis.dao.impl.UserDaoImpl;
import cn.itcast.mybatis.pojo.User;

public class UserDaoTest {
	
	/**
	 * dao开发方式
	 */
	//测试初始化函数
	private SqlSessionFactory sqlSessionFactory;
	private UserDaoImpl userDao;
	@Before
	public void init() throws Exception {
		//读取配置文件
		InputStream stream = Resources.getResourceAsStream("MyBatisConfig.xml");
		//根据配置文件创建会话工厂
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream);
		 userDao = new UserDaoImpl();
		 userDao.setSqlSessionFactory(sqlSessionFactory);
		 
	}
	/**
	 * 根据id查询用户信息
	 */
	@Test
	public void test1(){
		User user = userDao.queryUserById(1001);
		System.out.println(user);
	}
	
	/**
	 * 根据name查询用户
	 */
	@Test
	public void test2(){
		List<User> user = userDao.queryUserByUserName("王");
		System.out.println(user);
	}
	
	/**
	 * 保存一个用户
	 */
	@Test
	public void test3(){
		User user1 = new User();
		user1.setName("王大大");
		user1.setSex("男");
		user1.setAge("22");
		user1.setAddress("山东");
		user1.setMobile("山东");
		userDao.saveUser(user1);
		
	}
	/**
	 * 更新用户
	 */
	@Test
	public void  test4(){
		User user = new User();
		user.setUserId(1042);
		user.setAddress("河南");
		user.setName("王二二");
		userDao.updateUser(user);
	}
	/**
	 * 删除用户
	 */
	@Test
	public void test5(){
		userDao.deleteUserById(1042);
	}
	
}
