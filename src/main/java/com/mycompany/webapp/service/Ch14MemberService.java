package com.mycompany.webapp.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.Ch14MemberDao;
import com.mycompany.webapp.dto.Ch14Member;

@Service
public class Ch14MemberService {
	private static final Logger logger = LoggerFactory.getLogger(Ch14MemberService.class);

	// 열거 타입 선언
	public enum JoinResult {
		SUCCESS, FAIL, DUPLICATED
	}

	@Resource
	private Ch14MemberDao memberDao;

	// 회원 가입을 처리하는 비즈니스 메소드(로직)
	public JoinResult join(Ch14Member member) {
		try {
			// 이미 가입된 아이디인지 확인
			Ch14Member dbMember = memberDao.selectByMid(member.getMid());// SELECT * FROM member WHERE mid=?

			// DB에 회원 정보를 저장
			if (dbMember == null) {
				memberDao.insert(member);
				return JoinResult.SUCCESS;
			} else {
				return JoinResult.DUPLICATED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JoinResult.FAIL;
		}
	}

	public enum LoginResult {
		SUCCESS, FAIL, IDNULL, PWNULL, IDNOTEXIST, PWINCORRECT
	}

	@Resource
	private Ch14MemberDao memberLoginDao;

	// 로그인을 처리하는 메소드
	public LoginResult login(Ch14Member member) {
		try {
			String mid = member.getMid();
			String mpassword = member.getMpassword();

			if (mid == null || mid.trim().equals("")) { // ID 입력 공백
				return LoginResult.IDNULL;
			}
			if (mpassword == null || mpassword.trim().equals("")) { // PW 입력 공백
				return LoginResult.PWNULL;
			}

			Ch14Member dbmember = memberLoginDao.selectByMid(mid); // 데이터베이스 정보

			if (dbmember == null) { // ID 존재 안할 시
				return LoginResult.IDNOTEXIST;
			}
			if (!member.getMpassword().equals(dbmember.getMpassword())) { // PW 틀릴 시
				logger.info("member pw: " + member.getMpassword());
				logger.info("dbmember pw: " + dbmember.getMpassword());
				return LoginResult.PWINCORRECT;
			}
			return LoginResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return LoginResult.FAIL;
		}
	}
}
