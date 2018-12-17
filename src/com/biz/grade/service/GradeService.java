package com.biz.grade.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.biz.grade.contact.GradeContact;
import com.biz.grade.vo.GradeVO;

public class GradeService {
	
	String stdFile;		// 학생명부
	String scoreFile; 	// 점수표
	String gradeFile;	// 성적표처리(저장용)
	
	List<GradeVO> gradeList;	// 성적결과 List
	List<GradeVO> scoreList;	// 점수표를 저장할 List
	
	public GradeService(String [] files) {
		gradeList = new ArrayList();
		scoreList = new ArrayList();
		
		stdFile = files[GradeContact.FILES_STD];
		scoreFile = files[GradeContact.FILES_SCORE];
		gradeFile = files[GradeContact.FILES_GRADE];
	}
	// -학생리스트를 먼저 읽기-
	// 학생명부와 점수표가 각각의 파일로 존재할 경우
	// 점수표는 아직 집계가 덜 되어 미완성일 수 있지만
	// 최소 학생명부는 완성된 상태이므로 먼저 완성된 파일을 읽어서 
	// 데이터를 준비한다.
	public void stdFileReade() {
		//TODO 학생리스트 읽어오기
		FileReader fr;
		BufferedReader buffer;
		
		try {
			fr = new FileReader(stdFile);
			buffer = new BufferedReader(fr);
			
			while(true) {
				String reader = buffer.readLine();
				if(reader == null) break;
				
				String [] stds = reader.split(":");
				
				GradeVO vo = new GradeVO();
				vo.setStrNum(stds[GradeContact.ST_strNum]); //학번
				vo.setStrName(stds[GradeContact.ST_strName]); //이름
				vo.setStrTel(stds[GradeContact.ST_strTel]); // 전화번호
				vo.setStrAddr(stds[GradeContact.ST_strAddr]); // 주소
				
				gradeList.add(vo);
				
			}
			buffer.close();
			fr.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}
	
	public void writeScore() {
		//TODO 파일로 저장
	
		PrintWriter pw;
		try {
			pw = new PrintWriter(gradeFile);
			
			for(GradeVO vo : gradeList) {
				
				int intSum = vo.getIntKor();
				intSum += vo.getIntEng();
				intSum += vo.getIntMath();
				
				float floatAvg = (float) intSum/3;
				
				pw.printf("%3s:%s:%s:%s:%d:%d:%d:%d:%f\n",
						 vo.getStrNum(), vo.getStrName(),
						 vo.getStrTel(), vo.getStrAddr(),
						 vo.getIntKor(), vo.getIntEng(),
						 vo.getIntMath(),
						 intSum, floatAvg);
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void viewScore() {
		//TODO 보여주기
		System.out.println("================================================================");
		System.out.println("학번\t이름\t전화번호\t\t주소\t\t\t국어\t영어\t수학\t총점\t평균");
		System.out.println("================================================================");
		for(GradeVO vo : gradeList) {
			int intSum = vo.getIntKor();
			intSum += vo.getIntEng();
			intSum += vo.getIntMath();
			
			float floatAvg = (float) intSum/3;
			System.out.printf("%3s\t%-3s\t%-15.15s%-10.10s\t\t%3d\t%3d\t%3d\t%3d\t%3.2f\n",
					 vo.getStrNum(),
					 vo.getStrName(),
					 vo.getStrTel(),
					 vo.getStrAddr(),
					 vo.getIntKor(),
					 vo.getIntEng(),
					 vo.getIntMath(),
					 intSum,
					 floatAvg);
		}
		System.out.println("==============================================");
	}

	public void stdMatchScore() {
		// TODO 학생리스트와 점수리스트의 매칭
		for(GradeVO vo : scoreList ) {
			String strNum = vo.getStrNum();
			for(GradeVO st : gradeList) {
				if(st.getStrNum().equals(strNum)) {
					st.setIntKor(vo.getIntKor());
					st.setIntEng(vo.getIntEng());
					st.setIntMath(vo.getIntMath());
					
				}
				
			}
		
		
		}
			
	}
	
	public void scoreFileToList() {
	// TODO ??????????
		
		FileReader fr;
		BufferedReader buffer;
		
		try {
			fr = new FileReader(scoreFile);
			buffer = new BufferedReader(fr);
			
			while(true) {
				String reader = buffer.readLine();
				if(reader == null) break;
				
				String [] scores = reader.split(":");
			
				GradeVO vo = new GradeVO();
						
						int intKor = Integer.valueOf(scores[GradeContact.SC_intKor]);
						int intEng = Integer.valueOf(scores[GradeContact.SC_intEng]);
						int intMath = Integer.valueOf(scores[GradeContact.SC_intMath]);
						
						vo.setStrNum(scores[GradeContact.SC_strNum]);
						vo.setIntKor(intKor);
						vo.setIntEng(intEng);
						vo.setIntMath(intMath);
						gradeList.add(vo);
				}
				
			buffer.close();
			fr.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void scoreFileReader() {
		//TODO 점수표 읽기
		FileReader fr;
		BufferedReader buffer;
		
		try {
			fr = new FileReader(scoreFile);
			buffer = new BufferedReader(fr);
			
			while(true) {
				String reader = buffer.readLine();
				if(reader == null) break;
				
				String [] scores =reader.split(":");
				
				String strNum = scores[0];
				for(GradeVO vo : gradeList) {
					if(vo.getStrNum().equals(strNum)) {
						
						int intKor = Integer.valueOf(scores[1]);
						int intEng = Integer.valueOf(scores[2]);
						int intMath = Integer.valueOf(scores[3]);
						
						vo.setIntKor(intKor);
						vo.setIntEng(intEng);
						vo.setIntMath(intMath);
						
					}
					
				}
				
			}
			
			buffer.close();
			fr.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
