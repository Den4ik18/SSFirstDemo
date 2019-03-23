package com.web.addressservlet;

import com.database.service.AddressService;
import com.model.Address;
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
import java.util.List;

@WebServlet(urlPatterns = "/update-address")
public class UpdateAddressServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(UpdateAddressServlet.class);
    Long id;
    private AddressService service = new AddressService();
    private Address address = new Address();

    public UpdateAddressServlet() {
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
        /*id = Long.valueOf(request.getParameter("id"));
        address = service.getById(id);
        request.setAttribute("street",address.getStreet());
        request.setAttribute("city",address.getCity());
        request.setAttribute("zipCode",address.getZipCode());*/
        String action = request.getParameter("action");
        if ("submit".equals(action)) {
            address.setId(Long.valueOf(request.getParameter("id")));
            address.setStreet(request.getParameter("street"));
            address.setCity(request.getParameter("city"));
            address.setZipCode(Integer.parseInt(request.getParameter("zipCode")));
        }
        request.setAttribute("address", address);
        request.getRequestDispatcher("/WEB-INF/views/address.jsp").forward(request, response);



        /*service.update(address,id);

        List<Address> addresses = service.getAll();
        request.setAttribute("addresses", addresses);
        request.getRequestDispatcher("/WEB-INF/views/address.jsp").forward(request, response);
*/
//        response.sendRedirect("/com_serve_main_war_exploded/employee");
    }
}
