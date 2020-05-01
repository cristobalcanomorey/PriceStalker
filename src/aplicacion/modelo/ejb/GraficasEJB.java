package aplicacion.modelo.ejb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import aplicacion.modelo.LogSingleton;
import aplicacion.modelo.dao.PrecioDAO;
import aplicacion.modelo.pojo.Precio;

@Stateless
@LocalBean
public class GraficasEJB {

	public ArrayList<Precio> obtenerPreciosDeProductoPorIdContenido(String idContenido) {
		LogSingleton log = LogSingleton.getInstance();
		int contenidoId = -1;
		try {
			contenidoId = Integer.parseInt(idContenido);
		} catch (Exception e) {
			log.getLoggerGraficasEJB().error("Se ha producido un error en GET GraficasEJB: ", e);
			return null;
		}
		return PrecioDAO.obtenerPreciosDeProductoPorIdContenido(contenidoId);
	}

	public String fechasEnPreciosAJson(ArrayList<Precio> precios) {
		String resul = null;
		if (!precios.isEmpty()) {
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String fecha;
			resul = "[";
			for (int i = precios.size() - 1; i >= 0; i--) {
				fecha = simpleDateFormat.format(precios.get(i).getFecha());
				if (i == 0) {
					resul += "'" + fecha + "'" + "]";
				} else {
					resul += "'" + fecha + "'" + ",";
				}
			}
		}
		return resul;
	}

	public String costesEnPreciosAJson(ArrayList<Precio> precios) {
		String resul = null;
		if (!precios.isEmpty()) {
			resul = "[";
			for (int i = precios.size() - 1; i >= 0; i--) {
				if (i == 0) {
					resul += "'" + precios.get(i).getCoste() + "'" + "]";
				} else {
					resul += "'" + precios.get(i).getCoste() + "'" + ",";
				}
			}
		}
		return resul;
	}

}
