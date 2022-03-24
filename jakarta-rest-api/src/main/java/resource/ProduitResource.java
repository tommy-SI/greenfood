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

import business.ProduitBusiness;
import entities.Produit;
import exceptions.technical.DAOException;

// Une fois que l'appication tourne sur le payara les services sont accessibles à l'adresse :
// http://localhost:8080/api-rest/api/produits/ 
@Path("produits")
public class ProduitResource {

	@Inject
	private ProduitBusiness produitBusiness;
	
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
	public Response getAllProduits() {
		try {
			return Response.ok(produitBusiness.getAllProduits()).build();
		} catch (DAOException e) {
			return Response.status(500, e.getMessage()).build();
		}
	}
	
	// READ une produits par son id
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProduit(@PathParam("id") int id) {
		try {
			return Response.ok(produitBusiness.get(id)).build();
		} catch (DAOException e) {
			return Response.status(500, e.getMessage()).build();
		}
	}
	
	// CREATE une produit
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProduit(Produit produit) {
		try {
			return Response.ok(produitBusiness.add(produit)).build();
		} catch (DAOException e) {
			return Response.status(500, e.getMessage()).build();
		}
	}
	
	// DELETE une produit
	@DELETE
	public Response deleteProduit(Produit produit) {
		try {
			produitBusiness.delete(produit);
		} catch (DAOException e) {
			return Response.status(500, e.getMessage()).build();
		}
		return Response.noContent().build();
	}
	
	// UPDATE une produit par son id
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProduit(@PathParam("id") long id, Produit produit) {
		//produit.setId(id);
		try {
			return Response.ok(produitBusiness.update(produit)).build();
		} catch (DAOException e) {
			return Response.status(500, e.getMessage()).build();
		}
	}
	
	// READ une produit par son name
	@GET
	@Path("search/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchProduit(@QueryParam("name") String name) {
		try {
			return Response.ok(produitBusiness.search(name)).build();
		} catch (DAOException e) {
			return Response.status(500, e.getMessage()).build();
		}
	}
	
}
