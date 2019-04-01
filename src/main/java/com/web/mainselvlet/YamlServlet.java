package com.web.mainselvlet;

import com.database.service.EmployeeService;
import com.model.Employee;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("employee", service.getById(Long.valueOf(request.getParameter("id"))));
        employee = service.getById(Long.valueOf((request.getParameter("id"))));
        request.setAttribute("jobs", employee.getJobs());

        File dir = new File(getServletContext().getRealPath("/cv_files"));
        dir.mkdirs();
        File file = new File(dir.getAbsolutePath() + File.separator + employee.getName() + employee.getLastName() + ".yml");
        exportObjectToYaml(new File(file.getAbsolutePath()), employee);
        response.addHeader("Content-Disposition", "attachment;filename=" + file.getName());
        ServletOutputStream out = response.getOutputStream();
        FileInputStream stream = new FileInputStream(file);

        IOUtils.copy(stream, out);
        out.close();
        file.deleteOnExit();
    }

}
