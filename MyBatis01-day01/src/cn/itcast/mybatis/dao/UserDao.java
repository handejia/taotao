package cn.itcast.mybatis.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;

import cn.itcast.mybatis.pojo.User;

public interface UserDao {

	/**
	 * 根据id查询用户
	 * 
	 * @param id
	 * @return
	 */
	public User queryUserById(int id);

	/**
	 * 根据用户名模糊查询
	 * 
	 * @param userName
	 * @return
	 */
	public List<User> queryUserByUserName(String userName);

	/**
	 * 新增
	 * 
	 * @param user
	 */
	public void saveUser(User user);

	/**
	 * 更新
	 * 
	 * @param user
	 */
	public void updateUser(User user);

	/**
	 * 删除
	 * 
	 * @param user
	 */
	public void deleteUserById(int id);

}
