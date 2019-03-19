package com.web;

import com.database.dao.EmployeeDao;
import com.database.service.EmployeeService;
import com.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/employee")
public class EmployeeServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(EmployeeServlet.class);
    private static final long serialVersionUID = 1L;


    private Employee employee;
    private EmployeeDao dao = new EmployeeDao();
    private EmployeeService service = new EmployeeService();
    private List<Employee> employees = new ArrayList<>();


   /* public Employee getEmployee() {
        return employeeService.getById(3L);
    }*/

    public EmployeeServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) {
        employees = service.getAll();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*  *//* RequestDispatcher dispatcher = this.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/employee.jsp");
                dispatcher.forward(request, response);*/
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("/WEB-INF/views/employee.jsp").forward(request, response);


        // String action = request.getParameter("action");
        /*request.setAttribute("employee", employee);
        request.getRequestDispatcher("/WEB-INF/views/employee.jsp").forward(request, response);*/

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
