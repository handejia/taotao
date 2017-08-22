package cn.itcast.mybatis.dao;

import java.util.List;

import cn.itcast.mybatis.pojo.User;

public interface UserMapper {
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	public User userById(Integer id);
	/**
	 * 根据name查询用户
	 * @param name
	 * @return
	 */
	public List<User> userByName(String name);
	/**
	 * 新增一个用户
	 * @param user
	 */
	public void  insertUser(User user);
	/**
	 * 更新用户
	 * @param user
	 */
	public void  updateUser(User user);
	/**
	 * 删除一个用户
	 * @param user
	 */
	public void deletetUser(int id);
	
}
