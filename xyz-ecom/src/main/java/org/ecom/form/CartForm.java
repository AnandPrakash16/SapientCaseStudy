package org.ecom.form;

import org.ecom.entity.Product;

public class CartForm {

	private Product product;

	
	public CartForm() {
		
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
