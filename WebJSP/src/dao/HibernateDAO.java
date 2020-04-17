package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import domain.Product;
import domain.Seller;

public class HibernateDAO {
	private static HibernateDAO instance;
	private static Session session;
	
	public static void main(String[] args) throws Exception {
		session = getInstance().getSession();
		
//		List<Product> products = returnProductList();
//		for (Product product : products) {
//			System.out.println(product.getName());
//		}
		
		 Seller seller = returnSeller((long) 11);
		 System.out.println(seller.getName());
	}
	
	public static HibernateDAO getInstance() {
	    if (null == instance) {
	      instance = new HibernateDAO();
	      getInstance().getSession();
	    }
	    return instance;
	  }
	
	public static List<Seller> returnSellerList() {
		Query query = session.createQuery("FROM Seller");
		List<Seller> sellers = query.list();		
		return sellers;
	}
	
	public static Seller returnSeller(Long id) {
		Query query = session.createQuery("FROM Seller where id = :id_seller");
		query.setParameter("id_seller", id);
		List<Seller> sellerlist = query.list();
		
		Seller seller = sellerlist.get(0);
		
		return seller;
	}
	
	public static List<Product> returnProductList() {
		Query query = session.createQuery("FROM Product");
		List<Product> products = query.list();	
		return products;
	}
	
	public static void insertObject(Object object) {
		 Transaction transaction = session.beginTransaction();
		 session.save(object);
		 transaction.commit();		
	}
	
	public static void updateObject(Object object) {
		 Transaction transaction = session.beginTransaction();
		 session.update(object);;
		 transaction.commit();
	}
	
	public static void deleteObject(Object object) {
		 Transaction transaction = session.beginTransaction();
		 session.delete(object);
		 transaction.commit();
	}
	
	private Session getSession() {
	    if (null == session) {
	      Configuration configuration = new Configuration();
	      configuration.setProperty(Environment.DRIVER, "org.postgresql.Driver");
	      configuration.setProperty(Environment.URL,
	      "jdbc:postgresql://localhost:5432/Javabase");
	      configuration.setProperty(Environment.USER, "postgres");
	      configuration.setProperty(Environment.PASS, "0147258");
	      configuration.setProperty(Environment.DIALECT, 
	      "org.hibernate.dialect.PostgreSQLDialect");
	      configuration.setProperty(Environment.HBM2DDL_AUTO, "update");
	      configuration.setProperty(Environment.SHOW_SQL, "true");
	      configuration.addAnnotatedClass(Product.class);
	      configuration.addAnnotatedClass(Seller.class);
	      StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
	      serviceRegistryBuilder.applySettings(configuration.getProperties());
	      ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
	      SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	      session = sessionFactory.openSession();
	    }
	    return session;
	  }



}
