package com.web.employeeservlet;

import com.database.service.EmployeeService;
import com.database.service.JobService;
import com.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/employee")
public class EmployeeServlet extends HttpServlet {
    private static final String PATH = "/WEB-INF/views/";
    private static final Logger logger = LogManager.getLogger(EmployeeServlet.class);
    private EmployeeService service = new EmployeeService();

    public EmployeeServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employees = service.getAll();
        request.setAttribute("employees", employees);
        request.getRequestDispatcher(PATH + "employee.jsp").forward(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        service.remove(Long.parseLong(request.getParameter("id")));
    }

}
