package com.example.model;

import javax.persistence.Entity;

@Entity
public class Employee {

	private String id;
	private String name;
	private String projectName;

	public Employee() {

	}

	public Employee(String name, String projectName) {
		super();
		this.name = name;
		this.projectName = projectName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
