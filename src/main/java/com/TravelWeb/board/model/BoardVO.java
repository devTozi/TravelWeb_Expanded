package com.TravelWeb.board.model;

import java.sql.Timestamp;

public class BoardVO {
	
	private int bno;
	private String image;
	private String id;
	private String title;
	private String content;
	private int hit;
	private int likes;
	private Timestamp regdate;
	
	public BoardVO() {}

	public BoardVO(int bno, String image, String id, String title, String content, int hit, int likes, Timestamp legdate) {
		super();
		this.bno = bno;
		this.image = image;
		this.id = id;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.likes = likes;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

}
