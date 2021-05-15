package com.app.nomorenini.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class User {
	public int id;
	String name; //max 100
	String address; //max 100
	Date birthdate; //max 18 yo
	public String email;//max 100
	String password; //max 50
	float latitude;
	float longitude;

	public User(JSONObject jsonObject) {
		try {
			id=jsonObject.getInt("id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static int getType(User u){
		if(u instanceof Employee)return 0;
		else if(u instanceof Company)return 1;
		return 2;
	}
	public User(String email){
		this.email=email;
		this.password="";
	}
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
