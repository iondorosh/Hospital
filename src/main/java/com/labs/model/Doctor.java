package com.labs.model;

import com.labs.enums.Position;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Comparator;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Doctor implements Comparable<Doctor>, Comparator<Doctor> {
    private String firstName;
    private String lastName;
    private Position position;

    public Doctor() {
        firstName = "John";
        lastName = "Doe";
        position = Position.UNKNOWN;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Position getPosition() {
        return position;
    }

    /**
     * Method for comparing of Doctor instance with another instance
     * @param o another instance for comparing
     * @return result of comparing Doctor instance with another instance
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return getFirstName().equals(doctor.getFirstName()) && getLastName().equals(doctor.getLastName()) && getPosition() == doctor.getPosition();
    }

    /**
     * Method for getting hash code of the instance of Doctor
     * @return hash code of instance
     */
    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getPosition());
    }

    /**
     * Method for converting the instance of Doctor to String format
     * @return string format of the instance of Doctor
     */
    @Override
    public String toString() {
        return "Doctor{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position=" + position +
                '}';
    }

    @Override
    public int compareTo(Doctor o) {
        int flag = this.position.toString().compareTo(o.position.toString());
        if (flag != 0) return flag;

        flag = this.firstName.compareTo(o.firstName);
        if (flag != 0) return flag;

        return this.lastName.compareTo(o.lastName);
    }

    @Override
    public int compare(Doctor o1, Doctor o2) {
        return o1.compareTo(o2);
    }

    /**
     * Realisation of the builder pattern for class Doctor
     */
    public static class DoctorBuilder {
        private Doctor doctor;

        public DoctorBuilder() {
            this.doctor = new Doctor();
        }

        public DoctorBuilder setFirstName(String firstName) {
            this.doctor.firstName = firstName;
            return this;
        }

        public DoctorBuilder setLastName(String lastName) {
            this.doctor.lastName = lastName;
            return this;
        }

        public DoctorBuilder setPosition(Position position) {
            this.doctor.position = position;
            return this;
        }

        public Doctor build() {
            return this.doctor;
        }
    }
}
