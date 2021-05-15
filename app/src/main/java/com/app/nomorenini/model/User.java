package com.app.nomorenini.model;

import java.util.Date;

public class User {
	int id;
	String name; //max 100
	String address; //max 100
	Date birthdate; //max 18 yo
	String email;//max 100
	String password; //max 50
	float latitude;
	float longitude;

	public User(String name,Date birthdate,String email,String pass){
		this.name=name;
		this.birthdate=birthdate;
		this.email=email;
		this.password=pass;
	}
	public User(String name,Date birthdate,String email,String pass,String address){
		this.address=address;
		this.name=name;
		this.birthdate=birthdate;
		this.email=email;
		this.password=pass;
	}
	public User(int id,String name,Date birthdate,String email,String pass,String address){
		this.id=id;
		this.address=address;
		this.name=name;
		this.birthdate=birthdate;
		this.email=email;
		this.password=pass;
	}
	public User(int id,String name,Date birthdate,String email,String pass,String address,float lat,float lon){
		this.id=id;
		this.address=address;
		this.name=name;
		this.birthdate=birthdate;
		this.email=email;
		this.password=pass;
		this.latitude=lat;
		this.longitude=lon;
	}
}
