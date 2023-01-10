package vo;

public class Orders {
	private int orderCode;
	private String customerId;
	private int addressCode;
	private int orderQuantity;
	private int orderPrice;
	private String orderState;
	private String orderMemo;
	private String createdate;
	
	public Orders() {}

	public Orders(int orderCode, String customerId, int addressCode, int orderQuantity, int orderPrice,
			String orderState, String orderMemo, String createdate) {
		super();
		this.orderCode = orderCode;
		this.customerId = customerId;
		this.addressCode = addressCode;
		this.orderQuantity = orderQuantity;
		this.orderPrice = orderPrice;
		this.orderState = orderState;
		this.orderMemo = orderMemo;
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "Orders [orderCode=" + orderCode + ", customerId=" + customerId + ", addressCode=" + addressCode
				+ ", orderQuantity=" + orderQuantity + ", orderPrice=" + orderPrice + ", orderState=" + orderState
				+ ", orderMemo=" + orderMemo + ", createdate=" + createdate + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public int getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public int getAddressCode() {
		return addressCode;
	}

	public void setAddressCode(int addressCode) {
		this.addressCode = addressCode;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getOrderMemo() {
		return orderMemo;
	}

	public void setOrderMemo(String orderMemo) {
		this.orderMemo = orderMemo;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
}
