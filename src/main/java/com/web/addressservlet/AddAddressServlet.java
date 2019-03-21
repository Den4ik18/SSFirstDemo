package com.web.addressservlet;

import com.database.service.AddressService;
import com.database.service.EmployeeService;
import com.model.Address;
import com.model.Employee;
import com.web.employeeservlet.AddEmployeeServlet;
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

@WebServlet(urlPatterns = "/addAddress")
public class AddAddressServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AddAddressServlet.class);

    private AddressService service = new AddressService();

    public AddAddressServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/addAddress.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String zipCode = request.getParameter("zipCode");
        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        address.setZipCode(Integer.parseInt(zipCode));

        service.add(address);

        List<Address> addresses = service.getAll();
        request.setAttribute("addresses", addresses);
        request.getRequestDispatcher("/WEB-INF/views/address.jsp").forward(request, response);

//        response.sendRedirect("/com_serve_main_war_exploded/employee");
    }


}
