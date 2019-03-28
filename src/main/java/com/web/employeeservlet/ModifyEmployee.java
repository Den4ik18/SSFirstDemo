package com.web.employeeservlet;

import com.database.service.AddressService;
import com.database.service.EmployeeService;
import com.database.service.JobService;
import com.model.Address;
import com.model.Employee;
import com.model.Job;
import com.validation.Validator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@WebServlet(urlPatterns = "/modifyEmployee")
public class ModifyEmployee extends HttpServlet {
    private static final String PATH = "/WEB-INF/views/";
    private static final String REDIRECT = "/com_serve_main_war_exploded/";
    private EmployeeService employeeService;
    private JobService jobService;
    private AddressService addressService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        employeeService = new EmployeeService();
        jobService = new JobService();
        addressService = new AddressService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null) {
            Employee employee = employeeService.getById(Long.valueOf(req.getParameter("id")));
            if (employee != null) {
                req.setAttribute("employee", employee);
                req.setAttribute("jobs", employee.getJobs());
            }
        }
        req.getRequestDispatcher(PATH + "addEmployee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee employee = new Employee();
        employee.setId(Long.valueOf(request.getParameter("id")));
        employee.setName(request.getParameter("name"));
        employee.setLastName(request.getParameter("lastName"));
        employee.setPhoneNumber(request.getParameter("phoneNumber"));
        employee.setSex(request.getParameter("sex"));
        employee.setEmail(request.getParameter("email"));
        employee.setDateOfBirth(LocalDate.parse(request.getParameter("dateOfBirth")));

        Address address = new Address();
        address.setStreet((request.getParameter("street") == null) ? "" : request.getParameter("street"));
        address.setCity((request.getParameter("zipCode") == null) ? "" : request.getParameter("city"));
        address.setZipCode((request.getParameter("zipCode") == null) ? 0 : Integer.parseInt(request.getParameter("zipCode")));
        employee.setAddress(address);

        String[] companyName = request.getParameterValues("companyName");
        String[] start = request.getParameterValues("startDate");
        String[] end = request.getParameterValues("endDate");
        String[] jobPosition = request.getParameterValues("position");

        if (companyName != null) {
            for (int i = 0; i < companyName.length; ++i) {
                Job job = new Job();
                job.setCompanyName(companyName[i]);
                job.setStartDate(LocalDate.parse(start[i]));
                job.setEndDate(LocalDate.parse(end[i]));
                job.setPosition(jobPosition[i]);
                employee.getJobs().add(job);
            }
        }
        if (Validator.validate(employee)) {
            if (employee.getId() != 0) {
                employeeService.update(employee, employee.getId());
            } else {
                employeeService.add(employee);
            }
            List<Employee> all = employeeService.getAll();
            all.sort(Comparator.comparing(Employee::getId).reversed());
            for (Job place : employee.getJobs()) {
                jobService.addObjectForEmployee(place, all.get(0).getId());
            }
            addressService.addObjectForEmployee(employee.getAddress(), all.get(0).getId());
            response.sendRedirect(REDIRECT + "employee");
        } else {
            request.setAttribute("error", "An validation error");
            request.setAttribute("employee", employee);
            request.setAttribute("jobs", employee.getJobs());
            request.getRequestDispatcher(PATH + "addEmployee.jsp").forward(request, response);
        }

    }
}
