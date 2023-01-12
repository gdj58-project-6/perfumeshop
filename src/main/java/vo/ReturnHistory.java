package vo;

public class ReturnHistory {
	private int orderCode;
	private String customerId;
	private String returnMemo;
	private String returnState;
	private String createdate;
	
	public ReturnHistory() {}

	public ReturnHistory(int orderCode, String customerId, String returnMemo, String returnState, String createdate) {
		super();
		this.orderCode = orderCode;
		this.customerId = customerId;
		this.returnMemo = returnMemo;
		this.returnState = returnState;
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "ReturnHistory [orderCode=" + orderCode + ", customerId=" + customerId + ", returnMemo=" + returnMemo
				+ ", returnState=" + returnState + ", createdate=" + createdate + ", getClass()=" + getClass()
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

	public String getReturnMemo() {
		return returnMemo;
	}

	public void setReturnMemo(String returnMemo) {
		this.returnMemo = returnMemo;
	}

	public String getReturnState() {
		return returnState;
	}

	public void setReturnState(String returnState) {
		this.returnState = returnState;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
}
