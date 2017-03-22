package org.companyLog.util;
/**
 * @TODO：返回的结果，json
 * @fileName : org.companyLog.util.JSONResult.java
 * date | author | version |   
 * 2017年3月13日 | Jiong | 1.0 |
 */
public class JSONResult {
	private String code;
	private Object data;
	private String msg;
	private int total;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}

