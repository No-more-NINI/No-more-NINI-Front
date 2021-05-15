package com.app.nomorenini.model;

import java.util.Date;

public class Company extends User {
	String description;
	public Company(String name,String description, Date birthdate, String email, String pass) {
		super(name, birthdate, email, pass);
		this.description=description;
	}

	public Company(String name,String desc, Date birthdate, String email, String pass, String address) {
		super(name, birthdate, email, pass, address);
		this.description=desc;
	}

	public Company(int id, String name,String desc, Date birthdate, String email, String pass, String address) {
		super(id, name, birthdate, email, pass, address);
		this.description=desc;
	}

	public Company(int id, String name,String desc, Date birthdate, String email, String pass, String address, float lat, float lon) {
		super(id, name, birthdate, email, pass, address, lat, lon);
		this.description=desc;
	}
}
