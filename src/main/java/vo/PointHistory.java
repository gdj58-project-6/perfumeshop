package vo;

public class PointHistory {
	private int goodsCode;
	private String pointKind;
	private int point;
	private int createdate;
	
	public PointHistory() {}

	public PointHistory(int goodsCode, String pointKind, int point, int createdate) {
		super();
		this.goodsCode = goodsCode;
		this.pointKind = pointKind;
		this.point = point;
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "PointHistory [goodsCode=" + goodsCode + ", pointKind=" + pointKind + ", point=" + point
				+ ", createdate=" + createdate + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public int getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(int goodsCode) {
		this.goodsCode = goodsCode;
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
