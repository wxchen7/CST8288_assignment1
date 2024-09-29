package com.algonquin.cst8288.fall24.assignment1.patient;

import com.algonquin.cst8288.fall24.assignment1.prescription.Prescription;
import com.alognquin.cst8288.fall24.assignment1.Constants;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * Patient class that holds all patient data.
 */
public abstract class Patient {

    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
    private long age;
    private String lifeStage;
    private String plannedTreatment;
    private Prescription prescription;

    public Patient(String id, String name, String email, String phoneNumber, String dateOfBirth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        updateAgeAndLifeStage();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        updateAgeAndLifeStage();
    }

    public long getAge() {
        return age;
    }

    public String getLifeStage() {
        return lifeStage;
    }

    private void updateAgeAndLifeStage() {
        this.age = calculatePatientAge();
        this.lifeStage = determineLifeStage(this.age);
    }

    /**
     * Calculate patient age based on date of birth
     *
     * @return The patient's age in years
     */
    private long calculatePatientAge() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(this.dateOfBirth, formatter);
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    /**
     * Determine life stage based on age
     *
     * @param age The patient's age in years
     * @return The patient's life stage (CHILD, YOUTH, or ADULT)
     */
    private String determineLifeStage(long age) {
        return (age <= 6) ? Constants.CHILD
                : (age <= 18) ? Constants.YOUTH
                : Constants.ADULT;
    }

    public String getPlannedTreatment() {
        return plannedTreatment;
    }

    public void setPlannedTreatment(String plannedTreatment) {
        this.plannedTreatment = plannedTreatment;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
}