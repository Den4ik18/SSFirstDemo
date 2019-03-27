package com.web.employeeservlet;

import com.database.service.EmployeeService;
import com.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@WebServlet(urlPatterns = "/fullinfo-employee")
public class AllInfoAboutEmployeeServlet extends HttpServlet {
    private static final String PATH = "/WEB-INF/views/";
    private static final Logger logger = LogManager.getLogger(AllInfoAboutEmployeeServlet.class);
    private Long id;
    private Employee employee;
    private EmployeeService service;

    @Override
    public void init(ServletConfig config) {
        service = new EmployeeService();
    }

    public AllInfoAboutEmployeeServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("employee", service.getById(Long.valueOf(request.getParameter("id"))));
        employee = service.getById(Long.valueOf((request.getParameter("id"))));
        request.setAttribute("jobs", employee.getJobs());
        request.getRequestDispatcher(PATH + "fullEmployeeInfo.jsp").forward(request, response);
        id = Long.valueOf(request.getParameter("id"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf;charset=UTF-8");
        response.addHeader("Content-Disposition", "inline; filename=" + "employee.pdf");
        ServletOutputStream out = response.getOutputStream();
        ByteArrayOutputStream baos = GeneratePdf.getPdfFile(employee);
        System.out.println(employee);
        baos.writeTo(out);
    }
}
