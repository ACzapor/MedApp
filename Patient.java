package application;

import java.util.ArrayList;
import java.util.List;

public class Patient {

	private String name;
	private String surname;
	private String pesel;
	private String address;
	private List<BloodPressure> bloodPressureTests;

	public Patient(String name, String surname, String pesel) {
		if (name != null && surname != null && pesel != null) {
			this.name = name;
			this.surname = surname;
			this.pesel = pesel;
			this.setBloodPressureTests(new ArrayList<>());
		} else {
			throw new IllegalArgumentException();
		}
	}

	public List<BloodPressure> getBloodPressureTests() {
		return bloodPressureTests;
	}

	public void setBloodPressureTests(List<BloodPressure> bloodPressureTests) {
		this.bloodPressureTests = bloodPressureTests;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null) {
			this.name = name;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		if (surname != null) {
			this.surname = surname;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		if (pesel != null) {
			this.pesel = pesel;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if (address != null) {
			this.address = address;
		} else {
			throw new IllegalArgumentException();
		}
	}

}
