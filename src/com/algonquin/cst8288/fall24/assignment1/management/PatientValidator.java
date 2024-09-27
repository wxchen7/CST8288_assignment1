package com.algonquin.cst8288.fall24.assignment1.management;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.algonquin.cst8288.fall24.assignment1.patient.Patient;

/**
 * The PatientValidator class is responsible for validating patient data. 
 * It adheres to the Single Responsibility Principle.
 */
public class PatientValidator {

    /**
     * Validate patient data
     *
     * @param patient
     * @return
     */
    public boolean validatePatientData(Patient patient) {
        if (!isValidName(patient.getName())) {
            return false;
        }
        patient.setName(patient.getName().trim());

        if (!isValidAlphaNumeric(patient.getName())) {
            return false;
        }
        if (patient.getEmail() == null || patient.getEmail().trim().length() == 0) {
            return false;
        }
        patient.setEmail(patient.getEmail().trim());
        if (!isValidEmail(patient.getEmail())) {
            return false;
        }

        if (!isValidPhone(patient.getPhoneNumber())) {
            return false;
        }

        return true;
    }

    private boolean isValidName(String value) {
        return value != null && value.trim().length() > 0;
    }

    private boolean isValidAlphaNumeric(String value) {
        Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
        Matcher matcher = pattern.matcher(value);
        return !matcher.find();
    }

    private boolean isValidEmail(String value) {
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }

    private boolean isValidPhone(String value) {
        Pattern pattern = Pattern.compile("^(\\+1\\s?)?(\\(?\\d{3}\\)?[\\s.-]?)\\d{3}[\\s.-]?\\d{4}$");
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }
}
