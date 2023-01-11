package vo;

public class OrderGoods {
	private int orderCode;
	private int goodsCode;
	private int orderGoodsPrice;
	private int orderGoodsQuantity;
	
	public OrderGoods() {}

	public OrderGoods(int orderCode, int goodsCode, int orderGoodsPrice, int orderGoodsQuantity) {
		super();
		this.orderCode = orderCode;
		this.goodsCode = goodsCode;
		this.orderGoodsPrice = orderGoodsPrice;
		this.orderGoodsQuantity = orderGoodsQuantity;
	}

	@Override
	public String toString() {
		return "OrderGoods [orderCode=" + orderCode + ", goodsCode=" + goodsCode + ", orderGoodsPrice="
				+ orderGoodsPrice + ", orderGoodsQuantity=" + orderGoodsQuantity + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
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

	public int getOrderGoodsPrice() {
		return orderGoodsPrice;
	}

	public void setOrderGoodsPrice(int orderGoodsPrice) {
		this.orderGoodsPrice = orderGoodsPrice;
	}

	public int getOrderGoodsQuantity() {
		return orderGoodsQuantity;
	}

	public void setOrderGoodsQuantity(int orderGoodsQuantity) {
		this.orderGoodsQuantity = orderGoodsQuantity;
	}
	
}
