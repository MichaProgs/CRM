package de.michaprogs.crm.clerk;

public class ModelClerk {

	private int clerkID;
	private String salutation;
	private String name;
	private String phone;
	private String fax;
	private String email;
	private String department;
	
	public ModelClerk(){}

	/**
	 * Constructor for ObservableList (Clerk Add)
	 * @param clerkID
	 * @param salutation
	 * @param name
	 * @param phone
	 * @param fax
	 * @param email
	 * @param department
	 */
	public ModelClerk(	int clerkID,
						String salutation, 
						String name, 
						String phone, 
						String fax, 
						String email,
						String department) {

		this.clerkID = clerkID;
		this.salutation = salutation;
		this.name = name;
		this.phone = phone;
		this.fax = fax;
		this.email = email;
		this.department = department;
		
	}
	
	/**
	 * Constructor for Database (Insert Clerk)
	 * @param salutation
	 * @param name
	 * @param phone
	 * @param fax
	 * @param email
	 * @param department
	 */
	public ModelClerk(	String salutation, 
						String name, 
						String phone, 
						String fax, 
						String email,
						String department) {
	
		this.salutation = salutation;
		this.name = name;
		this.phone = phone;
		this.fax = fax;
		this.email = email;
		this.department = department;
	
	}
	
	/**
	 * Constructor for Database (Select Clerk)
	 * @param clerkID
	 */
	public ModelClerk(int clerkID){
		this.clerkID = clerkID;
	}

	/*
	 * GETTER & SETTER
	 */
	public int getClerkID() {
		return clerkID;
	}

	public void setClerkID(int clerkID) {
		this.clerkID = clerkID;
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
