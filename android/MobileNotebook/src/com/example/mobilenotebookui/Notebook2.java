package com.example.mobilenotebookui;

public class Notebook2 {
	private String subject;
	private int pageNumber;
	private String path;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path){
		  this.path = path;
	  }

	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return subject + ": p" + pageNumber;
	}
}
