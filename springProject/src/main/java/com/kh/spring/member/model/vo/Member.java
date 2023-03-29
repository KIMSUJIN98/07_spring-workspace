package com.kh.spring.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * * Lombok(롬복)  // = 코드다이어트
 * 
 * 1. 라이브러리 다운 후 적용 (Maven pom.xml)																	// Maven Repository 에서 다운로드 후 pom.xml 에서 셋팅
 * 2. 다운로드된 jar 찾아서 설치 (작업할 IDE 선택해서 설치) (CMD로 해당 경로 진입 후 java -jar lombok-1.18.12.jar)
 * 3. IDE 재실행
 */


@NoArgsConstructor		// 기본생성자 																			// arguments 매개변수가 없는 생성자
@AllArgsConstructor		// 매겨변수 생성자
@Setter					// setter 메소드
@Getter					// getter 메소드
@ToString				// toString 메소드
public class Member {

	private String userId;
	private String userPwd;
	private String userName;
	private String email;
	private String gender;
	//private int age;
	private String age; 											// 롬복(LomBok)을 사용하면 자동적으로 연동되며 바뀐다
	private String phone;
	private String address;
	private Date enrollDate;
	private Date modifyDate;
	private String status;
	
	// private String uName;
	// 단, 롬복을 쓸때는 필드명 작성시 적어도 소문자 두글자 이상으로 시작할 것		// 자동적으로 getter/setter 메소드 생성시 구분이 안되므로 에러발생 가능 => getUName(), setUName()
	
	
	
}
