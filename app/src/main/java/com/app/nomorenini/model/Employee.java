package com.app.nomorenini.model;


public class Employee extends User {
	public Employee(String name, String birthString, String email, String pass) {
		super(name, birthString, email, pass);
	}

	public Employee(String name, String birthString, String email, String pass, String address) {
		super(name, birthString, email, pass, address);
	}

	public Employee(int id, String name, String birthString, String email, String pass, String address) {
		super(id, name, birthString, email, pass, address);
	}

	public Employee(int id, String name, String birthString, String email, String pass, String address, float lat, float lon) {
		super(id, name, birthString, email, pass, address, lat, lon);
	}
}
