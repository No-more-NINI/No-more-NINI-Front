package com.app.nomorenini.model;

public class Offer {
	int id;
	Company comp;
	String title;
	String desc;
	float salary;
	//possem que les ofertes es puguin posar per regions?
	public Offer(int id,Company comp,String title,String desc,float salary){
		this.id=id;
		this.comp=comp;
		this.title=title;
		this.desc=desc;
		this.salary=salary;
	}
	public Offer(Company comp,String title,String desc,float salary){
		this.comp=comp;
		this.title=title;
		this.desc=desc;
		this.salary=salary;
	}
}
