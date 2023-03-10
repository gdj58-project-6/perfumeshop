package vo;

public class Goods {
	private int goodsCode;
	private String goodsName;
	private int goodsPrice;
	private String goodsCategory;
	private String goodsMemo;
	private String soldout;
	private String empId;
	private int hit;
	private String createdate;
	
	// 생성자
	public Goods()  {}

	public Goods(int goodsCode, String goodsName, int goodsPrice, String goodsCategory, String goodsMemo,
			String soldout, String empId, int hit, String createdate) {
		super();
		this.goodsCode = goodsCode;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.goodsCategory = goodsCategory;
		this.goodsMemo = goodsMemo;
		this.soldout = soldout;
		this.empId = empId;
		this.hit = hit;
		this.createdate = createdate;
	}
	
	// 부모 메서드를 자식단에서 재정의해서 멤버 객체 출력(디버깅시사용)
	@Override
	public String toString() {
		return "Goods [goodsCode=" + goodsCode + ", goodsName=" + goodsName + ", goodsPrice=" + goodsPrice
				+ ", goodsCategory=" + goodsCategory + ", goodsMemo=" + goodsMemo + ", soldout=" + soldout + ", empId="
				+ empId + ", hit=" + hit + ", createdate=" + createdate + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	// get, set
	public int getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(int goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsCategory() {
		return goodsCategory;
	}

	public void setGoodsCategory(String goodsCategory) {
		this.goodsCategory = goodsCategory;
	}

	public String getGoodsMemo() {
		return goodsMemo;
	}

	public void setGoodsMemo(String goodsMemo) {
		this.goodsMemo = goodsMemo;
	}

	public String getSoldout() {
		return soldout;
	}

	public void setSoldout(String soldout) {
		this.soldout = soldout;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
}
