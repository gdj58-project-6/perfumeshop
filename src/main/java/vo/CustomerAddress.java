package vo;

public class CustomerAddress {
	private int addressCode;
	private String cutomerId;
	private String address;
	private String createdate;
	
	// 생성자
	public CustomerAddress() {}

	public CustomerAddress(int addressCode, String cutomerId, String address, String createdate) {
		super();
		this.addressCode = addressCode;
		this.cutomerId = cutomerId;
		this.address = address;
		this.createdate = createdate;
	}

	// 부모 메서드를 자식단에서 재정의해서 멤버 객체 출력(디버깅시사용)
	@Override
	public String toString() {
		return "CustomerAddress [addressCode=" + addressCode + ", cutomerId=" + cutomerId + ", address=" + address
				+ ", createdate=" + createdate + "]";
	}

	// get, set
	public int getAddressCode() {
		return addressCode;
	}

	public void setAddressCode(int addressCode) {
		this.addressCode = addressCode;
	}

	public String getCutomerId() {
		return cutomerId;
	}

	public void setCutomerId(String cutomerId) {
		this.cutomerId = cutomerId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
	
	
	
}
