package models;

public class OrderItems {
	private int _orderId;
	private int _productId;
	private int _quantity;
	private String _productName;
	
	public void setProductName(String productName) {
		_productName = productName;
	}
	
	public String getProductName() {
		return _productName;
	}
	
	/* ----------------------------------------------------------------------- */
	
	public void setOrderId(int orderId) {
		_orderId = orderId;
	}
	
	public int getOrderId() {
		return _orderId;
	}
	
	/* ----------------------------------------------------------------------- */
	
	public void setProductId(int productId) {
		_productId = productId;
	}
	
	public int getProductId() {
		return _productId;
	}
	
	/* ----------------------------------------------------------------------- */
	
	public void setQuantity(int quantity) {
		_quantity = quantity;
	}
	
	public int getQuantity() {
		return _quantity;
	}
}
