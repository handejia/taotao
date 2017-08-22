package cn.itcast.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class MyTest {
	@Test
	public void  myTest() throws IOException{
		//读取配置文件
		InputStream stream = Resources.getResourceAsStream("MyBatisConfig.xml");
		//根据主配置文件创建会话工厂
		SqlSessionFactory builder = new  SqlSessionFactoryBuilder().build(stream);
		//根据会话工厂创建会话对象 
		SqlSession session = builder.openSession();
		
		Object one = session.selectOne("test.selectUserById",1001);
		System.out.println(one);
		session.close();
	}
}
