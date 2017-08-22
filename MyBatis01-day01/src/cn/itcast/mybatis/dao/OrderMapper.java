package cn.itcast.mybatis.dao;

import java.util.List;

import cn.itcast.mybatis.pojo.Order;

public interface OrderMapper {
	/**
	 * 根据id查询资料
	 * @param id
	 * @return
	 */
	public Order findById(Integer id);
	/**
	 * 模糊查询
	 * @param major
	 * @return
	 */
	public List<Order> findByMajor(String major);
	/**
	 * 新增
	 * @param order
	 */
	public void saveOrder(Order order);
	/**
	 * 修改
	 * @param order
	 */
	public void updateOrder(Order order);
	/**
	 * 删除
	 * @param id
	 */
	public void deleteOrder(Integer id);

}
