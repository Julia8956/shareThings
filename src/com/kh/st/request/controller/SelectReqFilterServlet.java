package com.kh.st.request.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.st.request.model.service.ReqService;

/**
 * Servlet implementation class SelectReqFilterServlet
 */
@WebServlet("/selectReqFilter.bo")
public class SelectReqFilterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectReqFilterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String okStatus = request.getParameter("okStatus");
		String details = request.getParameter("details");
		String filterContent = request.getParameter("filterContent");
		String startD = request.getParameter("startD");
		String endD = request.getParameter("endD");
		
		Date startDate = null;
		Date endDate = null;
		if(startD != "" && endD != "") {
			startDate = Date.valueOf(startD);
			endDate = Date.valueOf(endD);
		} else {
			startDate = null;
			endDate = null;
		}
		
		//상세조건과 상세내용 묶기
		HashMap<String, Object> detailsContent = new HashMap<String, Object>();
		detailsContent.put("details",details);
		detailsContent.put("filterContent", filterContent);
		
		//각 조건 묶기
		HashMap<String, Object> condition = new HashMap<String, Object>();
		
		condition.put("okStatus", okStatus);
		condition.put("detailsContent", detailsContent);
		condition.put("startD", startD);
		condition.put("endD", endD);
		
		System.out.println(condition.get("okStatus"));
		System.out.println(condition.get("detailsContent"));
		System.out.println(condition.get("startD"));
		System.out.println(condition.get("endD"));
		
		ArrayList<HashMap<String, Object>> list = new ReqService().selectReqFilter(condition);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		new Gson().toJson(list, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}