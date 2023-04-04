package com.kh.ajax.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AjaxController {
	
	@RequestMapping("ajax1.do")
	public void ajaxMethod1(String name, int age, HttpServletResponse response) throws IOException {
		System.out.println(name);
		System.out.println(age);
		
		// 요청 처리를 위해 서비스 호출
		
		// 요청 처리가 다 됐다는 가정 하에 요청한 그 페이지에 응답할 데이터가 있을 경우
		String responseData = "응답 문자열 : " + name + "은(는)" + age + "살 입니다.";
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(responseData); // 예외 던지면 스프링이 알아서 예외처리해줌
	}

}
