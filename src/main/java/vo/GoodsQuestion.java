package vo;

public class GoodsQuestion {
	private int goodsQuestionCode;
	private int goodsCode;
	private String customerId;
	private String category;
	private String goodsQuestionMemo;
	private String createdate;
	
	
	// 생성자
	public GoodsQuestion() {}

	public GoodsQuestion(int goodsQuestionCode, int goodsCode, String customerId, String category,
			String goodsQuestionMemo, String createdate) {
		super();
		this.goodsQuestionCode = goodsQuestionCode;
		this.goodsCode = goodsCode;
		this.customerId = customerId;
		this.category = category;
		this.goodsQuestionMemo = goodsQuestionMemo;
		this.createdate = createdate;
	}

	// 디버깅 자식단에서 재정의해서 사용
	@Override
	public String toString() {
		return "GoodsQuestion [goodsQuestionCode=" + goodsQuestionCode + ", goodsCode=" + goodsCode + ", customerId="
				+ customerId + ", category=" + category + ", goodsQuestionMemo=" + goodsQuestionMemo + ", createdate="
				+ createdate + "]";
	}

	
	public int getGoodsQuestionCode() {
		return goodsQuestionCode;
	}

	public void setGoodsQuestionCode(int goodsQuestionCode) {
		this.goodsQuestionCode = goodsQuestionCode;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGoodsQuestionMemo() {
		return goodsQuestionMemo;
	}

	public void setGoodsQuestionMemo(String goodsQuestionMemo) {
		this.goodsQuestionMemo = goodsQuestionMemo;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	
	
	
	
	
}
