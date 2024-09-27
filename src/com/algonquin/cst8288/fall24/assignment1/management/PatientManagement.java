package com.algonquin.cst8288.fall24.assignment1.management;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import com.algonquin.cst8288.fall24.assignment1.patient.Patient;
import com.algonquin.cst8288.fall24.assignment1.prescription.OnlinePrescriptionService;
import com.algonquin.cst8288.fall24.assignment1.prescription.PrescriptionService;
import com.algonquin.cst8288.fall24.assignment1.treatment.SurgeryTreatmentPlan;
import com.algonquin.cst8288.fall24.assignment1.treatment.TreatmentPlan;
import com.alognquin.cst8288.fall24.assignment1.Constants;


/**
 * The patient management class  used by Physicians to perform multiple tasks.
 * 
 */

public class PatientManagement {

	



	


	/**
	 * Calculate patient age based on date of birth
	 * 
	 * @param dob
	 * @return
	 */
	public long calulatePatientAge(String dob) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateOfBirth = LocalDate.parse(dob, formatter);
		
        LocalDate currentDate = LocalDate.now();
        long age = Period.between(dateOfBirth, currentDate).getYears();
        
        return age;
      
	}
	
	/**
	 * Determine life stage based on age
	 * 
	 * @param age
	 * @return
	 */
	public String determineLifeStage(long age) {
		
		return (age <= 6) ? Constants.CHILD : 
     	   (age <= 18) ? Constants.YOUTH : 
     		  Constants.ADULT;   
	}
	

	/**
	 * Create treatment plan
	 * 
	 * @param patient
	 */
	public void createTreatmentPlan(Patient patient){
		TreatmentPlan treatment = new SurgeryTreatmentPlan();
		patient.setAge(this.calulatePatientAge(patient.getDateOfBirth()));
		patient.setLifeStage(this.determineLifeStage(patient.getAge()));
		treatment.createTreatmentPlan(patient);
		
	}


	/**
	 * Create prescription
	 * 
	 * @param patient
	 * @param filename
	 * @throws IOException
	 */
	public void generatePrescription(Patient patient, String filename) throws IOException {
		PrescriptionService prescriptionService = new OnlinePrescriptionService();
		BufferedWriter bwriter;
		bwriter = new BufferedWriter(new FileWriter(filename));	
		bwriter.write(prescriptionService.generatePrescription(patient));
		bwriter.close();
	}

}



