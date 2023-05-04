package com.ncs.test.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ncs.test.member.model.service.MemberServiceImpl;
import com.ncs.test.member.model.vo.Member;

@Controller
public class MemberController {
	
	@Autowired
	private MemberServiceImpl mService;
	
	@RequestMapping("login")
	public String memberLogin(Member m, HttpSession session) {
		
		Member loginMember = mService.loginMember(m);
		
		if(loginMember == null) {
			session.setAttribute("msg", "로그인 실패");
		}else {
			session.setAttribute("loginMember", loginMember);
		}
		return "redirect:/";
	}
	
}
