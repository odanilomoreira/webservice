package tcc.iff.rdf.webservice.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tcc.iff.rdf.webservice.model.Offer;
import tcc.iff.rdf.webservice.services.CompanyOfferServices;

@Path("/")
public class CompanyOfferResources {
	
	CompanyOfferServices offerServ = new CompanyOfferServices();
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listarOfertas(@PathParam("CompanyID") String companyID) {		
		return offerServ.getAllOffers(companyID);	
    }
	
	@DELETE
	public Response deletarProdutos(@PathParam("CompanyID") String companyID) {
		offerServ.deleteAllOffers(companyID);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
		public Response adicionarProduto(@PathParam("CompanyID") String companyID, List<Offer> OfferList) {
		   return offerServ.addOffering(companyID, OfferList);
	}
	
	@GET
	@Path("/{OfferID}")
	 @Produces({	"application/json", 
	    	"application/ld+json",
	    	"application/n-triples",
	    	"application/rdf+xml",
	    	"application/turtle",
	    	"application/rdf+json"
	    	})
		public Response lerProduto(@PathParam("CompanyID") String companyID, @PathParam("OfferID") String offerID, @HeaderParam("Accept") String accept) {
		return offerServ.getOffer(companyID, offerID, accept);
	}
	
	@DELETE
	@Path("/{OfferID}")
	public Response deletarProduto(@PathParam("CompanyID") String companyID, @PathParam("OfferID") String offerID) {
		offerServ.deleteOffer(companyID, offerID);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
	@PUT
	@Path("/{OfferID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response alterarProduto(@PathParam("CompanyID") String companyID, @PathParam("OfferID") String offerID, Offer updatedOffer) {
		return offerServ.updateOffering(companyID, offerID, updatedOffer);
	}
	
}
