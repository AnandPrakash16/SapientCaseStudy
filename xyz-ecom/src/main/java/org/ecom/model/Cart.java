package org.ecom.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	List<CartItem> items = new ArrayList<CartItem>();
	private double cartTotal;
	/**
	 * @return the items
	 */
	public List<CartItem> getItems() {
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	/**
	 * @return the cartTotal
	 */
	public double getCartTotal() {
		return cartTotal;
	}
	/**
	 * @param cartTotal the cartTotal to set
	 */
	public void setCartTotal(double cartTotal) {
		this.cartTotal = cartTotal;
	}
	
	
}
