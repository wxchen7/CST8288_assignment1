package com.algonquin.cst8288.fall24.assignment1.prescription;

import com.algonquin.cst8288.fall24.assignment1.patient.Patient;
import com.algonquin.cst8288.fall24.assignment1.treatment.TreatmentPlan;
import com.algonquin.cst8288.fall24.assignment1.treatment.MedicationTreatmentPlan;

public class OnlinePrescriptionService implements PrescriptionService {

    @Override
    public String generatePrescription(Patient patient) {
        TreatmentPlan treatmentPlan = new MedicationTreatmentPlan();

        String plan = treatmentPlan.createTreatmentPlan(patient);

        StringBuilder htmlPrescription = new StringBuilder();
        htmlPrescription.append("<html><body>");
        htmlPrescription.append("<h1>Online Prescription</h1>");
        htmlPrescription.append("<p>Patient Name: ").append(patient.getName()).append("</p>");
        htmlPrescription.append("<p>Patient ID: ").append(patient.getId()).append("</p>");
        htmlPrescription.append("<h2>Treatment Plan:</h2>");
        htmlPrescription.append("<pre>").append(plan).append("</pre>");
        htmlPrescription.append("</body></html>");

        return htmlPrescription.toString();
    }
}
