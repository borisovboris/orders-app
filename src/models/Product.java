package models;

public class Product {
	private int _id;
	private int _merchantId;
	private String _name;
	private int _price;
	private String _status;
	private String _createdAt;
	private String _merchantName;
	
	public void setMerchantName(String merchantName) {
		_merchantName = merchantName;
	}
	
	public String getMerchantName() {
		return _merchantName;
	}
	
	/* ----------------------------------------------------------------------- */
	
	public void setId(int id) {
		_id = id;
	}
	
	public int getId() {
		return _id;
	}
	
	/* ----------------------------------------------------------------------- */
	
	public void setMerchantId(int merchantId) {
		_merchantId = merchantId;
	}
	
	public int getMerchantId() {
		return _merchantId;
	}
	
	/* ----------------------------------------------------------------------- */
	
	public void setName(String name) {
		_name = name;
	}
	
	public String getName() {
		return _name;
	}
	
	/* ----------------------------------------------------------------------- */
	
	public void setPrice(int price) {
		_price = price;
	}
	
	public int getPrice() {
		return _price;
	}
	
	/* ----------------------------------------------------------------------- */
	
	public void setStatus(String status) {
		_status = status;
	}
	
	public String getStatus() {
		return _status;
	}
	
	/* ----------------------------------------------------------------------- */
	
	public void setCreatedAt(String createdAt) {
		_createdAt = createdAt;
	}
	
	public String getCreatedAt() {
		return _createdAt;
	}
}
