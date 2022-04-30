package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ClassMaster {
	@Id
	@GeneratedValue
	private int class_id;
	private String class_name;
	
	 public ClassMaster(int id, String name) {
	        super();
	        this.class_id = id;
	        this.class_name = name;
	    }
	 
	public int getClass_id() {
		return class_id;
	}
	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
}
