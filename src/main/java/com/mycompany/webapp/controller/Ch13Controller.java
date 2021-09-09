package com.mycompany.webapp.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.service.Ch13Service;
import com.mycompany.webapp.service.Ch13Service1;
import com.mycompany.webapp.service.Ch13Service2;

@Controller
@RequestMapping("/ch13")
public class Ch13Controller {
	// Ch13Controller <- Ch13Service1 <- Ch13BoardDao1

	private static final Logger logger = LoggerFactory.getLogger(Ch13Controller.class);

	// XML으로 주입
	private Ch13Service1 ch13Service1;

	// 타입으로 객체를 찾아서 주입
	// @Autowired
	@Resource
	private Ch13Service2 ch13Service2;

	// 이름으로 객체를 찾아서 주입 - 인터페이스 여러 클래스 연결 되어있을 때
	@Resource(name = "ch13Service4")
	private Ch13Service ch13Service;

	public Ch13Controller() {
		logger.info("실행");
	}

	// 의존성 주입을 위한 Setter 선언
	public void setCh13Service1(Ch13Service1 ch13Service1) {
		logger.info("실행");
		this.ch13Service1 = ch13Service1;
	}

	/*
	 * @Autowired
	 *
	 * @Resource public void setCh13Service2(Ch13Service2 ch13Service2) {
	 * logger.info("실행"); this.ch13Service2 = ch13Service2; }
	 */

	@RequestMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch13/content";
	}

	@GetMapping("/request1")
	public String request1() {
		logger.info("실행");
		ch13Service1.method1();
		return "redirect:/ch13/content";
	}

	@GetMapping("/request2")
	public String request2() {
		logger.info("실행");
		ch13Service2.method1();
		return "redirect:/ch13/content";
	}

	@GetMapping("/request3")
	public String request3() {
		logger.info("실행");
		ch13Service.method2();
		return "redirect:/ch13/content";
	}

}
