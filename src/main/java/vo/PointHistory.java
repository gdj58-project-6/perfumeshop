package vo;

public class PointHistory {
	private int orderCode;
	private String pointKind;
	private int point;
	private int createdate;
	
	public PointHistory() {}

	public PointHistory(int orderCode, String pointKind, int point, int createdate) {
		super();
		this.orderCode = orderCode;
		this.pointKind = pointKind;
		this.point = point;
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "PointHistory [orderCode=" + orderCode + ", pointKind=" + pointKind + ", point=" + point
				+ ", createdate=" + createdate + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public int getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
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

	public int getCreatedate() {
		return createdate;
	}

	public void setCreatedate(int createdate) {
		this.createdate = createdate;
	}
	
	
}
