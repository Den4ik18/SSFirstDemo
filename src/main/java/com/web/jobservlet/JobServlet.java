package com.web.jobservlet;

import com.database.service.JobService;
import com.model.Job;
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

@WebServlet(urlPatterns = "/job")
public class JobServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(JobServlet.class);

    private JobService service = new JobService();
    private List<Job> jobs = new ArrayList<>();

    public JobServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) {
        jobs = service.getAll();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("jobs", jobs);
        request.getRequestDispatcher("/WEB-INF/views/job.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
