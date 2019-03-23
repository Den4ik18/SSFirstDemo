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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/address")
public class AddressServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AddressServlet.class);
    private AddressService service = new AddressService();
    private List<Address> addresses = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("add") != null) {
            req.getRequestDispatcher("/WEB-INF/views/addAddress.jsp").forward(req, resp);
        } else {
            List<Address> address = service.getAll();
            req.setAttribute("address", address);
            req.getRequestDispatcher("/WEB-INF/views/address.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Address address = new Address();
        address.setStreet(req.getParameter("street"));
        address.setCity(req.getParameter("city"));
        address.setZipCode(Integer.parseInt(req.getParameter("zipCode")));
        service.add(address);
        resp.sendRedirect("/com_serve_main_war_exploded/address");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        service.remove(Long.parseLong(req.getParameter("id")));
    }
}
