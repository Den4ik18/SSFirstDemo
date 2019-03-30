package com.web.mainselvlet;

import com.database.service.EmployeeService;
import com.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import static com.parser.YamlParser.exportObjectToYaml;

@WebServlet(urlPatterns = "/convertToYaml")
public class YamlServlet extends HttpServlet {
    private static final String PATH = "/WEB-INF/views/";
    private EmployeeService service;
    private Employee employee;

    @Override
    public void init() {
        service = new EmployeeService();
        employee = new Employee();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("employee", service.getById(Long.valueOf(request.getParameter("id"))));
        employee = service.getById(Long.valueOf((request.getParameter("id"))));
        request.setAttribute("jobs", employee.getJobs());
        request.getRequestDispatcher(PATH + "fullEmployeeInfo.jsp").forward(request, response);
        Long id = Long.valueOf(request.getParameter("id"));
        exportObjectToYaml(new File("P:\\Java Project\\JavaProject\\src\\main\\resources\\employee.yml"), service.getById(id));
    }
}
