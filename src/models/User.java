package models;

public class User {
	private int _id;
	private String _name;
	private String _email; 
	private String _gender;
	private String _dateOfBirth;
	private int _countryCode;
	private String _createdAt;
	private String _countryName;
	
	
	public void setCountryName(String countryName) {
		_countryName = countryName;
	}
	
	public String getCountryName() {
		return _countryName;
	}
	
	/* -------------------------------------------------------------------------*/
	
	public void setId(int id) {
		_id = id;
	}
	
	public int getId() {
		return _id;
	}
	
	/* ----------------------------------------------------------------------- */
	
	public void setFullName(String name) {
		_name = name;
	}
	
	public String getFullName() {
		return _name;
	}
	
	/* ----------------------------------------------------------------------- */
	
	public void setEmail(String email) {
		_email = email;
	}
	
	public String getEmail() {
		return _email;
	}
	
	/* ----------------------------------------------------------------------- */
	
	public void setGender(String gender) {
		_gender = gender;
	}
	
	public String getGender() {
		return _gender;
	}
	
	/* ----------------------------------------------------------------------- */
	
	public void setDateOfBirth(String dateOfBirth) {
		_dateOfBirth = dateOfBirth;
	}
	
	public String getDateOfBirth() {
		return _dateOfBirth;
	}
	
	/* ----------------------------------------------------------------------- */
	
	public void setCountryCode(int countryCode) {
		_countryCode = countryCode;
	}
	
	public int getCountryCode() {
		return _countryCode;
	}
	
	/* ----------------------------------------------------------------------- */
	
	public void setCreatedAt(String createdAt) {
		_createdAt = createdAt;
	}
	
	public String getCreatedAt() {
		return _createdAt;
	}
}
