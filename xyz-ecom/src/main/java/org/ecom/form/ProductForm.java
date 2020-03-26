package org.ecom.form;

public class ProductForm {

	
	private String catType;
	private String search;
	
	public ProductForm() {
		
	}
	public ProductForm(String catType, String search) {
		super();
		this.catType = catType;
		this.search = search;
	}
	/**
	 * @return the catType
	 */
	public String getCatType() {
		return catType;
	}
	/**
	 * @param catType the catType to set
	 */
	public void setCatType(String catType) {
		this.catType = catType;
	}
	/**
	 * @return the value
	 */
	public String getSearch() {
		return search;
	}
	/**
	 * @param value the value to set
	 */
	public void setSearch(String search) {
		this.search = search;
	}
	
	
}
