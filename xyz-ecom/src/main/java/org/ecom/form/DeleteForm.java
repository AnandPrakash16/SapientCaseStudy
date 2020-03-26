package org.ecom.form;

public class DeleteForm {

	private String Id;

	public DeleteForm() {
		
	}
	public DeleteForm(String id) {
		super();
		Id = id;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		Id = id;
	}
	
	
}
