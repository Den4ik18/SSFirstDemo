package com.web.jobservlet;

import com.database.service.JobService;
import com.model.Job;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/update-job")
public class UpdateJobServlet extends HttpServlet {
    private static final String PATH = "/WEB-INF/views/";
    private static final String REDIRECT = "/com_serve_main_war_exploded/";
    private static final Logger logger = LogManager.getLogger(UpdateJobServlet.class);
    private Long id;
    private JobService service = new JobService();

    public UpdateJobServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("job", service.getById(Long.valueOf(request.getParameter("id"))));
        request.getRequestDispatcher(PATH + "updateJob.jsp").forward(request, response);
        id = Long.valueOf(request.getParameter("id"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Job newJob = new Job();
        newJob.setId(id);
        newJob.setCompanyName(request.getParameter("companyName"));
        newJob.setEndDate(LocalDate.parse(request.getParameter("endDate")));
        newJob.setStartDate(LocalDate.parse(request.getParameter("startDate")));
        newJob.setPosition(request.getParameter("position"));
        service.update(newJob, id);
        response.sendRedirect(REDIRECT + "job");
    }
}
