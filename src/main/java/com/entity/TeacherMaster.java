package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class TeacherMaster {
	@Id
	@GeneratedValue
	private int teacher_id;
	private String teacher_name;
	public TeacherMaster(int id, String name) {
        super();
        this.teacher_id = id;
        this.teacher_name = name;
    }
	public int getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
}
