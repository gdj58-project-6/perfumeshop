package vo;

public class Review {
	private int orderCode;
	private int goodsCode;
	private String reviewMemo;
	private String createdate;
	
	public Review() {}

	public Review(int orderCode, int goodsCode, String reviewMemo, String createdate) {
		super();
		this.orderCode = orderCode;
		this.goodsCode = goodsCode;
		this.reviewMemo = reviewMemo;
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "Review [orderCode=" + orderCode + ", goodsCode=" + goodsCode + ", reviewMemo=" + reviewMemo
				+ ", createdate=" + createdate + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public int getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

	public int getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(int goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getReviewMemo() {
		return reviewMemo;
	}

	public void setReviewMemo(String reviewMemo) {
		this.reviewMemo = reviewMemo;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
}
