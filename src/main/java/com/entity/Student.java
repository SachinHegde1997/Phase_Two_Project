package com.entity;

import javax.persistence.*;
@Entity
@Table(name = "student", uniqueConstraints = @UniqueConstraint(columnNames = { "student_id", "class_id", "roll_no" }))
public class Student {
	@Id
	@GeneratedValue
	private int student_id;
	private String name;
	private String address;
	private int class_id;
	private int roll_no;

	public int getRoll_no() {
		return roll_no;
	}

	public void setRoll_no(int roll_no) {
		this.roll_no = roll_no;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
