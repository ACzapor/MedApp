package application;

public class BloodPressure {
private int systolic;
private int diastolic;
private String date;

public BloodPressure(int systolic, int diastolic, String date) {
	if(systolic > 0 && systolic < 300 && diastolic > 0 && diastolic < 300) {
		this.systolic = systolic;
		this.diastolic = diastolic;
		this.date = date;
	} else {
		throw new IllegalArgumentException();
	}
}

public int getSystolic() {
	return systolic;
}

public void setSystolic(int systolic) {
	if(systolic > 0 && systolic < 300) {
	this.systolic = systolic;
	} else {
		throw new IllegalArgumentException();
	}
}

public int getDiastolic() {
	return diastolic;
}

public void setDiastolic(int diastolic) {
	if(diastolic > 0 && diastolic < 300) {
	this.diastolic = diastolic;
	} else {
		throw new IllegalArgumentException();
	}
}

public String getDate() {
	return date;
}

public void setDate(String date) {
	this.date = date;
}
}
