package vo;

public class pwHistory {
	private String customerId;
	private String pw;
	private String createdate;
	
	public pwHistory() {}

	public pwHistory(String customerId, String pw, String createdate) {
		super();
		this.customerId = customerId;
		this.pw = pw;
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "pwHistory [customerId=" + customerId + ", pw=" + pw + ", createdate=" + createdate + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
}
