package org.com.progres.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.com.progres.entity.Utilisateur;
import org.com.progres.helper.DataServiceHelper;

public class UtilisateurService {
	public static UtilisateurService userService = new UtilisateurService();
	public static final String GET_USER = "SELECT * FROM USER";
	public static final String INSERT_USER = "Insert into user ";

	public List<Utilisateur> getUtilisateurAll() throws ClassNotFoundException, SQLException {
		List<Utilisateur> ls = new ArrayList();
		ls = DataServiceHelper.getInstance().executeQuery(GET_USER);
		return ls;
	}

	public List<Utilisateur> getUtilisateur(String name) throws ClassNotFoundException, SQLException {
		String SQL_WHERE_CAS = " where name='" + name + "'";
		List<Utilisateur> als = DataServiceHelper.getInstance().executeQuery(GET_USER + SQL_WHERE_CAS);
		return als;
	}

	public void CreateUtilisateur(Utilisateur user) throws SQLException, ClassNotFoundException {
		String SQL_WHERE_CASE = " VALUES('" + user.getName() + "','"	+ user.getPassword() + "')";
		DataServiceHelper.getInstance().executeUpdateQuery(INSERT_USER + SQL_WHERE_CASE);
	}

	public static UtilisateurService getInstance() {
		return userService;
	}
}