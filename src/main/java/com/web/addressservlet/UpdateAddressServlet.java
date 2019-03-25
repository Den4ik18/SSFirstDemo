package com.web.addressservlet;

import com.database.service.AddressService;
import com.model.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/update-address")
public class UpdateAddressServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(UpdateAddressServlet.class);
    private Long id;
    private AddressService service = new AddressService();

    public UpdateAddressServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("address", service.getById(Long.valueOf(request.getParameter("id"))));
        request.getRequestDispatcher("/WEB-INF/views/updateAddress.jsp").forward(request, response);
        id = Long.valueOf(request.getParameter("id"));
        //Address address = service.getById(id);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Address newAddress = new Address();
        newAddress.setId(id);
        newAddress.setStreet(request.getParameter("street"));
        newAddress.setCity(request.getParameter("city"));
        newAddress.setZipCode(Integer.parseInt(request.getParameter("zipCode")));
        service.update(newAddress, id);
        response.sendRedirect("/com_serve_main_war_exploded/address");
    }
}
