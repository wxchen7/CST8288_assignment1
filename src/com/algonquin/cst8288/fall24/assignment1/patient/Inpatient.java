package com.algonquin.cst8288.fall24.assignment1.patient;

public class Inpatient extends Patient {
    private String roomNumber;

    public Inpatient(String id, String name, String email, String phoneNumber, String dateOfBirth, String roomNumber) {
        super(id, name, email, phoneNumber, dateOfBirth);
        this.roomNumber = roomNumber;
    }


    public String getRoomNumber() {
        return roomNumber;
    }
    

	@Override
	public void admit() {
		System.out.println(getName() + " has been admitted to " + getRoomNumber());
		
	}
}
