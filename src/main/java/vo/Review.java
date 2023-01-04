package vo;

public class Review {
	private int orderCod;
	private String reviewMemo;
	private String createdate;
	
	public Review() {}

	public Review(int orderCod, String reviewMemo, String createdate) {
		super();
		this.orderCod = orderCod;
		this.reviewMemo = reviewMemo;
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "Review [orderCod=" + orderCod + ", reviewMemo=" + reviewMemo + ", createdate=" + createdate
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	public int getOrderCod() {
		return orderCod;
	}

	public void setOrderCod(int orderCod) {
		this.orderCod = orderCod;
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
