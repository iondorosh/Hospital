package com.labs.io;

import com.labs.model.Department;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TxtIO {
    public void serialize(Department department, String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(department.toString());
        writer.close();
    }
}
