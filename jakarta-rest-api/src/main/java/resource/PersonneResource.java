package resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import business.PersonneBusiness;
import entities.Personne;
import exceptions.technical.DAOException;

// Une fois que l'appication tourne sur le payara les services sont accessibles à l'adresse :
// http://localhost:8080/api-rest/api/personnes/ 
@Path("personnes")
public class PersonneResource {

	@Inject
	private PersonneBusiness personneBusiness;
	
	// Le classique hello world
	@GET
	@Path("hello")
	@Produces(MediaType.TEXT_PLAIN)
	public Response helloWorld() {
		return Response.ok("Hello World").build();
	}
	
	// READ toute les personnes
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPersonnes() {
		try {
			return Response.ok(personneBusiness.getAllPersonnes()).build();
		} catch (DAOException e) {
			return Response.status(500, e.getMessage()).build();
		}
	}
	
	// READ une personne par son id
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPersonne(@PathParam("id") int id) {
		try {
			return Response.ok(personneBusiness.get(id)).build();
		} catch (DAOException e) {
			return Response.status(500, e.getMessage()).build();
		}
	}
	
	// CREATE une personne
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPersonne(Personne personne) {
		try {
			return Response.ok(personneBusiness.add(personne)).build();
		} catch (DAOException e) {
			return Response.status(500, e.getMessage()).build();
		}
	}
	
	// DELETE une personne
	@DELETE
	public Response deletePersonne(Personne personne) {
		try {
			personneBusiness.delete(personne);
		} catch (DAOException e) {
			return Response.status(500, e.getMessage()).build();
		}
		return Response.noContent().build();
	}
	
	// UPDATE une personne par son id
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePersonne(@PathParam("id") long id, Personne personne) {
		//personne.setId(id);
		try {
			return Response.ok(personneBusiness.update(personne)).build();
		} catch (DAOException e) {
			return Response.status(500, e.getMessage()).build();
		}
	}
	
	// READ une personne par son name
	@GET
	@Path("search/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchPersonne(@QueryParam("name") String name) {
		try {
			return Response.ok(personneBusiness.search(name)).build();
		} catch (DAOException e) {
			return Response.status(500, e.getMessage()).build();
		}
	}
	
}
