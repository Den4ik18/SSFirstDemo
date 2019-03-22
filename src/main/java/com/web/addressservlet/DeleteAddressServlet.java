package com.web.addressservlet;

import com.database.service.AddressService;
import com.database.service.EmployeeService;
import com.web.employeeservlet.DeleteEmployeeServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete-address")
public class DeleteAddressServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(DeleteAddressServlet.class);
    private Long id;
    private AddressService service = new AddressService();

    public DeleteAddressServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("addresses", service.getAll());
        request.getRequestDispatcher("/WEB-INF/views/address.jsp").forward(request, response);
        id = Long.valueOf(request.getParameter("id"));
        doDelete(request, response);
        response.sendRedirect("/com_serve_main_war_exploded/address");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        service.remove(id);
    }

}
