package com.web.jobservlet;

import com.database.service.JobService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete-job")
public class DeleteJobServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(DeleteJobServlet.class);
    private Long id;
    private JobService service = new JobService();

    public DeleteJobServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("jobs", service.getAll());
        request.getRequestDispatcher("/WEB-INF/views/job.jsp").forward(request, response);
        id = Long.valueOf(request.getParameter("id"));
        doDelete(request, response);
        response.sendRedirect("/com_serve_main_war_exploded/job");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        service.remove(id);
    }
}
