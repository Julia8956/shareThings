package com.kh.st.rental.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.st.rental.model.service.RentalService;


/**
 * @author uukk
 *
 */
@WebServlet("/selectRantalList.pd")
public class SelectRentalListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SelectRentalListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		ArrayList<HashMap<String,Object>> list = new RentalService().selectRentalList();
		

		String page ="";
		if(list != null) {
			page = "views/admin/rental/rentalList.jsp";
			request.setAttribute("list", list);
		}
		request.getRequestDispatcher(page).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
