package ws.rest.servicios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import back.entidades.Usuario;
import back.excepciones.ServicioExcepcion;
import front.servicios.LoginSrv;
import front.servicios.UsuarioSrv;
import front.web.dto.UsuarioDTO;
import ws.rest.args.ArgsLogin;
import ws.rest.interfaces.IUsuarioRest;

@Stateless
@LocalBean
public class UsuarioRestSrv implements IUsuarioRest{

	@Inject
	private UsuarioSrv usuarioSrv;
	@Inject
	private LoginSrv loginSrv;
	
	@Override
	public Response getUsuarios() {
		try {
			List<UsuarioDTO> ret = usuarioSrv.filtrar(null);	
			return Response.ok().entity(ret).build();
			
		} catch (ServicioExcepcion e) {		
			e.printStackTrace();
			return  Response.serverError().build();
		}
	}

	@Override
	public String echo() {		
		return "Servicio de usuarios habilitado";
	}

	@Override
	public Response login(ArgsLogin argsLogin) {
		if (argsLogin.getUser() == null || argsLogin.getPass() == null)
			return Response.status(Response.Status.PARTIAL_CONTENT).entity("Por favor, especifica ambas credenciales").build();
		
		try{
			Usuario usuario = loginSrv.login(argsLogin.getUser(), argsLogin.getPass());
			if (usuario != null)				
				return Response.ok("Bienvenid@ " + usuario.getNombre() + " " + usuario.getApellido()).build();
		
			return Response.status(Response.Status.NOT_FOUND).entity("Usuario o contraseña incorrecta").build();
			
		}catch (ServicioExcepcion e) {		
			e.printStackTrace();
			return Response.status(Response.Status.BAD_GATEWAY).entity(e.getMessage()).build();
		}
	}

}
