package com.cyxy.entity;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

public class JsonResult<T> implements Serializable{

	public final static Integer SUCCESS = 0;
	
	/**
	 * 状态码，可以自由指定内容，一般用“0”代表成功，其他数字代表有异常
	 */
	private int state;
	
	/**
	 * 消息，用于往前台输出字符串内容
	 */
	private String message = "";
	
	/**
	 * 对象数据，用来承载单个对象的数据
	 */
	private T data;
	
	/**
	 * 集合数据，用来承载多个对象的集合数据
	 */
	private List<T> datas;
	
	/**
	 * 分页数据，用来承载带有分页的多个对象的分页数据集
	 */
	private PageInfo<T> pagedatas;
	
	/**
	 * 用于适配bootstrap-table
	 */
	private long total;
	private List<T> rows;
	
	/**
	 * constructers
	 */
	public JsonResult() {
	}
	
	/**
	 * 增删改——成功情况
	 */
	public JsonResult(String message) {
		state = SUCCESS;
		this.message = message;
	}
	
	/**
	 * 查询单个对象成功
	 */
	public JsonResult(T data) {
		state = SUCCESS;
		this.data = data;
	}
	
	/**
	 * 查询集合对象成功
	 */
	public JsonResult(List<T> datas) {
		state = SUCCESS;
		this.datas = datas;
	}
	
	/**
	 * 失败情况
	 * @param state    错误码
	 * @param message  错误提示消息
	 */
	public JsonResult(int state, String message) {
		this.state = state;
		this.message = message;
	}

	/**
	 * 查询分页对象成功
	 */
	public JsonResult(PageInfo<T> pagedatas) {
		state = SUCCESS;
		this.pagedatas = pagedatas;
	}

	/**
	 * getter   and   setter
	 */
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public PageInfo<T> getPagedatas() {
		return pagedatas;
	}

	public void setPagedatas(PageInfo<T> pagedatas) {
		this.pagedatas = pagedatas;
	}

	public long getTotal() {
		return pagedatas!=null?pagedatas.getTotal():0;
	}

	public List<T> getRows() {
		return pagedatas!=null?pagedatas.getList():null;
	}
	
	
	
}
