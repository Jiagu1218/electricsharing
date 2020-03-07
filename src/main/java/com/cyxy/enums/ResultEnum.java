package com.cyxy.enums;

public enum ResultEnum {
	
	SYS_ERROR(-1,"系统错误"),
	CHECK_FAIL(100),
	ADD_FAIL(101,"添加失败"),
	DELETE_FAIL(102,"删除失败"),
	EDIT_FAIL(103,"修改失败"),
	FIND_ERROR(105,"查询异常"),
	EXIST_USER_NAME(106,"用户名已处在"),
	LEGAL_USER_NAME(107,"用户名可使用"),
	EXIST_USER_PHONE(108,"手机号已处在"),
	LEGAL_USER_PHONE(109,"手机号可使用"),
	REGISTER_SUCCESS(110,"注册成功"),
	REGISTER_FIELD(111,"抱歉，注册失败，请重来一次"),
	SEND_CODE_FIELD(112,"验证码发送失败"),
	SEND_CODE_SUCCESS(113,"验证码已发送，请注意查收"),
	ILLEGAL_PHONE(114,"手机号码有误"),
	CHECK_SMS_CODE_SUCCESS(115,"验证码正确"),
	CHECK_SMS_CODE_FIELD(116,"验证码错误"),
	UPDATE_PARTNER_SUCCESS(120,"更新商家信息成功"),
	UPDATE_PARTNER_FIELD(121,"更新商家信息失败"),
	UPDATE_USER_INFO_SUCCESS(122,"更新用户信息成功"),
	UPDATE_USER_INFO_FIELD(123,"更新用户信息失败"),
	RENT_SUCCESS(200,"租借成功，请记得取走电宝"),
	NEED_LOGIN(201,"请先登录，再使用本功能！！！"),
	NO_POWER_CAN_RENT(202,"抱歉，没有可供租借的电宝"),
	HAVE_NEED_COMPLETE_ORDER(203,"请先完成上一个订单"),
	NO_NEED_COMPLETE_ORDER(204,"没有未完成的订单"),
	NO_FREE_HUB_PORT(205,"没有可供归还的电桩"),
	NO_ENOUGH_BALANCE(106,"余额不足"),
	RETURN_POWER_SUCCESS(300,"成功归还");


	private Integer code;
	private String msg;
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	private ResultEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private ResultEnum(Integer code) {
		this.code = code;
	}
	
	
	
}
