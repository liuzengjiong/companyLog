package org.companyLog.bean;


public class LogShower extends Log{
	private String AuthorName;
	private int commentNum;
	private boolean haveCollect;
	
	
	
	public String getAuthorName() {
		return AuthorName;
	}
	public void setAuthorName(String authorName) {
		AuthorName = authorName;
	}
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public boolean isHaveCollect() {
		return haveCollect;
	}
	public void setHaveCollect(boolean haveCollect) {
		this.haveCollect = haveCollect;
	}
	
}