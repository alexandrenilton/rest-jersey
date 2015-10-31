package org.com.progres.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.com.progres.entity.Utilisateur;

public class DataServiceHelper {
	public static DataServiceHelper dataServiceHelper = null;
	private Connection con = null;
	DataSource dataSource = null;
	InitialContext initialContext = null;
	//public static final String DB_URL = "jdbc:h2:tcp://localhost/~/test";
	public static final String DB_URL = "jdbc:h2:tcp://localhost/~/test;IFEXISTS=TRUE;TRACE_LEVEL_SYSTEM_OUT=3";
	public static final String DRIVER_NAME = "org.h2.Driver";

	/**
	 * 
	 * This method is used to create an object for the given DAO class name.
	 */

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_NAME);
		con = DriverManager.getConnection(DB_URL, "sa", "ds");
		return con;
	}

	public void closeConnection() throws SQLException {
		if (isConnectionOpen()) {
			con.close();
			con = null;
		}
	}

	public boolean isConnectionOpen() {
		return (con != null);
	}

	public static DataServiceHelper getInstance() {
		if (dataServiceHelper == null) {
			dataServiceHelper = new DataServiceHelper();
		}
		return dataServiceHelper;
	}

	public void executeUpdateQuery(String query) throws SQLException,
			ClassNotFoundException {
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		stmt.execute(query);
		closeConnection();
	}

	public List<Utilisateur> executeQuery(String query) throws ClassNotFoundException,
			SQLException {
		/*
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		List<Utilisateur> als = convertPojoList(rs);
		closeConnection();
		*/
		
		return getListUtilisateurDummy();
		
		//return als;
	}

	private List<Utilisateur> getListUtilisateurDummy() {
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		
		for(int a=0 ; a<=10 ; a++){
			Utilisateur util = new Utilisateur();
			util.setName("Utilisateur_"+a);
			util.setPassword(Math.random()+"");
			utilisateurs.add(util);
		}
		return utilisateurs;
	}

	private List<Utilisateur> convertPojoList(ResultSet rs) throws SQLException {
		List<Utilisateur> asl = new ArrayList<Utilisateur>();
		while (rs.next()) {
			Utilisateur user = new Utilisateur(rs.getString("name"), rs.getString("password"));
			asl.add(user);
		}
		return asl;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String query = "Select * from user where name = 'nitin'";
		List<Utilisateur> als = DataServiceHelper.getInstance().executeQuery(query);
		System.out.println("List==>" + als);
	}
}