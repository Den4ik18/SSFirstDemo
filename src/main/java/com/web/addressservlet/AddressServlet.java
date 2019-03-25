package com.web.addressservlet;

import com.database.dao.AddressDao;
import com.database.dao.EmployeeDao;
import com.database.service.AddressService;
import com.database.service.EmployeeService;
import com.model.Address;
import com.model.Employee;
import com.model.Job;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/address")
public class AddressServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AddressServlet.class);
    private AddressService service = new AddressService();
    private EmployeeDao dao = new EmployeeDao();
    private List<Employee> employeesList = new ArrayList<>();
    private List<String> employeeName = new ArrayList<>();

    public AddressServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("add") != null) {
            request.getRequestDispatcher("/WEB-INF/views/addAddress.jsp").forward(request, response);
        } else {
            List<Address> address = service.getAll();
            for (Address a : address) {
                employeeName.add(dao.getEmployeeNameByJobId(a.getId()));
            }
            request.setAttribute("name",employeeName);
            request.setAttribute("address", address);
            request.getRequestDispatcher("/WEB-INF/views/address.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Address address = new Address();
        address.setStreet(request.getParameter("street"));
        address.setCity(request.getParameter("city"));
        address.setZipCode(Integer.parseInt(request.getParameter("zipCode")));
        service.add(address);
        response.sendRedirect("/com_serve_main_war_exploded/address");
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        service.remove(Long.parseLong(req.getParameter("id")));
    }


}
