package com.algonquin.cst8288.fall24.assignment1.treatment;

import com.algonquin.cst8288.fall24.assignment1.patient.Patient;
import com.alognquin.cst8288.fall24.assignment1.Constants;

/**
 *
 * This class is responsible for implementing the business logic related to
 * generating a treatment plan.
 *
 * You can use any method you prefer, such as StringBuilder or standard String,
 * to construct the plan. Think of treatment plan is a doctor notes in a summary
 * format. Later, you will attach the treatment plan to detailed prescription.
 *
 * An example might look like this: "The patient is diagnosed with xxx and
 * requires surgery. The patient is xxx years old with a risk factor of xxx. It
 * is required to perform a minor surgery in order to address the xxx issue."
 *
 *
 */
public class SurgeryTreatmentPlan implements TreatmentPlan {

    @Override
    public String createTreatmentPlan(Patient patient) {
        long age = patient.getAge();
        String riskFactor;
        String fasting;
        String followUp;

        if (age < 6) {
            riskFactor = Constants.HIGH;
            fasting = "no fasting";
            followUp = "next day";
        } else if (age <= 18) {
            riskFactor = Constants.MEDIUM;
            fasting = "2 hours before";
            followUp = "in a week";
        } else {
            riskFactor = Constants.LOW;
            fasting = "8 hours before";
            followUp = "in a month";
        }

        String plan = "The patient is diagnosed with a condition and requires surgery. "
                + "The patient is " + age + " years old with a risk factor of " + riskFactor + ". "
                + "It is required to perform a minor surgery in order to address the patient's condition. "
                + "The patient should follow a fasting requirement of " + fasting + " the surgery. "
                + "A follow-up appointment will be scheduled " + followUp + ".";

        return plan;
    }
}
