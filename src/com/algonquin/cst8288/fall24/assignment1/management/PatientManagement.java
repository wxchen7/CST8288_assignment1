package com.algonquin.cst8288.fall24.assignment1.management;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	 * Validate patient data
	 * 
	 * @param patient
	 * @return
	 */

	public boolean validatePatientData(Patient patient) {
		if (!isValidName(patient.getName())) {
			return false;
		}
		patient.setName(patient.getName().trim());

		if (!isValidAlphaNumeric(patient.getName())) {
			return false;
		}
		if (patient.getEmail() == null || patient.getEmail().trim().length() == 0) {
			return false;
		}
		patient.setEmail(patient.getEmail().trim());
		if (!isValidEmail(patient.getEmail())) {
			return false;
		}
		
		if(!isValidPhone(patient.getPhoneNumber())) {
			return false;
		}

		return true;
	}


	private boolean isValidName(String value) {
		return value != null && value.trim().length() > 0;
	}


	private boolean isValidAlphaNumeric(String value) {
		Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
		Matcher matcher = pattern.matcher(value);
		return !matcher.find();
	}


	private boolean isValidEmail(String value) {
		Pattern pattern = Pattern
				.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher matcher = pattern.matcher(value);
		return matcher.find();
	}

	private boolean isValidPhone(String value) {
		Pattern pattern = Pattern
				.compile("\"^(\\+1\\\\s?)?(\\\\(?\\\\d{3}\\\\)?[\\\\s.-]?)\\\\d{3}[\\\\s.-]?\\\\d{4}$\"");
		Matcher matcher = pattern.matcher(value);
		return matcher.find();
	}


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



