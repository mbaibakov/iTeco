package ru.iteco.javacources.task3;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Calculator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Calculator() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s1 = request.getParameter("summand1");
		String s2 = request.getParameter("summand2");
		
		if (s1 != null && !s1.equals("") && s2 != null && !s2.equals("")){
			Double result =  Double.valueOf(s1) + Double.valueOf(s2);
			response.getWriter().print(result);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s1 = request.getParameter("summand1");
		String s2 = request.getParameter("summand2");
		
		if (s1 != null && !s1.equals("") && s2 != null && !s2.equals("")){
			Double result =  Double.valueOf(s1) + Double.valueOf(s2);
			response.getWriter().print(result);
			HttpSession session = request.getSession(true);
			session.setAttribute("result", result);
			response.sendRedirect("result.jsp");			
		}
	}

}
