package com.app.nomorenini.model;



public class Company extends User {
	String description;
	public Company(String name,String description, String birthString, String email, String pass) {
		super(name, birthString, email, pass);
		this.description=description;
	}

	public Company(String name,String desc, String birthString, String email, String pass, String address) {
		super(name, birthString, email, pass, address);
		this.description=desc;
	}

	public Company(int id, String name,String desc, String birthString, String email, String pass, String address) {
		super(id, name, birthString, email, pass, address);
		this.description=desc;
	}

	public Company(int id, String name,String desc, String birthString, String email, String pass, String address, float lat, float lon) {
		super(id, name, birthString, email, pass, address, lat, lon);
		this.description=desc;
	}
}
