package com.labs.io;

import com.labs.model.Department;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class XmlIO implements IO{
    @Override
    public void serialize(Department department, String path) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Department.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(department, new File(path));
    }

    @Override
    public Department deserialize(String path) throws JAXBException, FileNotFoundException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Department.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Department department = (Department) jaxbUnmarshaller.unmarshal(new File(path));
        return department;
    }
}
