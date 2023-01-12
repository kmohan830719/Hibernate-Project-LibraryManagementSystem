//declaring the package
package com.hiberUtil;

//importing the packages
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.entity.Book;
import com.entity.Login;
import com.entity.Registration;

//connection class to establish connection
public class GetConnection {

	public static SessionFactory sf;

	// method with sessionfactory as return type
	public static SessionFactory con() {

		// object of configuration
		Configuration cfg = new Configuration();

		// putting all the required properties
		Properties pr = new Properties();
		pr.put(Environment.USER, "root");
		pr.put(Environment.PASS, "Kmohan@180");
		pr.put(Environment.URL, "jdbc:mysql://localhost:3306/LibraryManagement");
		pr.put(Environment.HBM2DDL_AUTO, "update");

		// setting the properties
		cfg.setProperties(pr);

		// mapping the entity classes
		cfg.addAnnotatedClass(Book.class);
		cfg.addAnnotatedClass(Registration.class);
		cfg.addAnnotatedClass(Login.class);

		ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();

		sf = cfg.buildSessionFactory(sr);

		return sf;
	}
}
