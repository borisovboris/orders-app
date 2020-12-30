package models;

public class Merchant {
	private int _id;
	private String _merchantName;
	private int _adminId;
	private int _countryCode;
	private String _createdAt;
	private String _adminFullName;
	private String _countryName;
	
	public void setAdminFullName(String adminFullName) {
		_adminFullName = adminFullName;
	}
	
	public String getAdminFullName() {
		return _adminFullName;
	}
	
	/* ----------------------------------------------------------------------- */
	
	public void setCountryName(String countryName) {
		_countryName = countryName;
	}
	
	public String getCountryName() {
		return _countryName;
	}
	
	/* ----------------------------------------------------------------------- */
	
	public void setId(int id) {
		_id = id;
	}
	
	public int getId() {
		return _id;
	}
	
	/* ----------------------------------------------------------------------- */
	
	public void setMerchantName(String merchantName) {
		_merchantName = merchantName;
	}
	
	public String getMerchantName() {
		return _merchantName;
	}
	
	/* ----------------------------------------------------------------------- */
	
	public void setAdminId(int adminId) {
		_adminId = adminId;
	}
	
	public int getAdminId() {
		return _adminId;
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
