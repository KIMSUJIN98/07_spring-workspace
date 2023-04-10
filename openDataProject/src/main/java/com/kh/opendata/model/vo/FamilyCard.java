package com.kh.opendata.model.vo;

public class FamilyCard {
	
	private String cpCompname; 	// 참여업체명
	private String cpCeoname; 	// 대표자명
	private String cpSanum;		// 사업자번호
	private String cpSidate;	// 시행일
	private String cpAddr;		// 주소
	private String cpTel;		// 연락처
	
	public FamilyCard() {}

	public FamilyCard(String cpCompname, String cpCeoname, String cpSanum, String cpSidate, String cpAddr,
			String cpTel) {
		super();
		this.cpCompname = cpCompname;
		this.cpCeoname = cpCeoname;
		this.cpSanum = cpSanum;
		this.cpSidate = cpSidate;
		this.cpAddr = cpAddr;
		this.cpTel = cpTel;
	}

	public String getCpCompname() {
		return cpCompname;
	}

	public void setCpCompname(String cpCompname) {
		this.cpCompname = cpCompname;
	}

	public String getCpCeoname() {
		return cpCeoname;
	}

	public void setCpCeoname(String cpCeoname) {
		this.cpCeoname = cpCeoname;
	}

	public String getCpSanum() {
		return cpSanum;
	}

	public void setCpSanum(String cpSanum) {
		this.cpSanum = cpSanum;
	}

	public String getCpSidate() {
		return cpSidate;
	}

	public void setCpSidate(String cpSidate) {
		this.cpSidate = cpSidate;
	}

	public String getCpAddr() {
		return cpAddr;
	}

	public void setCpAddr(String cpAddr) {
		this.cpAddr = cpAddr;
	}

	public String getCpTel() {
		return cpTel;
	}

	public void setCpTel(String cpTel) {
		this.cpTel = cpTel;
	}

	@Override
	public String toString() {
		return "FamilyCard [cpCompname=" + cpCompname + ", cpCeoname=" + cpCeoname + ", cpSanum=" + cpSanum
				+ ", cpSidate=" + cpSidate + ", cpAddr=" + cpAddr + ", cpTel=" + cpTel + "]";
	}

	

}
