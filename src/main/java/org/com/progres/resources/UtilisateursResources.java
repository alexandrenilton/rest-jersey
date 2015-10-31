package org.com.progres.resources;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.com.progres.entity.Utilisateur;
import org.com.progres.service.UtilisateurService;



@Path("/users")
public class UtilisateursResources {
	@Context
	UriInfo uriInfo;
	
	@GET
	@Produces ("application/xml")
	public List<Utilisateur> getUsersAll() {
		
		List<Utilisateur> als=null;
		try {
			als = UtilisateurService.getInstance().getUtilisateurAll();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return als;
	
	}

	@POST
	@Consumes ("application/xml")
	@Produces ("application/xml")
	public Utilisateur createUtilisateur(Utilisateur user){
		URI uri = uriInfo.getAbsolutePathBuilder().path(user.getName()).build();
		Response res = Response.created(uri).build();
		try {
			UtilisateurService.getInstance().CreateUtilisateur(user);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Path("/user/{username}")
	@GET
	@Produces ("application/xml")
	public List<Utilisateur> getUser(@PathParam("username") String username) {
		List<Utilisateur> asl = null;
		try {
			asl= UtilisateurService.getInstance().getUtilisateur(username);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return asl;
	}
}