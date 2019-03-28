package com.web.employeeservlet;

import com.database.service.EmployeeService;
import com.model.Employee;
import com.parser.YamlParser;
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
import java.io.File;
import java.io.IOException;

import static com.parser.YamlParser.exportObjectToYaml;

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
        //if (request.getParameter("yaml") == null) {
        File file = new File("employee.yml");
        exportObjectToYaml(file, service.getById(id));

        //}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if ("submit".equals(action)) {
            response.setContentType("application/pdf;charset=UTF-8");
            response.addHeader("Content-Disposition", "inline; filename=" + "employee.pdf");
            ServletOutputStream out = response.getOutputStream();
            ByteArrayOutputStream baos = GeneratePdf.getPdfFile(employee);
            baos.writeTo(out);
        }/*else {
            exportObjectToYaml(new File("src/main/resources/employee.yml"),employee);
        }*/
        /*System.out.println(request.getParameter("yaml"));
        if (request.getParameter("yaml") != null) {
            System.out.println("else");
        }*/


    }
}
