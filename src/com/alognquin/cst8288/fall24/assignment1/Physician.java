package com.alognquin.cst8288.fall24.assignment1;

import java.io.IOException;

import com.algonquin.cst8288.fall24.assignment1.patient.Inpatient;
import com.algonquin.cst8288.fall24.assignment1.patient.Outpatient;
import com.algonquin.cst8288.fall24.assignment1.management.PatientManagement;
import com.algonquin.cst8288.fall24.assignment1.treatment.TreatmentPlan;
import com.algonquin.cst8288.fall24.assignment1.treatment.SurgeryTreatmentPlan;
import com.algonquin.cst8288.fall24.assignment1.treatment.MedicationTreatmentPlan;
import com.algonquin.cst8288.fall24.assignment1.prescription.PrescriptionService;
import com.algonquin.cst8288.fall24.assignment1.prescription.OnlinePrescriptionService;
import com.algonquin.cst8288.fall24.assignment1.prescription.PrintablePrescriptionService;

public class Physician {

    /**
     *
     * Create two instances of the 'Patient' class and apply
     * 'MedicationTreatment.java' and 'SurgeryTreatment.java' to generate and
     * populate the treatment plans for each patient. Then, use
     * 'PatientManagement.java' to generate both treatment plans. Once the
     * treatment plan is generated. Use PrescriptionService.java to generate
     * prescriptions in different format (.txt or .html)
     *
     *
     * @param args
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // Inpatient handling
        // Create inpatient instance
        Inpatient inpatient = new Inpatient("IP001", "John Doe", "john@example.com", "123-456-7890", "1950-01-01", "Room 101");
        inpatient.setPlannedTreatment("Surgery");

        // Create treatment plan instance for inpatient
        TreatmentPlan surgeryPlan = new SurgeryTreatmentPlan();

        // Create prescription service instance for inpatient
        PrescriptionService onlinePrescription = new OnlinePrescriptionService();

        // Create patient management instance for inpatient
        PatientManagement inpatientManagement = new PatientManagement(surgeryPlan, onlinePrescription);

        // Create treatment plan for inpatient
        inpatientManagement.createTreatmentPlan(inpatient);

        // Generate treatment documents for inpatient
        inpatientManagement.generateTreatmentDocuments(inpatient, inpatient.getName().replace(" ", "_") + "_surgery_plan");

        System.out.println("Inpatient treatment plan created and documents generated successfully.");

        // Print inpatient information
        System.out.println("\nInpatient Information:");
        System.out.println("Name: " + inpatient.getName());
        System.out.println("ID: " + inpatient.getId());
        System.out.println("Room Number: " + inpatient.getRoomNumber());
        System.out.println("Planned Treatment: " + inpatient.getPlannedTreatment());

        // Additional demonstration of inpatient treatment plan
        System.out.println("\nInpatient Treatment Plan:");
        System.out.println(surgeryPlan.createTreatmentPlan(inpatient));

        System.out.println("\n" + "=".repeat(100) + "\n");

        // Outpatient handling
        // Create outpatient instance
        Outpatient outpatient = new Outpatient("OP001", "Jane Smith", "jane@example.com", "987-654-3210", "2000-01-01", "2023-12-01");
        outpatient.setPlannedTreatment(Constants.CHRONIC);

        // Create treatment plan instance for outpatient
        TreatmentPlan medicationPlan = new MedicationTreatmentPlan();

        // Create prescription service instance for outpatient
        PrescriptionService printablePrescription = new PrintablePrescriptionService();

        // Create patient management instance for outpatient
        PatientManagement outpatientManagement = new PatientManagement(medicationPlan, printablePrescription);

        // Create treatment plan for outpatient
        outpatientManagement.createTreatmentPlan(outpatient);

        // Generate treatment documents for outpatient
        outpatientManagement.generateTreatmentDocuments(outpatient, outpatient.getName().replace(" ", "_") + "_prescription");

        System.out.println("Outpatient treatment plan created and prescription generated successfully.");

        // Print outpatient information
        System.out.println("\nOutpatient Information:");
        System.out.println("Name: " + outpatient.getName());
        System.out.println("ID: " + outpatient.getId());
        System.out.println("Appointment Date: " + outpatient.getAppointmentDate());
        System.out.println("Planned Treatment: " + outpatient.getPlannedTreatment());

        // Additional demonstration of outpatient treatment plan
        System.out.println("\nOutpatient Treatment Plan:");
        System.out.println(medicationPlan.createTreatmentPlan(outpatient));
    }
}
