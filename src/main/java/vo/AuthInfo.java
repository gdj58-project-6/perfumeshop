package vo;

public class AuthInfo {
	private int authCode;
	private String authMemo;
	private String createdate;
	
	// 생성자
	public AuthInfo() {}
	
	public AuthInfo(int authCode, String authMemo, String createdate) {
		this.authCode = authCode;
		this.authMemo = authMemo;
		this.createdate = createdate;
	}

	// 부모 메서드를 자식단에서 재정의
	// 디버깅시 객체 멤버를 출력할때 사용
	@Override
	public String toString() {
		return "Auth_info [authCode=" + authCode + ", authMemo=" + authMemo + ", createdate=" + createdate + "]";
	}

	// get, set
	public int getAuthCode() {
		return authCode;
	}

	public void setAuthCode(int authCode) {
		this.authCode = authCode;
	}

	public String getAuthMemo() {
		return authMemo;
	}

	public void setAuthMemo(String authMemo) {
		this.authMemo = authMemo;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
	
	
}
