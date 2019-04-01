package com.web.mainselvlet;

import com.database.service.EmployeeService;
import com.model.Employee;
import com.web.employeeservlet.EmployeeServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/main")
public class MainServlet extends HttpServlet {
    private static final String PATH = "/WEB-INF/views/";
    private static final Logger logger = LogManager.getLogger(MainServlet.class);
    private EmployeeService service = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employees = service.getAll();
        request.setAttribute("employees", employees);
        request.getRequestDispatcher(PATH + "main.jsp").forward(request, response);
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        service.remove(Long.parseLong(request.getParameter("id")));
    }
}
