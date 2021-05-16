package com.app.nomorenini.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	public int id;
	public int type;
	public String name; //max 100
	public String address; //max 100
	public String birthdate; //max 18 yo
	public String email;//max 100
	public String password; //max 50
	float latitude;
	float longitude;

	public User(JSONObject jsonObject) {
		try {
			id=jsonObject.getInt("id");
			type=jsonObject.getInt("type");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public User(JSONObject jsonObject,int i) {
		try {
			id=jsonObject.getInt("id");
			 name=jsonObject.getString("namme"); //max 100
			 address=jsonObject.getString("address"); //max 100
			 birthdate=jsonObject.getString("birthdate"); //max 18 yo
			 email=jsonObject.getString("email");;//max 100
			 password=jsonObject.getString("password");; //max 50
			 latitude=jsonObject.getLong("latitude");;
			 longitude=jsonObject.getLong("longitude");;
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
	public User(String name,String birthdate,String email,String pass){
		this.name=name;
		this.birthdate=birthdate;
		this.email=email;
		this.password=pass;
	}
	public User(String name,String birthdate,String email,String pass,String address){
		this.address=address;
		this.name=name;
		this.birthdate=birthdate;
		this.email=email;
		this.password=pass;
	}
	public User(int id,String name,String birthdate,String email,String pass,String address){
		this.id=id;
		this.address=address;
		this.name=name;
		this.birthdate=birthdate;
		this.email=email;
		this.password=pass;
	}
	public User(int id,String name,String birthdate,String email,String pass,String address,float lat,float lon){
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
