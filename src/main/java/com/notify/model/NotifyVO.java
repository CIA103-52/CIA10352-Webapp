package com.notify.model;

import java.sql.Date;

public class NotifyVO implements java.io.Serializable {

	private Integer notify_no;
	private Integer mem_id;
	private String notify_content;

	public NotifyVO() {
		super();
	}

	public Integer getNotify_no() {
		return notify_no;
	}

	public void setNotify_no(Integer notify_no) {
		this.notify_no = notify_no;
	}

	public Integer getMem_id() {
		return mem_id;
	}

	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}

	public String getNotify_content() {
		return notify_content;
	}

	public void setNotify_content(String notify_content) {
		this.notify_content = notify_content;
	}
}
