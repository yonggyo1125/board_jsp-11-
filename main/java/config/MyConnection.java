package config;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import commons.PropertyLibrary;

public class MyConnection {
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		try {
			String env = PropertyLibrary.get("environment");
			String mode = (env != null && env.equals("real"))?"real":"dev";
			Reader reader = Resources.getResourceAsReader("config/mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, mode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSession getSession() {
		return sqlSessionFactory.openSession();
	}
}
