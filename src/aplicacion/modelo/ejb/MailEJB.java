package aplicacion.modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import aplicacion.modelo.pojo.Contenido;
import aplicacion.modelo.pojo.ProductoScraped;
import aplicacion.modelo.pojo.Usuario;

@Stateless
@LocalBean
public class MailEJB {

	public void enviarCorreo(Usuario usuario, Contenido contenido, ProductoScraped productoScraped) {
		
		
								}

	}

}
