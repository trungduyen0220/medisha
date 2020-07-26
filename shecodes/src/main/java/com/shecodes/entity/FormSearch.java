package com.shecodes.entity;


public class FormSearch {
	private String medicineName;
	private String medicineAmount;
	private String medicineUnit;

	public FormSearch(String medicineName, String medicineAmount, String medicineUnit) {
		super();
		this.medicineName = medicineName;
		this.medicineAmount = medicineAmount;
		this.medicineUnit = medicineUnit;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public String getMedicineAmount() {
		return medicineAmount;
	}
	public void setMedicineAmount(String medicineAmount) {
		this.medicineAmount = medicineAmount;
	}
	public String getMedicineUnit() {
		return medicineUnit;
	}
	public void setMedicineUnit(String medicineUnit) {
		this.medicineUnit = medicineUnit;
	}
}
