package com.app.nomorenini.model;

import java.util.Date;

public class Employee extends User {
	public Employee(String name, Date birthdate, String email, String pass) {
		super(name, birthdate, email, pass);
	}

	public Employee(String name, Date birthdate, String email, String pass, String address) {
		super(name, birthdate, email, pass, address);
	}

	public Employee(int id, String name, Date birthdate, String email, String pass, String address) {
		super(id, name, birthdate, email, pass, address);
	}

	public Employee(int id, String name, Date birthdate, String email, String pass, String address, float lat, float lon) {
		super(id, name, birthdate, email, pass, address, lat, lon);
	}
}
