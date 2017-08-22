package cn.itcast.mybatis.pojo;

import java.util.Date;

public class Order {
	private Integer id;

	private Integer stu_gender;

	private String stu_name;

	private String stu_major;

	private String stu_addr;
	
	private Integer stu_age;
	
	private String stu_mobile;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStu_gender() {
		return stu_gender;
	}

	public void setStu_gender(Integer stu_gender) {
		this.stu_gender = stu_gender;
	}

	public String getStu_name() {
		return stu_name;
	}

	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}

	public String getStu_major() {
		return stu_major;
	}

	public void setStu_major(String stu_major) {
		this.stu_major = stu_major;
	}

	public String getStu_addr() {
		return stu_addr;
	}

	public void setStu_addr(String stu_addr) {
		this.stu_addr = stu_addr;
	}

	public Integer getStu_age() {
		return stu_age;
	}

	public void setStu_age(Integer stu_age) {
		this.stu_age = stu_age;
	}

	public String getStu_mobile() {
		return stu_mobile;
	}

	public void setStu_mobile(String stu_mobile) {
		this.stu_mobile = stu_mobile;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", stu_gender=" + stu_gender + ", stu_name=" + stu_name + ", stu_major=" + stu_major
				+ ", stu_addr=" + stu_addr + ", stu_age=" + stu_age + ", stu_mobile=" + stu_mobile + "]";
	}
	
	

	}