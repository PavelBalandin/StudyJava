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

public class UpdateServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateServ() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter printWriter = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		printWriter.write("Update id= " + request.getParameter("id"));
		printWriter.write("Update name= " + request.getParameter("name"));
		printWriter.write("Update price= " + request.getParameter("price"));
		printWriter.write("Update seller= " + request.getParameter("id_seller"));
		
		Product product = HibernateDAO.getInstance().returnProduct((long) id);
		product.setName(request.getParameter("name"));
		product.setPrice(Integer.parseInt(request.getParameter("prise")));
		product.setSeller(HibernateDAO.getInstance().returnSeller((long) Integer.parseInt(request.getParameter("id_seller"))));
			
		HibernateDAO.getInstance().updateObject(product);
	}

}