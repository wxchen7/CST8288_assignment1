package com.algonquin.cst8288.fall24.assignment1.treatment;

import com.algonquin.cst8288.fall24.assignment1.patient.Patient;
import com.alognquin.cst8288.fall24.assignment1.Constants;

public class MedicationTreatmentPlan implements TreatmentPlan {

    @Override
    public String createTreatmentPlan(Patient patient) {

        int dosesPerDay = getDosesPerDay(patient.getLifeStage());
        String condition = patient.getPlannedTreatment();
        int durationDays = (int) getDurationDays(condition);
        String medicationType = getMedicationType(condition);

        String plan = "Medication Treatment Plan for " + patient.getName() + ":\n"
                + "Life Stage: " + patient.getLifeStage() + "\n"
                + "Condition: " + condition + "\n"
                + "Doses per day: " + dosesPerDay + "\n"
                + "Duration: " + durationDays + " days\n"
                + "Medication Type: " + medicationType + "\n";

        return plan;
    }

    private int getDosesPerDay(String lifeStage) {
        switch (lifeStage) {
            case Constants.CHILD:
                return 1;
            case Constants.YOUTH:
                return 2;
            case Constants.ADULT:
                return 3;
            default:
                throw new IllegalArgumentException("Invalid life stage: " + lifeStage);
        }
    }

    private long getDurationDays(String condition) {
        switch (condition) {
            case Constants.ACUTE:
                return Constants.ACUTE_DURATION;
            case Constants.INFECTION:
                return Constants.INFECTION_DURATION;
            case Constants.CHRONIC:
                return Constants.CHRONIC_DURATION;
            default:
                throw new IllegalArgumentException("Invalid condition: " + condition);
        }
    }

    private String getMedicationType(String condition) {
        switch (condition) {
            case Constants.ACUTE:
                return "No Medication";
            case Constants.INFECTION:
                return "Antibiotics";
            case Constants.CHRONIC:
                return "Specialized Medication";
            default:
                throw new IllegalArgumentException("Invalid condition: " + condition);
        }
    }

}
