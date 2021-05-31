package com.notifier.model;

public class Notebook {
	
	private int nbid;
	private int userid;
	private String nbname;

	public Notebook() {	}
	
	public Notebook(int nbid, int userid, String nbname) {
		super();
		this.nbid = nbid;
		this.userid = userid;
		this.nbname = nbname;
	}

	
	public int getNbid() {
		return nbid;
	}
	public void setNbid(int nbid) {
		this.nbid = nbid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getNbname() {
		return nbname;
	}
	public void setNbname(String nbname) {
		this.nbname = nbname;
	}
	
	
}
