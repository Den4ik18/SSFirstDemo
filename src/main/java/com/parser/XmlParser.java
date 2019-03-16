package com.parser;

import com.fasterxml.jackson.xml.XmlMapper;
import com.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class XmlParser {
    private static final Logger logger = LogManager.getLogger(XmlParser.class);

    public static Employee getEmployeeFromXmlInputStream(InputStream inputStream) {
        JAXBContext jaxbContext;
        Employee employeeXml = null;
        try {
            jaxbContext = JAXBContext.newInstance(Employee.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            employeeXml = (Employee) unmarshaller.unmarshal(inputStream);
            logger.info("Employee was successful written");
        } catch (JAXBException e) {
            logger.error(e.getMessage());
        }
        logger.info("Return employee from input stream");
        return employeeXml;
    }

    public static void writeToXmlFileFromEmployee(Employee employee) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Employee.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(employee, new File("src/main/resources/xmlFormat.xml"));
            logger.info("File was written");
        } catch (JAXBException e) {
            logger.error(e.getMessage());
        }
    }

    public static Employee getEmployeeWithXmlFile(File file) {
        JAXBContext jaxbContext;
        Employee employeeFromXmlFile = null;
        try {
            jaxbContext = JAXBContext.newInstance(Employee.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            employeeFromXmlFile = (Employee) unmarshaller.unmarshal(file);
            logger.info("Employee was received");
        } catch (JAXBException ex) {
            logger.error(ex.getMessage());
        }
        logger.info("Return employee from xml file");
        return employeeFromXmlFile;
    }

    public void exportXML(String fileName, Employee employee) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File(fileName), employee);
        logger.info("Xml file was written");
        File file = new File(fileName);//maybe dont need
    }

    public Employee importXML(String line) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.readValue(line, Employee.class);
    }

}
