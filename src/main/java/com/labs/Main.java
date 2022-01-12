package com.labs;

import com.labs.enums.Position;
import com.labs.io.JsonIO;
import com.labs.io.TxtIO;
import com.labs.io.XmlIO;
import com.labs.model.Department;
import com.labs.model.Doctor;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Department department = new Department.DepartmentBuilder()
                .setDepartmentName("Department1")
                .setPersonal(new LinkedList<Doctor>(Arrays.asList(
                        new Doctor.DoctorBuilder()
                                .setFirstName("FN1")
                                .setLastName("LN1")
                                .setPosition(Position.CEO)
                                .build(),
                        new Doctor.DoctorBuilder()
                                .setFirstName("FN2")
                                .setLastName("LN2")
                                .setPosition(Position.DIRECTOR)
                                .build(),
                        new Doctor.DoctorBuilder()
                                .setFirstName("FN3")
                                .setLastName("LN3")
                                .setPosition(Position.MANAGER)
                                .build()
                )))
                .build();

        System.out.println("Department created manually");
        System.out.println(department.toString());

        try {
            new JsonIO().serialize(department, "department.json");
            new XmlIO().serialize(department, "department.xml");
            new TxtIO().serialize(department, "department.txt");

            System.out.println("\nDepartment from JSON:");
            System.out.println(new JsonIO().deserialize("department.json").toString());

            System.out.println("\nDepartment from XML:");
            System.out.println(new XmlIO().deserialize("department.xml").toString());

        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }

        System.out.println("List of CEO: " + department.getPersonalByPosition(Position.CEO));
        System.out.println("List of CEO using streams: " + department.getPersonalByPositionUsingStreams(Position.CEO));

        System.out.println("Personal with name \"FN1\": " + department.getPersonalByFirstName("FN1"));
        System.out.println("Personal with name \"FN1\" using streams: " + department.getPersonalByFirstNameUsingStreams("FN1"));

        System.out.println("Personal with name \"FN1\", surname \"LN1\" and CEO position:" +
                department.getPersonalByFirstNameAndLastNameAndPosition("FN1", "LN1", Position.CEO));
        System.out.println("Personal with name \"FN1\", surname \"LN1\" and CEO position using streams:" +
                department.getPersonalByFirstNameAndLastNameAndPositionUsingStreams("FN1", "LN1", Position.CEO));
    }
}
