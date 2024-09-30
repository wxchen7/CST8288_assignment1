package com.algonquin.cst8288.fall24.assignment1.treatment;

import org.junit.Test;
import static org.junit.Assert.*;

import com.algonquin.cst8288.fall24.assignment1.patient.Patient;
import com.alognquin.cst8288.fall24.assignment1.Constants;

public class MedicationTreatmentPlanTest {

    @Test
    public void testCreateTreatmentPlanForChild() {
        Patient patient1 = createMockPatient("CH001", "Child Name", "child@email.com", "1234567890", "2018-01-01");
        patient1.setPlannedTreatment(Constants.ACUTE);
        MedicationTreatmentPlan plan = new MedicationTreatmentPlan();
        String result = plan.createTreatmentPlan(patient1);

        System.out.println("Child Treatment Plan: " + result);

        assertTrue(result.contains("Doses per day: 1"));
        assertTrue(result.contains("Duration: " + Constants.ACUTE_DURATION + " days"));
        assertTrue(result.contains("Medication Type: No Medication"));
    }

    @Test
    public void testCreateTreatmentPlanForYouth() {
        Patient patient2 = createMockPatient("YO001", "Youth Name", "youth@email.com", "2345678901", "2008-01-01");
        patient2.setPlannedTreatment(Constants.INFECTION);
        MedicationTreatmentPlan plan = new MedicationTreatmentPlan();
        String result = plan.createTreatmentPlan(patient2);

        System.out.println("Youth Treatment Plan: " + result);

        assertTrue(result.contains("Doses per day: 2"));
        assertTrue(result.contains("Duration: " + Constants.INFECTION_DURATION + " days"));
        assertTrue(result.contains("Medication Type: Antibiotics"));
    }

    @Test
    public void testCreateTreatmentPlanForAdult() {
        Patient patient3 = createMockPatient("AD001", "Adult Name", "adult@email.com", "3456789012", "1990-01-01");
        patient3.setPlannedTreatment(Constants.CHRONIC);
        MedicationTreatmentPlan plan = new MedicationTreatmentPlan();
        String result = plan.createTreatmentPlan(patient3);

        System.out.println("Adult Treatment Plan: " + result);

        assertTrue(result.contains("Doses per day: 3"));
        assertTrue(result.contains("Duration: " + Constants.CHRONIC_DURATION + " days"));
        assertTrue(result.contains("Medication Type: Specialized Medication"));
    }

    private Patient createMockPatient(String id, String name, String email, String phoneNumber, String dateOfBirth) {
        return new Patient(id, name, email, phoneNumber, dateOfBirth) {
        };
    }
}
