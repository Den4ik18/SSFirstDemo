package com.parser;

import com.model.Address;
import com.model.Employee;
import com.model.Job;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TxtParser {
    private final static Logger logger = LogManager.getLogger(TxtParser.class);

    public static Employee serealizationTxt(List<String> line) throws ParseException {
        Job job = new Job();
        Employee employeeTxt = new Employee();
        List<Job> jobList = new ArrayList<>();
        for (String word : line) {
            if (word.startsWith("id"))
                employeeTxt.setId(Long.valueOf(word.substring(4)));
            if (word.startsWith("name")) {
                employeeTxt.setName(word.substring(6));
            } else if (word.startsWith("last")) {
                employeeTxt.setLastName(word.substring(10));
            } else if (word.startsWith("address")) {
                Address address = new Address();
                String[] strings = word.split("\\W\\s");
                for (int j = 0; j < strings.length; j++) {
                    if (strings[j].startsWith("id")) {
                        address.setId(Long.valueOf(strings[j + 1]));
                    }
                    if (strings[j].startsWith("city")) {
                        address.setCity(strings[j + 1]);
                    } else if (strings[j].startsWith("street")) {
                        address.setStreet(strings[j + 1]);
                    } else if (strings[j].startsWith("zip")) {
                        address.setZipCode(Integer.parseInt(strings[j + 1]));
                    }
                }
                employeeTxt.setAddress(address);
                logger.info("Address was received");
            } else if (word.startsWith("sex")) {
                employeeTxt.setSex(word.substring(5));
            } else if (word.startsWith("date")) {
                employeeTxt.setDateOfBirth(LocalDate.parse(word.substring(14), DateTimeFormatter.ISO_LOCAL_DATE));
            } else if (word.startsWith("phone")) {
                employeeTxt.setPhoneNumber((word.substring(13)));
            } else if (word.startsWith("email")) {
                employeeTxt.setEmail(word.substring(7));
            } else if (word.startsWith("jobs")) {
                String[] strings = word.split("\\W\\s");
                for (int k = 0; k < strings.length; k++) {
                    if (strings[k].startsWith("id")) {
                        job.setId(Long.valueOf((strings[k + 1])));
                    }
                    if (strings[k].startsWith("company")) {
                        job.setCompanyName(strings[k + 1]);
                    } else if (strings[k].startsWith("start")) {
                        job.setStartDate(LocalDate.parse((strings[k + 1]), DateTimeFormatter.ISO_LOCAL_DATE));
                    } else if (strings[k].startsWith("end")) {
                        job.setEndDate(LocalDate.parse((strings[k + 1]), DateTimeFormatter.ISO_LOCAL_DATE));
                    } else if (strings[k].startsWith("pos")) {
                        job.setPosition(strings[k + 1]);
                    }
                }
                jobList.add(job);
                employeeTxt.setJobs(jobList);
                logger.info("Jobs were received");
            }
        }
        logger.info("Return employee from txt format");
        return employeeTxt;
    }
}
