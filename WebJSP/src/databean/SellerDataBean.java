package databean;

import java.util.List;

import domain.Product;
import domain.Seller;
import dao.HibernateDAO;

public class SellerDataBean {

	private List<Seller> sellerlist = null;

	public SellerDataBean() {
		sellerlist = HibernateDAO.getInstance().returnSellerList();
	}

	public List<Seller> getSellerlist() {
		return sellerlist;
	}

	public void setSellerlist(List<Seller> sellerlist) {
		this.sellerlist = sellerlist;
	}
	
	public void delete(int id) {
		Seller seller = new Seller();
		seller.setId((long) id);
		HibernateDAO.getInstance().deleteObject(seller);
	}
	
	public void insert(String name, String description) {
		Seller seller = new Seller();
		seller.setId((long) 1);
		seller.setName(name);
		seller.setDescription(description);	
		HibernateDAO.getInstance().insertObject(seller);
	}
	
}
