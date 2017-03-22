package org.companyLog.bean;


public class ReportShower extends Report{
	
	//自定义
	private String authorName;
	private String receiverName;
	
	

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
}