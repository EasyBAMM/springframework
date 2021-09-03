package com.mycompany.webapp.controller;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.mycompany.webapp.dto.Ch08InputForm;

@Controller
@RequestMapping("/ch08")
@SessionAttributes({ "inputForm" })
public class Ch08Controller {

	private static final Logger logger = LoggerFactory.getLogger(Ch08Controller.class);

	@RequestMapping("/content")
	public String content() {

		return "ch08/content";
	}

	// Session 데이터 저장
	@GetMapping(value = "/saveData", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String saveData(String name, HttpSession session) {
		logger.info("실행");
		logger.info("name: " + name);

		// HttpSession session = request.getSession();
		session.setAttribute("name", name);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		String json = jsonObject.toString(); // {"result" : "success"}

		return json;
	}

	// Session 데이터 읽기
	@GetMapping(value = "/readData", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String readData(HttpSession session, @SessionAttribute String name) {
		logger.info("실행");

		// 방법 1(교수님 픽)
		// String name = (String) session.getAttribute("name");
		// logger.info("name: " + name);

		// 방법 2
		logger.info("name: " + name);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		String json = jsonObject.toString(); // {"name" : "홍길동"}

		return json;
	}

	// 로그인 폼
	@GetMapping("/login")
	public String loginForm() {
		logger.info("실행");

		return "ch08/loginForm";
	}

	// 로그인 세션 생성
	@PostMapping("/login")
	public String login(String mid, String mpassword, HttpSession session) {
		logger.info("실행");
		if (mid.equals("spring") && mpassword.equals("12345")) {
			session.setAttribute("sessionMid", mid);
		}

		return "redirect:/ch08/content";
	}

	// 로그아웃 세션 제거
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		logger.info("실행");

		// 방법 1
		session.removeAttribute("sessionMid");
		// 방법 2
		// session.invalidate(); // 모든 세션 지우기

		return "redirect:/ch08/content";
	}

	// AJAX 로그인 세션 생성
	@PostMapping(value = "/loginAjax", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String loginAjax(String mid, String mpassword, HttpSession session) {
		logger.info("실행");
		String result = "";

		if (!mid.equals("spring")) {
			result = "wrongMid";
		} else if (!mpassword.equals("12345")) {
			result = "wrongMpassword";
		} else if (mid.equals("spring") && mpassword.equals("12345")) {
			result = "success";
			session.setAttribute("sessionMid", mid);
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		String json = jsonObject.toString();

		return json;
	}

	// AJAX 로그아웃
	@GetMapping(value = "/logoutAjax", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String logoutAjax(HttpSession session) {
		logger.info("실행");

		session.invalidate();

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		String json = jsonObject.toString();

		return json;
	}

	// @SetAttributes - class에 적혀있음
	// 세션에 inputForm 이름이 존재하지 않을 경우 딱 한번 실행
	@ModelAttribute("inputForm")
	public Ch08InputForm getInputForm() {
		logger.info("실행");
		Ch08InputForm inputForm = new Ch08InputForm();
		return inputForm;
	}

	@GetMapping("/inputStep1")
	public String inputStep1(@ModelAttribute("inputForm") Ch08InputForm inputForm) {
		logger.info("실행");
		return "ch08/inputStep1";
	}

	@PostMapping("/inputStep2")
	public String inputStep2(@ModelAttribute("inputForm") Ch08InputForm inputForm) {
		logger.info("실행");
		logger.info("data1: " + inputForm.getData1());
		logger.info("data2: " + inputForm.getData2());
		logger.info("data3: " + inputForm.getData3());
		logger.info("data4: " + inputForm.getData4());
		return "ch08/inputStep2";
	}

	@PostMapping("/inputDone")
	public String inputDone(@ModelAttribute("inputForm") Ch08InputForm inputForm, SessionStatus sessionStatus) {
		logger.info("실행");
		logger.info("data1: " + inputForm.getData1());
		logger.info("data2: " + inputForm.getData2());
		logger.info("data3: " + inputForm.getData3());
		logger.info("data4: " + inputForm.getData4());
		// 처리 내용~
		// 세션에 저장되어 있는 inputForm을 제거, remove(사용하면 안됨)
		sessionStatus.setComplete();

		return "redirect:/ch08/content";
	}
}
