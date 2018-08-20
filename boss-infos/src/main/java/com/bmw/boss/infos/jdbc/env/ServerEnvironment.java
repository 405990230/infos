package com.bmw.boss.infos.jdbc.env;



import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ServerEnvironment {

	private static String driver = null;
	private static String url = null;
	private static String username = null;
	private static String password = null;

	static {
		Properties properties = new Properties();
		InputStream input = null;

		ClassLoader classLoader = ServerEnvironment.class.getClassLoader();
		try {
			//input = new FileInputStream(classLoader.getResource("properties/jdbc.properties").getFile());
			System.out.println(ServerEnvironment.class.getResource("/properties/jdbc.properties").getPath());
			input = ServerEnvironment.class.getResourceAsStream("/properties/jdbc.properties");
			properties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}

		ServerEnvironment.driver = properties.getProperty("driver");
		ServerEnvironment.url = properties.getProperty("url");
		ServerEnvironment.username = properties.getProperty("username");
		ServerEnvironment.password = properties.getProperty("password");
	}

	public static String getDriver() {
		return driver;
	}

	public static void setDriver(String driver) {
		ServerEnvironment.driver = driver;
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		ServerEnvironment.url = url;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		ServerEnvironment.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		ServerEnvironment.password = password;
	}
}
