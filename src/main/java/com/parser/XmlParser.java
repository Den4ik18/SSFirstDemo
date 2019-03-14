package com.parser;

import com.fasterxml.jackson.xml.XmlMapper;
import com.model.Employee;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class XmlParser {

    public static Employee getEmployeeFromXmlInputStream(InputStream inputStream) {
        JAXBContext jaxbContext;
        Employee employeeXml = null;
        try {
            jaxbContext = JAXBContext.newInstance(Employee.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            employeeXml = (Employee) unmarshaller.unmarshal(inputStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return employeeXml;
    }

    public static void getXmlFileFromEmployee(Employee employee) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Employee.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(employee, new File("src/main/resources/xmlFormat.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    public static Employee getEmployeeWithXmlFile(File file) {
        JAXBContext jaxbContext;
        Employee employeeFromXmlFile = null;
        try {
            jaxbContext = JAXBContext.newInstance(Employee.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            employeeFromXmlFile = (Employee) unmarshaller.unmarshal(file);
        } catch (JAXBException ex) {
            ex.printStackTrace();
            ex.getCause();
        }
        return employeeFromXmlFile;
    }

    public void exportXML(String fileName, Employee employee) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File(fileName), employee);
        File file = new File(fileName);//maybe dont need
    }

    public Employee importXML(String line) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.readValue(line, Employee.class);
    }

}
