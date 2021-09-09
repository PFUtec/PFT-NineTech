package ws.rest.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ws.rest.args.ArgsLogin;

@Path("")
public interface IUsuarioRest {

	@GET
	@Path("echo")
	@Produces({MediaType.TEXT_PLAIN})
	public String echo();
	 
	@GET
    @Path("allUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarios();
	
	@POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(ArgsLogin argsLogin);
 
}
