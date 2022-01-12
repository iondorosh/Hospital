package com.labs.io;

import com.labs.model.Department;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface IO {
    void serialize(Department department, String path) throws JAXBException, IOException;

    Department deserialize(String path) throws JAXBException, FileNotFoundException;
}
