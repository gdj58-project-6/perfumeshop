package vo;

public class GoodsQuestionComment {
	private int goodsCommentCode;
	private int goodsQuestionCode;
	private String goodsCommentMemo;
	private String createdate;
	
	// 생성자
	public GoodsQuestionComment() {}

	public GoodsQuestionComment(int goodsCommentCode, int goodsQuestionCode, String goodsCommentMemo,
			String createdate) {
		super();
		this.goodsCommentCode = goodsCommentCode;
		this.goodsQuestionCode = goodsQuestionCode;
		this.goodsCommentMemo = goodsCommentMemo;
		this.createdate = createdate;
	}

	// 자식단에서 재정의하여 디버깅하는데 사용
	@Override
	public String toString() {
		return "GoodsQuestionComment [goodsCommentCode=" + goodsCommentCode + ", goodsQuestionCode=" + goodsQuestionCode
				+ ", goodsCommentMemo=" + goodsCommentMemo + ", createdate=" + createdate + "]";
	}

	// get set
	public int getGoodsCommentCode() {
		return goodsCommentCode;
	}

	public void setGoodsCommentCode(int goodsCommentCode) {
		this.goodsCommentCode = goodsCommentCode;
	}

	public int getGoodsQuestionCode() {
		return goodsQuestionCode;
	}

	public void setGoodsQuestionCode(int goodsQuestionCode) {
		this.goodsQuestionCode = goodsQuestionCode;
	}

	public String getGoodsCommentMemo() {
		return goodsCommentMemo;
	}

	public void setGoodsCommentMemo(String goodsCommentMemo) {
		this.goodsCommentMemo = goodsCommentMemo;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
	
	
}
