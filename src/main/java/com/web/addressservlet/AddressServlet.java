package com.web.addressservlet;

import com.database.service.AddressService;
import com.model.Address;
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

@WebServlet(urlPatterns = "/address")
public class AddressServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AddressServlet.class);
    private AddressService service = new AddressService();
    private List<Address> addresses = new ArrayList<>();

    public AddressServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) {
        addresses = service.getAll();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("addresses", addresses);
        request.getRequestDispatcher("/WEB-INF/views/address.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
