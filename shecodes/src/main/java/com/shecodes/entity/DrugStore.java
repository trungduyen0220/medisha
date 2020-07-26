/**
 * @author ntmduyen
 * @filename Medicine.java
 * @datetime Jul 24, 2020 - tim9:09:37 AM
 */
package com.shecodes.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * @author ntmduyen
 *
 */
@Entity
public class DrugStore implements Serializable {

	private static final long serialVersionUID = -5052946979390519252L;

	@Id
	private Long storeId;

	private String storeImage;

	private String storeUrl;

	private String storeName;

	private String storeTel;

	private String storeAddress;

	private Long parentDrugStoreId;

	private boolean enabled;

	private String description;

	@Transient
	private List<Medicine> lstMedicineByStore;
	
	@Transient
	private int numberOfMedicine;
	
	@Transient
	private int totalPrice;

	public DrugStore() {
	}

	/**
	 * @author ntmduyen
	 * @datetime Jul 24, 2020 - 4:57:48 PM
	 * @param storeId
	 * @param storeImage
	 * @param storeUrl
	 * @param storeName
	 * @param storeTel
	 * @param storeAddress
	 * @param parentDrugStoreId
	 * @param enabled
	 * @param description
	 */
	public DrugStore(Long storeId, String storeImage, String storeUrl, String storeName, String storeTel,
			String storeAddress, Long parentDrugStoreId, boolean enabled, String description) {
		super();
		this.storeId = storeId;
		this.storeImage = storeImage;
		this.storeUrl = storeUrl;
		this.storeName = storeName;
		this.storeTel = storeTel;
		this.storeAddress = storeAddress;
		this.parentDrugStoreId = parentDrugStoreId;
		this.enabled = enabled;
		this.description = description;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getStoreImage() {
		return storeImage;
	}

	public void setStoreImage(String storeImage) {
		this.storeImage = storeImage;
	}

	public String getStoreUrl() {
		return storeUrl;
	}

	public void setStoreUrl(String storeUrl) {
		this.storeUrl = storeUrl;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreTel() {
		return storeTel;
	}

	public void setStoreTel(String storeTel) {
		this.storeTel = storeTel;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public Long getParentDrugStoreId() {
		return parentDrugStoreId;
	}

	public void setParentDrugStoreId(Long parentDrugStoreId) {
		this.parentDrugStoreId = parentDrugStoreId;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Transient
	public List<Medicine> getLstMedicineByStore() {
		return lstMedicineByStore;
	}

	@Transient
	public void setLstMedicineByStore(List<Medicine> lstMedicineByStore) {
		this.lstMedicineByStore = lstMedicineByStore;
	}

	public int getNumberOfMedicine() {
		return numberOfMedicine;
	}

	public void setNumberOfMedicine(int numberOfMedicine) {
		this.numberOfMedicine = numberOfMedicine;
	}
	
	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
}
