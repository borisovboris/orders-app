package models;

public class OrderedItem {
	private int _orderId;
	private int _productId;
	private int _quantity;
	
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
