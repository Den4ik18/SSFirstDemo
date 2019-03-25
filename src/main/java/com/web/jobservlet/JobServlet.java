package com.web.jobservlet;

import com.database.dao.EmployeeDao;
import com.database.service.JobService;
import com.model.Employee;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/job")
public class JobServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(JobServlet.class);
    private JobService service = new JobService();
    private EmployeeDao dao = new EmployeeDao();
    private List<String> employeeName = new ArrayList<>();

    public JobServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("add") != null) {
            request.getRequestDispatcher("/WEB-INF/views/addJob.jsp").forward(request, response);
        } else {
            List<Job> jobs = service.getAll();
            for (Job j : jobs) {
                employeeName.add(dao.getEmployeeNameByJobId(j.getId()));
                System.out.println(dao.getEmployeeNameByJobId(j.getId()));
            }
            request.setAttribute("name",employeeName);
            request.setAttribute("jobs", jobs);
            request.getRequestDispatcher("/WEB-INF/views/job.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Job job = new Job();
        job.setCompanyName(request.getParameter("companyName"));
        job.setStartDate(LocalDate.parse(request.getParameter("startDate")));
        job.setEndDate(LocalDate.parse(request.getParameter("endDate")));
        job.setPosition(request.getParameter("position"));
        service.add(job);
        response.sendRedirect("/com_serve_main_war_exploded/job");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        service.remove(Long.parseLong(request.getParameter("id")));
    }
}
