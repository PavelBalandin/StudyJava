package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HibernateDAO;
import domain.Product;
import domain.Seller;


public class InsertServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertServ() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter printWriter = response.getWriter();
		printWriter.write("Insert name= " + request.getParameter("name"));
		printWriter.write("Insert price= " + request.getParameter("price"));
		printWriter.write("Insert seller= " + request.getParameter("id_seller"));
		
		if (request.getParameter("operation").equals("insertSeller") ) {
			Seller seller = new Seller();
			seller.setName(request.getParameter("name"));
			seller.setDescription(request.getParameter("description"));
			
			HibernateDAO.getInstance().insertObject(seller);
			
			
			
		}else {
			Product product = new Product();
			product.setName(request.getParameter("name"));
			product.setPrice(Integer.parseInt(request.getParameter("price")));
			product.setSeller(HibernateDAO.getInstance().returnSeller((long) Integer.parseInt(request.getParameter("id_seller"))));
				
			HibernateDAO.getInstance().insertObject(product);
		}
	}

}
