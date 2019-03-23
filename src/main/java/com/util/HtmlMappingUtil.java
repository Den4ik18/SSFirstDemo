package com.util;

import com.model.Address;
import com.model.Employee;
import com.model.Job;

import java.util.List;

public class HtmlMappingUtil {

    private static String mapJobToTable(Job job){
        StringBuilder result = new StringBuilder();
        result.append("<tr>")
                .append("<td>").append(job.getId()).append("</td>")
                .append("<td>").append(job.getStartDate()).append("</td>")
                .append("<td>").append(job.getEndDate()).append("</td>")
                .append("<td>").append(job.getPosition()).append("</td>")
                .append("</tr>");
        return result.toString();
    }

    public static String mapJobToTable (List<Job> jobs){
        StringBuilder result = new StringBuilder();
        jobs.forEach(x -> result.append(mapJobToTable(x)));
        return result.toString();
    }

    private static String mapAddressToTable(Address address){
        StringBuilder result = new StringBuilder();
        result.append("<tr>")
                //.append("<td>").append(address.getId()).append("</td>")
                .append("<td>").append(address.getStreet()).append("</td>")
                .append("<td>").append(address.getCity()).append("</td>")
                .append("<td>").append(address.getZipCode()).append("</td>")
                .append("<td><button onClick = handleDelete(").append(address.getId()).append(")>Delete</button></td>")
                .append("</tr>");
        return result.toString();
    }

    public static String mapAddressToTable(List<Address> addresses){
        StringBuilder result = new StringBuilder();
        addresses.forEach(x -> result.append(mapAddressToTable(x)));
        return result.toString();
    }

    private static String mapPersonToTable(Employee employee){
        StringBuilder result = new StringBuilder();
        result.append("<tr>")
                .append("<td>").append(employee.getId()).append("</td>")
                .append("<td>").append(employee.getName()).append("</td>")
                .append("<td>").append(employee.getLastName()).append("</td>")
                .append("<td>").append(employee.getPhoneNumber()).append("</td>")
                .append("<td>").append(employee.getSex()).append("</td>")
                .append("<td>").append(employee.getEmail()).append("</td>")
                .append("<td>").append(employee.getSex()).append("</td>")
                .append("<td><button onClick = handleDelete(").append(employee.getId()).append(")>Delete</button></td>")
                .append("</tr>");
        return result.toString();
    }

    public static String mapPersonToTable(List<Employee> employees){
        StringBuilder result = new StringBuilder();
        employees.forEach(x -> result.append(mapPersonToTable(x)));
        return result.toString();
    }
}
