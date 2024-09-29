package com.algonquin.cst8288.fall24.assignment1.prescription;

import com.algonquin.cst8288.fall24.assignment1.patient.Patient;
import com.algonquin.cst8288.fall24.assignment1.treatment.TreatmentPlan;
import com.algonquin.cst8288.fall24.assignment1.treatment.MedicationTreatmentPlan;

public class PrintablePrescriptionService implements PrescriptionService {

    @Override
    public String generatePrescription(Patient patient) {
        TreatmentPlan treatmentPlan = new MedicationTreatmentPlan();

        String plan = treatmentPlan.createTreatmentPlan(patient);

        StringBuilder txtPrescription = new StringBuilder();
        txtPrescription.append("Printable Prescription\n");
        txtPrescription.append("=====================\n\n");
        txtPrescription.append("Patient Name: ").append(patient.getName()).append("\n");
        txtPrescription.append("Patient ID: ").append(patient.getId()).append("\n\n");
        txtPrescription.append("Treatment Plan:\n");
        txtPrescription.append(plan).append("\n");

        return txtPrescription.toString();
    }
}
