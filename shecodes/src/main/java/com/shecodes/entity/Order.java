package com.shecodes.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Order {
	@Id
	private Long orderId;
	private Long medicineId;
	private int quantity;
	private String text;
	private boolean enabled;
	
	/**
	 * 
	 * @param orderId
	 * @param medicineId
	 * @param quantity
	 * @param text
	 * @param enabled
	 */
	public Order(Long orderId, Long medicineId, int quantity, String text, boolean enabled) {
		super();
		this.orderId = orderId;
		this.medicineId = medicineId;
		this.quantity = quantity;
		this.text = text;
		this.enabled = enabled;
	}
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
