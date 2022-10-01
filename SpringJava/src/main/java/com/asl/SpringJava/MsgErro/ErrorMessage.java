package com.asl.SpringJava.MsgErro;

public class ErrorMessage {

private String msgTitle;
private String msgBody;
private Integer msgStatus;


public String getMsgTitle() {
	return msgTitle;
}
public void setMsgTitle(String msgTitle) {
	this.msgTitle = msgTitle;
}
public String getMsgBody() {
	return msgBody;
}
public void setMsgBody(String msgBody) {
	this.msgBody = msgBody;
}
public Integer getMsgStatus() {
	return msgStatus;
}
public void setMsgStatus(Integer msgStatus) {
	this.msgStatus = msgStatus;
}


public ErrorMessage(String msgTitle, String msgBody, Integer msgStatus) {
	super();
	this.msgTitle = msgTitle;
	this.msgBody = msgBody;
	this.msgStatus = msgStatus;
}
}
