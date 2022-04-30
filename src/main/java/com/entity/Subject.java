package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Subject {
	@Id
	@GeneratedValue
	private int subject_id;
	private String subject_name;
	public Subject(int id, String name) {
        super();
        this.subject_id = id;
        this.subject_name = name;
    }
	public int getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}
	public String getSubject_name() {
		return subject_name;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
}
