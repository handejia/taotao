package cn.itcast.test;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.mybatis.dao.UserMapper;
import cn.itcast.mybatis.pojo.User;

public class UserMapperTest {
	//初始化函数
	private SqlSessionFactory sqlSessionFactory;
	
	@Before
	public void setUp() throws Exception{
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		InputStream stream = Resources.getResourceAsStream("MyBatisConfig.xml");
		this.sqlSessionFactory = builder.build(stream);
		
	}
	
	
	
	/**
	 * 
	 * 根据id查询用户
	 */
	@Test
	public void test1(){
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = mapper.userById(1001);
		System.out.println(user);
		sqlSession.close();
	}
	
	/**
	 * 根据name模糊查询用户
	 */
	@Test
	public void test2(){
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		List<User> list = mapper.userByName("王");
		for (User user : list) {
			System.out.println(user);
		}
		sqlSession.close();
	}
	/**
	 * 新增一个用户
	 * 
	 */
	@Test
	public void test3(){
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = new User();
		user.setName("韩得家");
		user.setSex("男");
		user.setMobile("13333333333");
		user.setAge("22");
		user.setAddress("河南");
		mapper.insertUser(user);
		sqlSession.commit();
		sqlSession.close();
	}
	/**
	 * 
	 * 更新用户
	 */
	@Test
	public void test4(){
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = new User();
		user.setName("韩寒");
		user.setUserId(1043);
		mapper.updateUser(user);
		sqlSession.commit();
		sqlSession.close();
	}
	/**
	 * 删除一个用户
	 */
	@Test
	public void test5(){
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		mapper.deletetUser(1043);
		sqlSession.commit();
		sqlSession.close();
	}
}
