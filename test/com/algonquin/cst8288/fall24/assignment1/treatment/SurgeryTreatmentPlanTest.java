package com.algonquin.cst8288.fall24.assignment1.treatment;

import org.junit.Test;
import static org.junit.Assert.*;

import com.algonquin.cst8288.fall24.assignment1.patient.Patient;
import com.alognquin.cst8288.fall24.assignment1.Constants;

public class SurgeryTreatmentPlanTest {

    @Test
    public void testCreateTreatmentPlanForChild() {
        Patient patient1 = createMockPatient("CH001", "Child Name", "child@email.com", "1234567890", "2018-01-01");
        SurgeryTreatmentPlan plan = new SurgeryTreatmentPlan();
        String result = plan.createTreatmentPlan(patient1);

        System.out.println("Child Surgery Treatment Plan: " + result);

        assertTrue(result.contains("risk factor of " + Constants.HIGH));
        assertTrue(result.contains("fasting requirement of no fasting"));
        assertTrue(result.contains("follow-up appointment will be scheduled next day"));
    }

    @Test
    public void testCreateTreatmentPlanForYouth() {
        Patient patient2 = createMockPatient("YO001", "Youth Name", "youth@email.com", "2345678901", "2008-01-01");
        SurgeryTreatmentPlan plan = new SurgeryTreatmentPlan();
        String result = plan.createTreatmentPlan(patient2);

        System.out.println("Youth Surgery Treatment Plan: " + result);

        assertTrue(result.contains("risk factor of " + Constants.MEDIUM));
        assertTrue(result.contains("fasting requirement of 2 hours before"));
        assertTrue(result.contains("follow-up appointment will be scheduled in a week"));
    }

    @Test
    public void testCreateTreatmentPlanForAdult() {
        Patient patient3 = createMockPatient("AD001", "Adult Name", "adult@email.com", "3456789012", "1990-01-01");
        SurgeryTreatmentPlan plan = new SurgeryTreatmentPlan();
        String result = plan.createTreatmentPlan(patient3);

        System.out.println("Adult Surgery Treatment Plan: " + result);

        assertTrue(result.contains("risk factor of " + Constants.LOW));
        assertTrue(result.contains("fasting requirement of 8 hours before"));
        assertTrue(result.contains("follow-up appointment will be scheduled in a month"));
    }

    private Patient createMockPatient(String id, String name, String email, String phoneNumber, String dateOfBirth) {
        return new Patient(id, name, email, phoneNumber, dateOfBirth) {
            @Override
            public long getAge() {
                switch (id.substring(0, 2)) {
                    case "CH":
                        return 5;  // Child
                    case "YO":
                        return 15; // Youth
                    case "AD":
                        return 30; // Adult
                    default:
                        return 0;
                }
            }
        };
    }
}
