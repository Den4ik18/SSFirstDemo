package com.web.employeeservlet;

import com.database.service.EmployeeService;
import com.model.Employee;
import com.validation.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = "/addEmployee")
public class AddEmployeeServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AddEmployeeServlet.class);
    private static final long serialVersionUID = 1L;

    private EmployeeService service = new EmployeeService();

    public AddEmployeeServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/addEmployee.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dateOfBirth"));
        Employee employee = new Employee();
        employee.setName(request.getParameter("name"));
        employee.setLastName(request.getParameter("lastName"));
        employee.setPhoneNumber(request.getParameter("phoneNumber"));
        employee.setSex(request.getParameter("sex"));
        employee.setEmail(request.getParameter("email"));
        employee.setDateOfBirth(dateOfBirth);
       /* if (Validator.validate(employee)) {
            service.add(employee);
            response.sendRedirect("/com_serve_main_war_exploded/employee");
        } else {
            response.sendRedirect("/com_serve_main_war_exploded/addEmployee");
        }*/
        service.add(employee);
        List<Employee> employees = service.getAll();
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("/WEB-INF/views/employee.jsp").forward(request, response);
    }

}
