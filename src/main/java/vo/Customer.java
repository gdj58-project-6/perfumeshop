package vo;

public class Customer {
	private int customerCode;
	private String customerId;
	private String customerPw;
	private String customerName;
	private String customerPhone;
	private int point;
	private int authCode;
	private String createdate;
	
	public Customer() {}

	@Override
	public String toString() {
		return "Customer [customerCode=" + customerCode + ", customerId=" + customerId + ", customerPw=" + customerPw
				+ ", customerName=" + customerName + ", customerPhone=" + customerPhone + ", point=" + point
				+ ", authCode=" + authCode + ", createdate=" + createdate + "]";
	}

	public Customer(int customerCode, String customerId, String customerPw, String customerName, String customerPhone,
			int point, int authCode, String createdate) {
		super();
		this.customerCode = customerCode;
		this.customerId = customerId;
		this.customerPw = customerPw;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.point = point;
		this.authCode = authCode;
		this.createdate = createdate;
	}

	public int getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(int customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerPw() {
		return customerPw;
	}

	public void setCustomerPw(String customerPw) {
		this.customerPw = customerPw;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getAuthCode() {
		return authCode;
	}

	public void setAuthCode(int authCode) {
		this.authCode = authCode;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

}
