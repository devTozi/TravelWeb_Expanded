package com.TravelWeb.board.model;

import java.sql.Timestamp;

public class CommentVO {
	
	private int cno;
	private int bno;
	private String id;
	private String content;
	private Timestamp regdate;
	
	public CommentVO() {}

	public CommentVO(int cno, int bno, String id, String content, Timestamp regdate) {
		super();
		this.cno = cno;
		this.bno = bno;
		this.id = id;
		this.content = content;
		this.regdate = regdate;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	
	
}
