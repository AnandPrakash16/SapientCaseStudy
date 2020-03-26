package org.ecom.model;

public class BuyProduct {

	private int SKU;
	private int quantity;
	
	public BuyProduct() {
		
	}
	
	
	public BuyProduct(int sKU, int quantity) {
		super();
		SKU = sKU;
		this.quantity = quantity;
	}


	/**
	 * @return the sKU
	 */
	public int getSKU() {
		return SKU;
	}
	/**
	 * @param sKU the sKU to set
	 */
	public void setSKU(int sKU) {
		SKU = sKU;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
