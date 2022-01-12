package com.labs.model;

import com.labs.enums.Position;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Department {
    private String name;

    @XmlElements({
            @XmlElement(name = "doctor", type = Doctor.class)
    })
    private List<Doctor> personal;

    public Department() {
        name = "DefaultDepartmentName";
        personal = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public List<Doctor> getPersonal() {
        return personal;
    }

    public List<Doctor> getPersonalByPosition(Position position){
        List<Doctor> res = new LinkedList();
        for (Doctor doc : personal) {
            if (position == doc.getPosition()) {
                res.add(doc);
            }
        }
        return res;
    }

    public List<Doctor> getPersonalByPositionUsingStreams(Position position){
        return personal.stream()
                .filter(doc -> doc.getPosition()==position)
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Doctor> getPersonalByFirstName(String firstName) {
        List<Doctor> res = new LinkedList();
        for (Doctor doc : personal) {
            if (firstName.equals(doc.getFirstName())) {
                res.add(doc);
            }
        }
        return res;
    }

    public List<Doctor> getPersonalByFirstNameUsingStreams(String firstName) {
        return personal.stream()
                .filter(doc -> firstName.equals(doc.getFirstName()))
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Doctor> getPersonalByFirstNameAndLastNameAndPosition(String firstName, String lastName, Position position) {
        List<Doctor> res = new LinkedList();
        for (Doctor doc : personal) {
            if (firstName.equals(doc.getFirstName()) && lastName.equals(doc.getLastName()) && position==doc.getPosition()) {
                res.add(doc);
            }
        }
        return res;
    }

    public List<Doctor> getPersonalByFirstNameAndLastNameAndPositionUsingStreams(String firstName, String lastName, Position position) {
        return personal.stream()
                .filter(doctor -> firstName.equals(doctor.getFirstName()))
                .filter(doctor -> lastName.equals(doctor.getLastName()))
                .filter(doctor -> firstName.equals(doctor.getFirstName()))
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Method for comparing of Department instance with another instance
     * @param o another instance for comparing
     * @return result of comparing Department instance with another instance
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return getName().equals(that.getName()) && getPersonal().equals(that.getPersonal());
    }

    /**
     * Method for getting hash code of the instance of Department
     * @return hash code of instance
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPersonal());
    }

    /**
     * Method for converting the instance of Department to String format
     * @return string format of the instance of Department
     */
    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", personal=" + personal +
                '}';
    }

    /**
     * Realisation of the builder pattern for class Department
     */
    public static class DepartmentBuilder {
        private Department department;

        public DepartmentBuilder() {
            this.department = new Department();
        }

        public DepartmentBuilder setDepartmentName(String name) {
            this.department.name = name;
            return this;
        }

        public DepartmentBuilder setPersonal(List<Doctor> personal) {
            this.department.personal = personal;
            return this;
        }

        public Department build() {
            return this.department;
        }
    }
}
