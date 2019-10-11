package com.khrd.dto;

import java.util.Date;

public class Member {
	private String memberId;
	private String name;
	private String password;
	private Date regdate;
	
	
	public Member() {}

	public Member(String memberId, String name, String password, Date regdate) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.password = password;
		this.regdate = regdate;
	}

	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Date getRegdate() {
		return regdate;
	}


	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", name=" + name + ", password=" + password + ", regdate=" + regdate + "]";
	}
}