package tcc.iff.rdf.webservice.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tcc.iff.rdf.webservice.RDFMediaType;
import tcc.iff.rdf.webservice.model.Company;
import tcc.iff.rdf.webservice.services.CompanyServices;

@Path("/companies")
public class CompanyResources {
	
	CompanyServices cp = new CompanyServices();
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String listarEmpresas() {		
		return cp.getAllCompanies();		
    }
	
	@DELETE
	public Response deletarEmpresas() {
		cp.deleteAllCompanies();
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
		public Response adicionarEmpresa(List<Company> companyList) {
		cp.addCompany(companyList);
	   return Response.status(Response.Status.CREATED).build();
	}
	
	@GET
	@Path("/{CompanyID}")
    @Produces(RDFMediaType.APPLICATION_JSON_LD)
	public String lerEmpresa(@PathParam("CompanyID") String companyID) {
		return cp.getCompany(companyID);
	}
	
	@DELETE
	@Path("/{CompanyID}")
	public Response deletarEmpresa(@PathParam("CompanyID") String companyID) {
		cp.deleteCompany(companyID);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
	@PUT
	@Path("/{CompanyID}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response alterarEmpresa(@PathParam("CompanyID") String companyID, Company newCompany) {
		cp.updateCompany(companyID, newCompany);
		return Response.status(Response.Status.CREATED).build();
	}
}