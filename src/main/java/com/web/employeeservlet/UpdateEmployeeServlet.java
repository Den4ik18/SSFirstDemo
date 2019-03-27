package com.web.employeeservlet;

import com.database.service.EmployeeService;
import com.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/update-employee")
public class UpdateEmployeeServlet extends HttpServlet {
    private static final String PATH = "/WEB-INF/views/";
    private static final String REDIRECT = "/com_serve_main_war_exploded/";
    private static final Logger logger = LogManager.getLogger(UpdateEmployeeServlet.class);
    private Long id;
    private EmployeeService service = new EmployeeService();

    public UpdateEmployeeServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("employee", service.getById(Long.valueOf(request.getParameter("id"))));
        request.getRequestDispatcher(PATH + "updateEmployee.jsp").forward(request, response);
        id = Long.valueOf(request.getParameter("id"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Employee newEmployee = new Employee();
        newEmployee.setId(id);
        newEmployee.setName(request.getParameter("name"));
        newEmployee.setLastName(request.getParameter("lastName"));
        newEmployee.setEmail(request.getParameter("email"));
        newEmployee.setDateOfBirth(LocalDate.parse(request.getParameter("dateOfBirth")));
        newEmployee.setSex(request.getParameter("sex"));
        newEmployee.setPhoneNumber(request.getParameter("phoneNumber"));
        service.update(newEmployee, id);
        response.sendRedirect(REDIRECT + "employee");
    }
}
