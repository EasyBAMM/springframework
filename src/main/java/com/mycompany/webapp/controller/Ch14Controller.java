package com.mycompany.webapp.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.webapp.dto.Ch14Board;
import com.mycompany.webapp.dto.Ch14Member;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.service.Ch14BoardService;
import com.mycompany.webapp.service.Ch14MemberLoginService;
import com.mycompany.webapp.service.Ch14MemberLoginService.LoginResult;
import com.mycompany.webapp.service.Ch14MemberService;
import com.mycompany.webapp.service.Ch14MemberService.JoinResult;

@Controller
@RequestMapping("/ch14")
public class Ch14Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch14Controller.class);

	// 필드 의존성 주입
	@Resource
	private DataSource dataSource;

	@RequestMapping("/content")
	public String content() {
		return "ch14/content";
	}

	@GetMapping("/testConnectToDB")
	public String testConnectToDB() throws Exception {
		// 커넥션 풀에서 연결 객체 하나를 가져오기
		Connection conn = dataSource.getConnection();
		logger.info("연결 성공");

		// 연결 객체를 반납하기
		conn.close();

		return "redirect:/ch14/content";
	}

	// Insert
	@GetMapping("/testInsert")
	public String testInsert() throws Exception {

		// 커넥션 풀에서 연결 객체 하나를 가져오기
		Connection conn = dataSource.getConnection();

		try {
			// 작업 처리
			String sql = "INSERT INTO board VALUES(SEQ_BNO.NEXTVAL, ?, ?, SYSDATE, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "오늘은 월요일");
			pstmt.setString(2, "스트레스가 이빠이 올라갔어요.");
			pstmt.setString(3, "user");
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 커넥션 풀로 연결 객체를 반납하기
		conn.close();

		return "redirect:/ch14/content";
	}

	// Select
	@GetMapping("/testSelect")
	public String testSelect() throws Exception {

		// 커넥션 풀에서 연결 객체 하나를 가져오기
		Connection conn = dataSource.getConnection();

		try {
			// 작업 처리
			String sql = "SELECT bno, btitle, bcontent, bdate, mid FROM board";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int bno = rs.getInt("bno");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				Date bdate = rs.getDate("bdate");
				String mid = rs.getString("mid");

				System.out.println(bno + " : " + btitle + " : " + bcontent + " : " + bdate + " : " + mid);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 커넥션 풀로 연결 객체를 반납하기
		conn.close();

		return "redirect:/ch14/content";
	}

	// Update
	@GetMapping("/testUpdate")
	public String testUpdate() throws Exception {

		// 커넥션 풀에서 연결 객체 하나를 가져오기
		Connection conn = dataSource.getConnection();

		try {
			// 작업 처리
			String sql = "UPDATE board SET btitle=?, bcontent=? WHERE bno=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "배고파요");
			pstmt.setString(2, "점심 먹으러 언제 가요?");
			pstmt.setString(3, "81");
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 연결 객체를 반납하기
		conn.close();

		return "redirect:/ch14/content";
	}

	// Delete
	@GetMapping("/testDelete")
	public String testDelete() throws Exception {

		// 커넥션 풀에서 연결 객체 하나를 가져오기
		Connection conn = dataSource.getConnection();

		try {
			// 작업 처리
			String sql = "DELETE FROM board WHERE bno=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 81);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 연결 객체를 반납하기
		conn.close();

		return "redirect:/ch14/content";
	}

	// 회원가입 처리
	@Resource
	private Ch14MemberService memberService;

	@GetMapping("/join")
	public String joinForm() {
		return "ch14/joinForm";
	}

	// Join
	@PostMapping("/join")
	public String join(Ch14Member member, Model model) throws Exception {
		member.setMenabled(1);
		member.setMrole("ROLE_USER");
		JoinResult joinResult = memberService.join(member);
		if (joinResult == JoinResult.SUCCESS) {
			return "redirect:/ch14/content";
		} else if (joinResult == JoinResult.DUPLICATED) {
			model.addAttribute("error", "중복된 아이디가 있습니다.");
			return "ch14/joinForm";
		} else { // JoinResult.FAIL
			model.addAttribute("error", "회원가입이 실패되었습니다. 다시 시도해주세요.");
			return "ch14/joinForm";
		}
	}

	// 로그인 처리
	@Resource
	private Ch14MemberLoginService memberLoginService;

	@GetMapping("/login")
	public String loginForm() {
		return "ch14/loginForm";
	}

	// Join
	@PostMapping("/login")
	public String login(Ch14Member member, Model model) throws Exception {
		logger.info("실행");
		LoginResult loginResult = memberLoginService.login(member);
		if (loginResult == LoginResult.SUCCESS) {
			return "redirect:/ch14/content";
		} else if (loginResult == LoginResult.IDNULL) {
			model.addAttribute("error", "아이디를 입력해주세요.");
			return "ch14/loginForm";
		} else if (loginResult == LoginResult.PWNULL) {
			model.addAttribute("error", "비밀번호를 입력해주세요.");
			return "ch14/loginForm";
		} else if (loginResult == LoginResult.IDNOTEXIST) {
			model.addAttribute("error", "입력한 아이디가 존재하지 않습니다.");
			return "ch14/loginForm";
		} else if (loginResult == LoginResult.PWINCORRECT) {
			model.addAttribute("error", "비밀번호가 틀렸습니다.");
			return "ch14/loginForm";
		} else {
			model.addAttribute("error", "회원가입이 실패되었습니다. 다시 시도해주세요.");
			return "ch14/joinForm";
		}
	}

	// 게시판
	@Resource
	private Ch14BoardService boardService;

	// 글목록
	@GetMapping("/boardList")
	public String boardList(@RequestParam(defaultValue = "1") int pageNo, Model model) {
		int totalRows = boardService.getTotalBoardNum();
		Pager pager = new Pager(5, 5, totalRows, pageNo);
		model.addAttribute("pager", pager);

		List<Ch14Board> boards = boardService.getBoards(pager);
		model.addAttribute("boards", boards);
		return "ch14/boardList";
	}

	// 글 보기
	@GetMapping("/boardDetail")
	public String boardDetail(int bno, Model model) {
		Ch14Board board = boardService.getBoard(bno);
		model.addAttribute("board", board);
		return "ch14/boardDetail";
	}

	// 글 작성
	@GetMapping("/boardWriteForm")
	public String boardWriteForm() {
		return "ch14/boardWriteForm";
	}

	@PostMapping("/boardWrite")
	public String boardWrite(Ch14Board board) {
		boardService.writeBoard(board);
		return "redirect:/ch14/boardList";
	}

	// 글 수정
	@GetMapping("/boardUpdateForm")
	public String boardUpdateForm(int bno, Model model) {
		Ch14Board board = boardService.getBoard(bno);
		model.addAttribute("board", board);
		return "ch14/boardUpdateForm";
	}

	@PostMapping("/boardUpdate")
	public String boardUpdate(Ch14Board board) {
		boardService.updateBoard(board);
		return "redirect:/ch14/boardDetail?bno=" + board.getBno();
	}

	// 글 삭제
	@GetMapping("/boardDelete")
	public String boardDelete(int bno) {
		boardService.removeBoard(bno);
		return "redirect:/ch14/boardList";
	}
}
