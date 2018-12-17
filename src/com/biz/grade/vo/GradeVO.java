package com.biz.grade.vo;

public class GradeVO {
	
	private String strNum; // 학번
	private String strName; // 이름
	private String strTel; // 전화
	private String strAddr; // 주소

	private int intKor;
	private int intEng;
	private int intMath;
	public String getStrNum() {
		return strNum;
	}
	public void setStrNum(String strNum) {
		this.strNum = strNum;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	public String getStrTel() {
		return strTel;
	}
	public void setStrTel(String strTel) {
		this.strTel = strTel;
	}
	public String getStrAddr() {
		return strAddr;
	}
	public void setStrAddr(String strAddr) {
		this.strAddr = strAddr;
	}
	public int getIntKor() {
		return intKor;
	}
	public void setIntKor(int intKor) {
		this.intKor = intKor;
	}
	public int getIntEng() {
		return intEng;
	}
	public void setIntEng(int intEng) {
		this.intEng = intEng;
	}
	public int getIntMath() {
		return intMath;
	}
	public void setIntMath(int intMath) {
		this.intMath = intMath;
	}
	@Override
	public String toString() {
		return "GradeVO [strNum=" + strNum + ", strName=" + strName + ", strTel=" + strTel + ", strAddr=" + strAddr
				+ ", intKor=" + intKor + ", intEng=" + intEng + ", intMath=" + intMath + "]";
	}
	
		
}
