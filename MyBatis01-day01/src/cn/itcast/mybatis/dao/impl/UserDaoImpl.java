package cn.itcast.mybatis.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import cn.itcast.mybatis.dao.UserDao;
import cn.itcast.mybatis.pojo.User;

public class UserDaoImpl implements UserDao {
	//获取会话工厂 模型qudon
	private SqlSessionFactory sqlSessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory ssf) {
		this.sqlSessionFactory = ssf;
	}

	/**
	 * 根据Id查询用户信息
	 */
	@Override
	public User queryUserById(int id) {
		//开启session
		SqlSession sqlSession = null; 
		
		try {
			sqlSession = sqlSessionFactory.openSession();
			//根据id查询用户信息
			User one = sqlSession.selectOne("test.selectUserById",id);
			return one;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			sqlSession.close();
		}
		
	}
	/**
	 * 根据name模糊查询用户
	 */
	@Override
	public List<User> queryUserByUserName(String userName) {
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			List<User> list = session.selectList("test.selectUserByName", userName);
			return list;
		} catch (Exception e) {
			
			e.printStackTrace();
			throw e;
		}finally {
			
			session.close();
		}
	}
	/**
	 * 新增一个客户
	 */
	@Override
	public void saveUser(User user) {
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			
			int insert = session.insert("test.insertUser", user);
			session.commit();
		} catch (Exception e) {
		
			e.printStackTrace();
			throw e;
		}finally {
			session.close();
		}
		
	}
	
	/**
	 * 更新客户
	 */
	@Override
	public void updateUser(User user) {
		SqlSession sqlSession = null;
		
		try {
			sqlSession = sqlSessionFactory.openSession();
			int update = sqlSession.update("test.updateUser", user);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			sqlSession.close();
		}
		

	}

	@Override
	public void deleteUserById(int id) {
		SqlSession sqlSession =null;
		
		try {
			sqlSession = sqlSessionFactory.openSession();
			int delete = sqlSession.delete("test.deleteUser", id);
			sqlSession.commit();
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		
	}

}
