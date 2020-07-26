/**
 * @author ntmduyen
 * @filename Medicine.java
 * @datetime Jul 24, 2020 - tim9:09:37 AM
 */
package com.shecodes.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

/**
 * @author ntmduyen
 *
 */
@Entity
@Table(name = "medicine")
public class Medicine implements Serializable {

	private static final long serialVersionUID = -5052946979390519252L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "medicine_id")
	private Long medicineId;
	
	@Column(name = "medicine_name")
	private String medicineName;
	
	@Column(name = "medicine_unit")
	private String medicineUnit;
	
	@Column(name = "medicine_amount")
	private String medicineAmount;
	
	@Column(name = "medicine_physical")
	private String medicinePhysical;
	
	@Column(name = "medicine_medication")
	private boolean prescriptMedication;

	@Column(name = "image")
	private String image;
	
	@Column(name = "expire_date")
	private String expireDate;
	
	@Column(name = "date_created")
	private String dateCreated;
	
	@Column(name = "user_created_id")
	private Long userCreatedId;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@Column(name = "max_quantity")
	private int maxQuantity;

	@Transient
	private int price;
	
	@Transient
	private int salePercent;
	
	@Transient
	private float realPrice;

	public Medicine() {
	}

	public Medicine(Long medicineId) {
		this.medicineName = "Medicine Name";
		this.medicineUnit = "Unit";
		this.medicineAmount = "20ml";
		this.medicinePhysical = "physical";
		this.prescriptMedication = true;
		this.image = "../../imageDrug.jpg";
		this.expireDate = "2030-12-31";
		this.dateCreated = "";
		this.userCreatedId = (long) 1;
		this.description = "This is desciption";
		this.enabled = true;
		this.maxQuantity = 1;
	}
	
	public Medicine(String medicineName, String medicineUnit) {
		this.medicineName = "Medicine Name";
		this.medicineUnit = "Unit";
		this.medicineAmount = "20ml";
		this.medicinePhysical = "physical";
		this.prescriptMedication = true;
		this.image = "../../imageDrug.jpg";
		this.expireDate = "2030-12-31";
		this.dateCreated = "";
		this.userCreatedId = (long) 1;
		this.description = "This is desciption";
		this.enabled = true;
		this.maxQuantity = 1;
	}
	
	
	/**
	 * @author ntmduyen
	 * @datetime Jul 24, 2020 - 2:35:23 PM
	 * @param medicineId
	 * @param medicineName
	 * @param medicineUnit
	 * @param medicineAmount
	 * @param medicinePhysical
	 * @param prescript_medication
	 * @param image
	 * @param expireDate
	 * @param dateCreated
	 * @param userCreated
	 * @param description
	 * @param enabled
	 * @param maxQuantity
	 */
	public Medicine(Long medicineId, String medicineName, String medicineUnit, String medicineAmount,
			String medicinePhysical, boolean prescriptMedication, String image, String expireDate, String dateCreated,
			Long userCreatedId, String description, boolean enabled, int maxQuantity) {
		super();
		this.medicineId = medicineId;
		this.medicineName = medicineName;
		this.medicineUnit = medicineUnit;
		this.medicineAmount = medicineAmount;
		this.medicinePhysical = medicinePhysical;
		this.prescriptMedication = prescriptMedication;
		this.image = image;
		this.expireDate = expireDate;
		this.dateCreated = dateCreated;
		this.userCreatedId = userCreatedId;
		this.description = description;
		this.enabled = enabled;
		this.maxQuantity = maxQuantity;
	}

	public Long getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getMedicineUnit() {
		return medicineUnit;
	}

	public void setMedicineUnit(String medicineUnit) {
		this.medicineUnit = medicineUnit;
	}

	public String getMedicineAmount() {
		return medicineAmount;
	}

	public void setMedicineAmount(String medicineAmount) {
		this.medicineAmount = medicineAmount;
	}

	public String getMedicinePhysical() {
		return medicinePhysical;
	}

	public void setMedicinePhysical(String medicinePhysical) {
		this.medicinePhysical = medicinePhysical;
	}

	public boolean isPrescriptMedication() {
		return prescriptMedication;
	}

	public void setPrescriptMedication(boolean prescriptMedication) {
		this.prescriptMedication = prescriptMedication;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Long getUserCreated() {
		return userCreatedId;
	}

	public void setUserCreated(Long userCreatedId) {
		this.userCreatedId = userCreatedId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxQuantity(int maxQuantity) {
		this.maxQuantity = maxQuantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public int getSalePercent() {
		return salePercent;
	}

	public void setSalePercent(int salePercent) {
		this.salePercent = salePercent;
	}

	public float getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(float realPrice) {
		this.realPrice = realPrice;
	}
}
