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
import java.util.List;

@WebServlet(urlPatterns = "/employee")
public class EmployeeServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(EmployeeServlet.class);
    private EmployeeService service = new EmployeeService();

    public EmployeeServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("add") != null) {
            request.getRequestDispatcher("/WEB-INF/views/addEmployee.jsp").forward(request, response);
        } else {
            List<Employee> employees = service.getAll();
            request.setAttribute("employees", employees);
            request.getRequestDispatcher("/WEB-INF/views/employee.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Employee employee = new Employee();
        employee.setName(request.getParameter("name"));
        employee.setLastName(request.getParameter("lastName"));
        employee.setPhoneNumber(request.getParameter("phoneNumber"));
        employee.setSex(request.getParameter("sex"));
        employee.setEmail(request.getParameter("email"));
        employee.setDateOfBirth(LocalDate.parse(request.getParameter("dateOfBirth")));
       /* if (Validator.validate(employee)) {
            service.add(employee);
            response.sendRedirect("/com_serve_main_war_exploded/employee");
        } else {
            response.sendRedirect("/com_serve_main_war_exploded/addEmployee");
        }*/
        service.add(employee);
        response.sendRedirect("/com_serve_main_war_exploded/employee");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        service.remove(Long.parseLong(request.getParameter("id")));
    }

}
