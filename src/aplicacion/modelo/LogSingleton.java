package aplicacion.modelo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aplicacion.controlador.AddProducto;
import aplicacion.controlador.AdminUser;
import aplicacion.controlador.DelUser;
import aplicacion.controlador.Eliminar;
import aplicacion.controlador.Grafica;
import aplicacion.controlador.Lista;
import aplicacion.controlador.Login;
import aplicacion.controlador.Logout;
import aplicacion.controlador.Principal;
import aplicacion.controlador.Registro;
import aplicacion.modelo.dao.MyBatisUtil;
import aplicacion.modelo.ejb.GraficasEJB;
import aplicacion.modelo.ejb.ProductosEJB;
import aplicacion.modelo.ejb.ScraperEJB;

public class LogSingleton {
	private static final LogSingleton INSTANCE = new LogSingleton();
	private Logger loggerPrincipal = LoggerFactory.getLogger(Principal.class);
	private Logger loggerLogin = LoggerFactory.getLogger(Login.class);
	private Logger loggerLogout = LoggerFactory.getLogger(Logout.class);
	private Logger loggerRegistro = LoggerFactory.getLogger(Registro.class);
	private Logger loggerGrafica = LoggerFactory.getLogger(Grafica.class);
	private Logger loggerGraficasEJB = LoggerFactory.getLogger(GraficasEJB.class);
	private Logger loggerProductosEJB = LoggerFactory.getLogger(ProductosEJB.class);
	private Logger loggerAdminUser = LoggerFactory.getLogger(AdminUser.class);
	private Logger loggerLista = LoggerFactory.getLogger(Lista.class);
	private Logger loggerEliminar = LoggerFactory.getLogger(Eliminar.class);
	private Logger loggerAddProducto = LoggerFactory.getLogger(AddProducto.class);
	private Logger loggerDelUser = LoggerFactory.getLogger(DelUser.class);
	private Logger loggerScraperEJB = LoggerFactory.getLogger(ScraperEJB.class);
	private Logger loggerMyBatisUtil = LoggerFactory.getLogger(MyBatisUtil.class);

	/**
	 * Constructor privado
	 */
	private LogSingleton() {
	}

	/**
	 * Obtener instancia
	 * 
	 * @return
	 */
	public static LogSingleton getInstance() {
		return INSTANCE;
	}

	/**
	 * Obtener el logger para Principal
	 * 
	 * @return Logger
	 */
	public Logger getLoggerPrincipal() {
		return loggerPrincipal;
	}

	/**
	 * Obtener el logger para Login
	 * 
	 * @return Logger
	 */
	public Logger getLoggerLogin() {
		return loggerLogin;
	}

	public Logger getLoggerAddProducto() {
		return loggerAddProducto;
	}

	/***
	 * Obtener el logger para Logout
	 * 
	 * @return Logger
	 */
	public Logger getLoggerLogout() {
		return loggerLogout;
	}

	/***
	 * Obtener el logger para Registro
	 * 
	 * @return Logger
	 */
	public Logger getLoggerRegistro() {
		return loggerRegistro;
	}

	/***
	 * Obtener el logger para MyBatisUtil
	 * 
	 * @return Logger
	 */
	public Logger getLoggerMyBatisUtil() {
		return loggerMyBatisUtil;
	}

	/***
	 * Obtener el logger para Grafica
	 * 
	 * @return Logger
	 */
	public Logger getLoggerGrafica() {
		return loggerGrafica;
	}

	/***
	 * Obtener el logger para AdminUser
	 * 
	 * @return Logger
	 */
	public Logger getLoggerAdminUser() {
		return loggerAdminUser;
	}

	/***
	 * Obtener el logger para Lista
	 * 
	 * @return Logger
	 */
	public Logger getLoggerLista() {
		return loggerLista;
	}

	/***
	 * Obtener el logger para DelUser
	 * 
	 * @return Logger
	 */
	public Logger getLoggerDelUser() {
		return loggerDelUser;
	}

	public Logger getLoggerGraficasEJB() {
		return loggerGraficasEJB;
	}

	public Logger getLoggerProductosEJB() {
		return loggerProductosEJB;
	}

	public Logger getLoggerEliminar() {
		return loggerEliminar;
	}

	public Logger getLoggerScraperEJB() {
		return loggerScraperEJB;
	}

}
