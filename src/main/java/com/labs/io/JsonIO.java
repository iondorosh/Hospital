package com.labs.io;

import com.google.gson.GsonBuilder;
import com.labs.model.Department;

import javax.xml.bind.JAXBException;
import java.io.*;

public class JsonIO implements IO{
    @Override
    public void serialize(Department department, String path) throws JAXBException, IOException {
        File file = new File(path);
        FileWriter fw = new FileWriter(file);
        fw.write(new GsonBuilder().create().toJson(department));
        fw.close();
    }

    @Override
    public Department deserialize(String path) throws JAXBException, FileNotFoundException {
        return new GsonBuilder().create().fromJson(new FileReader(path), Department.class);
    }
}
