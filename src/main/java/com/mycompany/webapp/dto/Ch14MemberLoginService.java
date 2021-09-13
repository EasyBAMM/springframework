package com.mycompany.webapp.dto;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.Ch14MemberDao;

@Service
public class Ch14MemberLoginService {
	private static final Logger logger = LoggerFactory.getLogger(Ch14MemberLoginService.class);

	public enum LoginResult {
		SUCCESS, FAIL, IDNULL, PWNULL, IDNOTEXIST, PWINCORRECT
	}

	@Resource
	private Ch14MemberDao memberLoginDao;

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
