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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/addJob")
public class AddJobServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AddJobServlet.class);
    private JobService service = new JobService();
    private List<Job> jobs;

    public AddJobServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) {
        jobs = service.getAll();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/addJob.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Job job = new Job();
        job.setCompanyName(request.getParameter("companyName"));
        job.setStartDate(LocalDate.parse(request.getParameter("startDate")));
        job.setEndDate(LocalDate.parse(request.getParameter("endDate")));
        job.setPosition(request.getParameter("position"));

        service.add(job);

        List<Job> jobs = service.getAll();
        request.setAttribute("jobs", jobs);
        request.getRequestDispatcher("/WEB-INF/views/job.jsp").forward(request, response);

    }
}
