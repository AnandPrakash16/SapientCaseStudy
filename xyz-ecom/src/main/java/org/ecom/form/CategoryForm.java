package org.ecom.form;

import java.util.Date;

public class CategoryForm {

	private String name;

	private Date lastUpdate ;

	private int cat_id;

	public CategoryForm() {
		
	}
			
	public CategoryForm(String name, Date lastUpdate, int cat_id) {
		super();
		this.name = name;
		this.lastUpdate = lastUpdate;
		this.cat_id = cat_id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the lastUpdate
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/**
	 * @return the cat_id
	 */
	public int getCat_id() {
		return cat_id;
	}

	/**
	 * @param cat_id the cat_id to set
	 */
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}
	
	
}
