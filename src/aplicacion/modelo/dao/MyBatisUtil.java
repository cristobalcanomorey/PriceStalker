package aplicacion.modelo.dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;

import aplicacion.modelo.LogSingleton;

public class MyBatisUtil {
	private static SqlSessionFactory factory;

	/**
	 * Logger
	 */
	private static final Logger LOG = LogSingleton.getInstance().getLoggerMyBatisUtil();

	/**
	 * Constructor privado
	 */
	private MyBatisUtil() {
	}

	/**
	 * Estático para que sólo se configure MyBatis una vez
	 */
	static {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("mybatis-config.xml");
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
		factory = new SqlSessionFactoryBuilder().build(reader);
	}

	/**
	 * Obtiene una SqlSessionFactory
	 * 
	 * @return La SqlSessionFactory
	 */
	public static SqlSessionFactory getSqlSessionFactory() {
		return factory;
	}

}
