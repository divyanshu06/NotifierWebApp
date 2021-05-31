package com.notifier.model;

import java.time.LocalDate;

public class Notes {
	
	private int nid;
	private int nbid;
	private int userid;
	private String nname;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate reminderDate;
	private String status;
	private String tag;
	private String description;
	
	
	public Notes(int nbid, String nname, LocalDate startDate, LocalDate endDate,
			LocalDate reminderDate, String status, String tag, String description) {
		super();
		this.nbid = nbid;
		this.nname = nname;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reminderDate = reminderDate;
		this.status = status;
		this.tag = tag;
		this.description = description;
	}
	public Notes(String nname2, LocalDate startDate1, LocalDate endDate1, LocalDate reminderDate1, String status2,
			String tag2, String description2) {
		// TODO Auto-generated constructor stub

		this.nname = nname2;
		this.startDate = startDate1;
		this.endDate = endDate1;
		this.reminderDate = reminderDate1;
		this.status = status2;
		this.tag = tag2;
		this.description = description2;
	}
	public Notes(String nname2, LocalDate startDate1, LocalDate endDate1, LocalDate reminderDate1, String status2,
			String tag2, String description2, int nid2) {
		// TODO Auto-generated constructor stub
		this.nname = nname2;
		this.startDate = startDate1;
		this.endDate = endDate1;
		this.reminderDate = reminderDate1;
		this.status = status2;
		this.tag = tag2;
		this.description = description2;
		this.nid = nid2;
	}
	
	public Notes(String nname2, LocalDate reminderDate1, String status2, int nid2) {
		// TODO Auto-generated constructor stub
		this.nname = nname2;
		this.reminderDate = reminderDate1;
		this.status = status2;
		this.nid = nid2;
	}
	public Notes() {
		// TODO Auto-generated constructor stub
	}
	public Notes(int nbid2, int nid2, String nname2, LocalDate startDate1, LocalDate endDate1, LocalDate reminderDate1,
			String status2, String tag2, String description2) {
		// TODO Auto-generated constructor stub
		this.nbid = nbid2;
		this.nid = nid2;
		this.nname = nname2;
		this.startDate = startDate1;
		this.endDate = endDate1;
		this.reminderDate = reminderDate1;
		this.status = status2;
		this.tag = tag2;
		this.description = description2;
	}
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
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
	public String getNname() {
		return nname;
	}
	public void setNname(String nname) {
		this.nname = nname;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public LocalDate getReminderDate() {
		return reminderDate;
	}
	public void setReminderDate(LocalDate reminderDate) {
		this.reminderDate = reminderDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
