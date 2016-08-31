package de.michaprogs.crm.contact;

public class ModelContact {

	private int ID;
	private String salutation;
	private String name;
	private String phone;
	private String fax;
	private String email;
	private String department;
	
	public ModelContact(	int ID,
							String salutation, 
							String name, 
							String phone, 
							String fax, 
							String email, 
							String department) {
		
		this.ID = ID;
		this.salutation = salutation;
		this.name = name;
		this.phone = phone;
		this.fax = fax;
		this.email = email;
		this.department = department;
		
	}

	/*
	 * GETTER & SETTER
	 */
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	
	
}
