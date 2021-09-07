package com.mycompany.webapp.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ch12")
public class Ch12Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch12Controller.class);

	@RequestMapping("/content")
	public String content() {
		return "ch12/content";
	}

	// 파일 이름 목록 및 파일의 총 개수 JSON 반환
	@GetMapping("/fileList")
	public String fileList() throws IOException {
		logger.info("실행");
		return "ch12FileListView";
	}

	// 파일다운로드 정석
	@GetMapping("/fileDownload")
	public String fileDownload(@ModelAttribute("fileName") String fileName,
			@ModelAttribute("userAgent") @RequestHeader("User-Agent") String userAgent) throws Exception {
		logger.info("실행");
		return "ch12FileDownloadView";
	}
}
