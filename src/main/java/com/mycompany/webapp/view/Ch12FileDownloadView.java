package com.mycompany.webapp.view;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

@Component
public class Ch12FileDownloadView extends AbstractView {

	private static final Logger logger = LoggerFactory.getLogger(Ch12FileListView.class);

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("실행");

		// request 범위에 있는 값 가져온다.
		String fileName = (String) model.get("fileName");
		String userAgent = (String) model.get("userAgent");

		// fileNo를 이용해서 DB에서 파일 정보를 가져오기
		String contentType = request.getServletContext().getMimeType(fileName);
		String originalFileName = fileName;
		String savedName = fileName;

		// 응답 바디의 데이터의 형식
		response.setContentType(contentType);

		// 브라우저별로 한글 파일명을 변환
		if (userAgent.contains("Trident") || userAgent.contains("MSIE")) {
			// IE일 경우
			originalFileName = URLEncoder.encode(originalFileName, "UTF-8");
		} else {
			// 크롬, 엣지, 사파리 브라우저일 경우
			originalFileName = new String(originalFileName.getBytes("UTF-8"), "ISO-8859-1");
		}

		// 파일을 첨부로 다운로드하도록 설정 - 헤더에는 ascii 값만 들어감
		response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFileName + "\"");

		// 파일로부터 데이터를 읽는 입력스트림 생성
		String filePath = "C:/hyundai_it&e/upload_files/" + savedName;
		InputStream is = new FileInputStream(filePath);

		// 응답 바디에 출력하는 출력스트림 얻기
		OutputStream os = response.getOutputStream();

		// 입력스트림 -> 출력스트림
		// FileCopyUtils.copy(is, os);
		byte[] data = new byte[1024];
		int readByteNum = -1;

		while ((readByteNum = is.read(data)) != -1) {
			os.write(data, 0, readByteNum);
			os.flush();
		}

		is.close();
		// os.flush();
		os.close();
	}

}
