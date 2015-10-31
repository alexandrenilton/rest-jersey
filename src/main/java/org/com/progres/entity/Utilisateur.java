package org.com.progres.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Utilisateur {
	private int id;
	private String name;
	private String password;

	public Utilisateur(String username, String password) {
		this.name = username;
		this.password = password;
	}

	public Utilisateur() {
		super();
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}