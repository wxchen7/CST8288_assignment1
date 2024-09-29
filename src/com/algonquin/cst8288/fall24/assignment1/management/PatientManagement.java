package com.algonquin.cst8288.fall24.assignment1.management;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.algonquin.cst8288.fall24.assignment1.patient.Patient;
import com.algonquin.cst8288.fall24.assignment1.prescription.PrescriptionService;
import com.algonquin.cst8288.fall24.assignment1.treatment.TreatmentPlan;

/**
 * The patient management class used by Physicians to perform multiple tasks.
 *
 */
public class PatientManagement {

    private final TreatmentPlan treatmentPlan;
    private final PrescriptionService prescriptionService;

    // Constructor injection TreatmentPlan
    public PatientManagement(TreatmentPlan treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
        this.prescriptionService = null;
    }

    // Constructor injection PrescriptionService
    public PatientManagement(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
        this.treatmentPlan = null;
    }

    // Constructor injection TreatmentPlan & PrescriptionService
    public PatientManagement(TreatmentPlan treatmentPlan, PrescriptionService prescriptionService) {
        this.treatmentPlan = treatmentPlan;
        this.prescriptionService = prescriptionService;
    }

    /**
     * Create treatment plan
     *
     * @param patient
     */
    public void createTreatmentPlan(Patient patient) {

        treatmentPlan.createTreatmentPlan(patient);

    }

    /**
     * Create prescription
     *
     * @param patient
     * @param filename
     * @throws IOException
     */
    public void generatePrescription(Patient patient, String filename) throws IOException {
        BufferedWriter bwriter;
        bwriter = new BufferedWriter(new FileWriter(filename));
        bwriter.write(prescriptionService.generatePrescription(patient));
        bwriter.close();
    }

}
