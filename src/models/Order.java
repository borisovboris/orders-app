package models;

public class Order {
	private int _id;
	private int _userId;
	private String _status;
	private String _createdAt;

	public void setId(int id) {
		_id = id;
	}

	public int getId() {
		return _id;
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
	
	/* ----------------------------------------------------------------------- */
	
	public void setUserId(int userId) {
		_userId = userId;
	}

	public int getUserId() {
		return _userId;
	}

}
