package vo;

public class Cart {
	private int goodsCode;
	private String customerId;
	private int cartQuantity;
	private String createdate;
	
	// 생성자
	public Cart() {}

	public Cart(int goodsCode, String customerId, int cartQuantity, String createdate) {
		super();
		this.goodsCode = goodsCode;
		this.customerId = customerId;
		this.cartQuantity = cartQuantity;
		this.createdate = createdate;
	}
	
	// 부모 메서드를 자식단에서 재정의 디버깅시 객체 멤버 출력할때 사용
	@Override
	public String toString() {
		return "Cart [goodsCode=" + goodsCode + ", customerId=" + customerId + ", cartQuantity=" + cartQuantity
				+ ", createdate=" + createdate + "]";
	}

	// get, set
	public int getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(int goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public int getCartQuantity() {
		return cartQuantity;
	}

	public void setCartQuantity(int cartQuantity) {
		this.cartQuantity = cartQuantity;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
	
}
