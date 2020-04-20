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

public class DeleteServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteServ() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter printWriter = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		printWriter.write("Delete id= " + request.getParameter("id"));
		
		if (request.getParameter("table").equals("seller") ) {
			Seller seller = HibernateDAO.getInstance().returnSeller((long) id);
			HibernateDAO.getInstance().deleteObject(seller);
		}else {
			Product product = HibernateDAO.getInstance().returnProduct((long) id);
			HibernateDAO.getInstance().deleteObject(product);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
