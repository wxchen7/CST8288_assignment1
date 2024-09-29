package com.algonquin.cst8288.fall24.assignment1.management;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import com.algonquin.cst8288.fall24.assignment1.patient.Patient;
import com.algonquin.cst8288.fall24.assignment1.treatment.MedicationTreatmentPlan;
import com.algonquin.cst8288.fall24.assignment1.treatment.SurgeryTreatmentPlan;
import com.algonquin.cst8288.fall24.assignment1.treatment.TreatmentPlan;
import com.algonquin.cst8288.fall24.assignment1.prescription.PrescriptionService;
import com.algonquin.cst8288.fall24.assignment1.prescription.OnlinePrescriptionService;
import com.algonquin.cst8288.fall24.assignment1.prescription.PrintablePrescriptionService;

/**
 * The patient management class used by Physicians to perform multiple tasks.
 *
 */
public class PatientManagement {

    private final TreatmentPlan treatmentPlan;
    private final PrescriptionService prescriptionService;

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
     * @param baseFilename
     * @throws IOException
     */
    public void generateTreatmentDocuments(Patient patient, String baseFilename) throws IOException {
        String htmlDocument;
        String txtDocument;

        // create exports folder
        File exportsDir = new File("exports");
        if (!exportsDir.exists()) {
            exportsDir.mkdirs();
        }

        if (treatmentPlan instanceof MedicationTreatmentPlan) {
            OnlinePrescriptionService onlineService = new OnlinePrescriptionService();
            PrintablePrescriptionService printableService = new PrintablePrescriptionService();

            htmlDocument = onlineService.generatePrescription(patient);
            txtDocument = printableService.generatePrescription(patient);
        } else if (treatmentPlan instanceof SurgeryTreatmentPlan) {
            String surgeryPlan = ((SurgeryTreatmentPlan) treatmentPlan).createTreatmentPlan(patient);
            htmlDocument = "<html><body><pre>" + surgeryPlan + "</pre></body></html>";
            txtDocument = surgeryPlan;
        } else {
            throw new IllegalStateException("Unknown treatment plan type");
        }

        // generate HTML file
        String htmlFilename = "exports/" + baseFilename + ".html";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(htmlFilename))) {
            writer.write(htmlDocument);
        }

        // generate TXT file
        String txtFilename = "exports/" + baseFilename + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(txtFilename))) {
            writer.write(txtDocument);
        }

        System.out.println("Treatment documents generated: " + htmlFilename + " and " + txtFilename);
    }

}
