package com.web.mainselvlet;

import com.database.service.AddressService;
import com.database.service.EmployeeService;
import com.database.service.JobService;
import com.model.Employee;
import com.model.Job;
import com.service.ParserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@WebServlet("/uploadFile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class UploadFileServlet extends HttpServlet {
    private static final String SAVE_DIRECTORY = "file";
    private static final String PATH = "/WEB-INF/views/";
    private static final String REDIRECT = "/com_serve_main_war_exploded/";
    private EmployeeService employeeService;
    private ParserService parserService;
    private JobService jobService;
    private AddressService addressService;



    @Override
    public void init(ServletConfig config) {
        employeeService = new EmployeeService();
        parserService = new ParserService();
        jobService = new JobService();
        addressService = new AddressService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(PATH + "main.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Employee employee = new Employee();
            List<Employee> employees = new ArrayList<>();
            String appPath = request.getServletContext().getRealPath("");
            appPath = appPath.replace('\\', '/');
            String fullSavePath;
            if (appPath.endsWith("/")) {
                fullSavePath = appPath+ SAVE_DIRECTORY;
            } else {
                fullSavePath = appPath+"/" + SAVE_DIRECTORY;
            }
            System.out.println(fullSavePath);
            File fileSaveDir = new File(fullSavePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }
            for (Part part : request.getParts()) {
                String fileName = extractFileName(part);
                if (fileName != null && fileName.length() > 0) {
                    String filePath = fullSavePath + File.separator + fileName;
                    if (fileName.contains(".txt")) {
                        employees = parserService.searchEmployeeInSingleFile(new File(filePath));
                        for (Employee emp : employees) {
                            employeeService.add(emp);
                            List<Employee> all = employeeService.getAll();
                            all.sort(Comparator.comparing(Employee::getId).reversed());
                            for (Job place : emp.getJobs()) {
                                jobService.addObjectForEmployee(place, all.get(0).getId());
                            }
                            addressService.addObjectForEmployee(emp.getAddress(), all.get(0).getId());
                        }
                    } else {
                        employee = parserService.searchOneEmployeeInSingleFile(filePath);
                        employeeService.add(employee);
                        List<Employee> all = employeeService.getAll();
                        all.sort(Comparator.comparing(Employee::getId).reversed());
                        for (Job place : employee.getJobs()) {
                            jobService.addObjectForEmployee(place, all.get(0).getId());
                        }
                        addressService.addObjectForEmployee(employee.getAddress(), all.get(0).getId());
                    }
                }
            }
            response.sendRedirect(REDIRECT + "employee");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(PATH + "main.jsp");
            dispatcher.forward(request, response);
        }
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');
                return clientFileName.substring(i + 1);
            }
        }
        return null;
    }
}
