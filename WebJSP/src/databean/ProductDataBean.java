package databean;

import java.util.List;

import dao.HibernateDAO;
import domain.Product;
import domain.Seller;

public class ProductDataBean {
	private List<Product> productlist = null;
	
	public ProductDataBean() {
		productlist = HibernateDAO.getInstance().returnProductList();
	}
	
	public void update(int id, String name, double price, Long id_seller) {
		Product product = new Product();
		product.setId((long) id);
		product.setName(name);
		product.setPrice(price);
		product.setSeller(HibernateDAO.getInstance().returnSeller(id_seller));
		
		HibernateDAO.getInstance().updateObject(product);
	}
	
	public void delete(int id) {
		Product product = new Product();
		product.setId((long) id);
		HibernateDAO.getInstance().deleteObject(product);
	}
	
	public void insert(String name, double price, Long id_seller) {
		Product product = new Product();
		product.setId((long) 1);
		product.setName(name);
		product.setPrice(price);
		product.setSeller(HibernateDAO.getInstance().returnSeller(id_seller));
		
		HibernateDAO.getInstance().insertObject(product);
	}
	
	
	public List<Product> getProductlist() {
		return productlist;
	}

	public void setProductlist(List<Product> productlist) {
		this.productlist = productlist;
	}
	
	

}
