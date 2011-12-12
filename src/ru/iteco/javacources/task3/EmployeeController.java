package ru.iteco.javacources.task3;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.iteco.javacources.task2.Employee;
import ru.iteco.javacources.task2.EmployeeDao;

public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static EmployeeDao dao = new EmployeeDao(); 
       
    public EmployeeController() {
        super();
        dao.init();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			
		response.getWriter().println("SELECT * FROM Employee");
		for(Employee employee:dao.SelectStatemet())
			response.getWriter().println(employee);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action!= null && action.equals("create")){
			String number = request.getParameter("number");
			String name =  request.getParameter("name");
			String jobTitle =  request.getParameter("jobTitle");
			
			if(number!= null && !number.equals("") && name!= null && !name.equals("") && jobTitle!= null && !jobTitle.equals("")){
				Employee employee = new Employee();
				employee.setNumber(Integer.valueOf(number));
				employee.setName(name);
				employee.setJobTitle(jobTitle);
				
				dao.InsertTransaction(employee);
				
				response.sendRedirect("Employee");
			} 
		} else if (action!= null && action.equals("select")){
			String jobTitle =  request.getParameter("jobTitle");
			if(jobTitle!= null && !jobTitle.equals("")){
				response.getWriter().println("SELECT * FROM Employee WHRER JOB_TITLE = '"  + jobTitle + "'");
				for(Employee employee:dao.SelectPreparedStatemet(jobTitle))
					response.getWriter().println(employee);
			}
		}
		
	}

}
