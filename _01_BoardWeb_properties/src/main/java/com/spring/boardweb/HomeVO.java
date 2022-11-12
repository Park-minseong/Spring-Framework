package com.spring.boardweb;

//VO: ValueObject 데이터 테이블과 1:1 매칭되도록 구현하는 게 원칙
//DTO: Data Transfer Object
public class HomeVO {
	private String firstName;
	private String lastName;
	private int age;
	private String job;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
}
