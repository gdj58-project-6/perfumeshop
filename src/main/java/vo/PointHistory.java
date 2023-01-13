package vo;

public class PointHistory {
	private int orderCode;
	private int goodsCode;
	private String customerId;
	private String pointKind;
	private int point;
	private String createdate;
	
	public PointHistory() {}

	public PointHistory(int orderCode, int goodsCode, String customerId, String pointKind, int point,
			String createdate) {
		super();
		this.orderCode = orderCode;
		this.goodsCode = goodsCode;
		this.customerId = customerId;
		this.pointKind = pointKind;
		this.point = point;
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "PointHistory [orderCode=" + orderCode + ", goodsCode=" + goodsCode + ", customerId=" + customerId
				+ ", pointKind=" + pointKind + ", point=" + point + ", createdate=" + createdate + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getPointKind() {
		return pointKind;
	}

	public void setPointKind(String pointKind) {
		this.pointKind = pointKind;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
}
