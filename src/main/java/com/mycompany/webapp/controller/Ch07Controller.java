package com.mycompany.webapp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.webapp.dto.Ch07Board;
import com.mycompany.webapp.dto.Ch07City;
import com.mycompany.webapp.dto.Ch07Cloth;
import com.mycompany.webapp.dto.Ch07Member;

@Controller
@RequestMapping("/ch07")
public class Ch07Controller {

	private static final Logger logger = LoggerFactory.getLogger(Ch07Controller.class);

	@RequestMapping("/content")
	public String content() {
		return "ch07/content";
	}

	@GetMapping("/saveData")
	public String saveData(HttpServletRequest request) {
		logger.info("실행");

		// Request 범위에 데이터를 저장
		request.setAttribute("requestData", "자바");

		// Session 범위에 데이터를 저장
		HttpSession session = request.getSession();
		session.setAttribute("sessionData", "자바스크립트");

		// Application 범위에 데이터를 저장
		ServletContext application = request.getServletContext();
		application.setAttribute("applicationData", "백-엔드 스프링프레임워크");

		return "ch07/readData";
	}

	@GetMapping("/readData")
	public String readData() {
		logger.info("실행");
		return "ch07/readData";
	}

	// request에 object 저장하고 읽기
	@GetMapping("/objectSaveAndRead1")
	public String objectSaveAndRead1(HttpServletRequest request) {
		logger.info("실행");

		Ch07Member member = new Ch07Member();
		member.setName("홍길동");
		member.setAge(25);
		member.setJob("프로그래머");
		Ch07City city = new Ch07City();
		city.setName("서울");
		member.setCity(city);

		request.setAttribute("member", member);

		return "ch07/objectRead";
	}

	// ModelAndView에 object 저장하고 읽기
	@GetMapping("/objectSaveAndRead2")
	public ModelAndView objectSaveAndRead2() {
		logger.info("실행");

		Ch07Member member = new Ch07Member();
		member.setName("홍길동");
		member.setAge(25);
		member.setJob("프로그래머");
		Ch07City city = new Ch07City();
		city.setName("서울");
		member.setCity(city);

		ModelAndView mav = new ModelAndView();
		mav.addObject("member", member); // request 범위에 저장함
		mav.setViewName("ch07/objectRead");

		return mav;
	}

	// 요즘 방법, Model 매개변수에 값 저장
	@GetMapping("/objectSaveAndRead3")
	public String objectSaveAndRead3(Model model) {
		logger.info("실행");

		Ch07Member member = new Ch07Member();
		member.setName("홍길동");
		member.setAge(25);
		member.setJob("프로그래머");
		Ch07City city = new Ch07City();
		city.setName("서울");
		member.setCity(city);

		model.addAttribute("member", member); // request 범위에 저장함

		return "ch07/objectRead";
	}

	// JSTL(Java Standartd Tag Library)
	@GetMapping("/useJstl1")
	public String useJstl1(Model model) {
		logger.info("실행");

		String[] languages = { "Java", "JavaScript", "SpringFramework", "Vue" };
		model.addAttribute("langs", languages);

		return "ch07/useJstl1";
	}

	// JSTL(Java Standartd Tag Library)
	@GetMapping("/useJstl2")
	public String useJstl2(Model model) {
		logger.info("실행");

		List<Ch07Board> list = new ArrayList<>();

		for (int i = 1; i <= 5; i++) {
			list.add(new Ch07Board(i, "제목" + i, "내용" + i, "글쓴이" + i, new Date()));
		}

		model.addAttribute("boardList", list);

		return "ch07/useJstl2";
	}

	// @ModelAttribute로 전역적으로 object 전달
	// model.addAttribute("colors", colors)와 같음, 그러나 Request 범위에 저장됨
	@ModelAttribute("colors")
	public String[] getColors() {
		logger.info("실행");
		String[] colors = { "Red", "Green", "Blue", "Yellow", "Pink" };
		return colors;
	}

	@GetMapping("/useModelAttribute1")
	public String useModelAttribute1() {
		logger.info("실행");

		return "ch07/useModelAttribute";
	}

	@GetMapping("/useModelAttribute2")
	public String useModelAttribute2() {
		logger.info("실행");

		return "ch07/useModelAttribute";
	}

	// 매개변수 값을 바로 저장하기 @ModelAttribute("이름") 반드시 이름을 줘야함
	@GetMapping("/argumentSaveAndRead1")
	public String argumentSaveAndRead1(@ModelAttribute("kind") String kind, @ModelAttribute("sex") String sex) {
		logger.info("실행");

		logger.info("kind: " + kind);
		logger.info("sex: " + sex);

		return "ch07/argumentRead1";
	}

	// 매개변수 값을 바로 저장하기 @ModelAttribute("이름") 객체(= 커맨드객체)
	// 이름 부여안 할 시 객체 타입은 첫 글자를 소문자로 해서 관리된다. ex) ch07Cloth
	@GetMapping("/argumentSaveAndRead2")
	public String argumentSaveAndRead2(@ModelAttribute("cloth") Ch07Cloth cloth) {
		logger.info("실행");

		logger.info("kind: " + cloth.getKind());
		logger.info("sex: " + cloth.getSex());

		return "ch07/argumentRead1";
	}

}
