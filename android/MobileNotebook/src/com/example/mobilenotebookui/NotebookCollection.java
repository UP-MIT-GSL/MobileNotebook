package com.example.mobilenotebookui;

public class NotebookCollection {
	private String subject;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return subject;
	}
}
