package aplicacion.modelo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aplicacion.controlador.AdminUser;
import aplicacion.controlador.DelUser;
import aplicacion.controlador.Grafica;
import aplicacion.controlador.Lista;
import aplicacion.controlador.Login;
import aplicacion.controlador.Logout;
import aplicacion.controlador.Principal;
import aplicacion.controlador.Registro;
import aplicacion.modelo.dao.MyBatisUtil;
import aplicacion.modelo.ejb.GraficasEJB;

public class LogSingleton {
	private static final LogSingleton INSTANCE = new LogSingleton();
	private Logger loggerPrincipal = LoggerFactory.getLogger(Principal.class);
	private Logger loggerLogin = LoggerFactory.getLogger(Login.class);
	private Logger loggerLogout = LoggerFactory.getLogger(Logout.class);
	private Logger loggerRegistro = LoggerFactory.getLogger(Registro.class);
	private Logger loggerGrafica = LoggerFactory.getLogger(Grafica.class);
	private Logger loggerGraficasEJB = LoggerFactory.getLogger(GraficasEJB.class);
	private Logger loggerAdminUser = LoggerFactory.getLogger(AdminUser.class);
	private Logger loggerLista = LoggerFactory.getLogger(Lista.class);
	private Logger loggerDelUser = LoggerFactory.getLogger(DelUser.class);
//	private Logger loggerMailEJB = LoggerFactory.getLogger(MailEJB.class);
//	private Logger loggerValidacion = LoggerFactory.getLogger(Validacion.class);
//	private Logger loggerBaja = LoggerFactory.getLogger(Baja.class);
//	private Logger loggerTimerEJB = LoggerFactory.getLogger(TimerEJB.class);
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

	/***
	 * Obtener el logger para Historial
	 * 
	 * @return Logger
	 */
//	public Logger getLoggerHistorial() {
//		return loggerHistorial;
//	}

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
	 * Obtener el logger para MailEJB
	 * 
	 * @return Logger
	 */
//	public Logger getLoggerMailEJB() {
//		return loggerMailEJB;
//	}

	/***
	 * Obtener el logger para Validacion
	 * 
	 * @return Logger
	 */
//	public Logger getLoggerValidacion() {
//		return loggerValidacion;
//	}

	/***
	 * Obtener el logger para Baja
	 * 
	 * @return Logger
	 */
//	public Logger getLoggerBaja() {
//		return loggerBaja;
//	}

	/***
	 * Obtener el logger para TimerSingleton
	 * 
	 * @return Logger
	 */
//	public Logger getLoggerTimerSingleton() {
//		return loggerTimerEJB;
//	}

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

}
