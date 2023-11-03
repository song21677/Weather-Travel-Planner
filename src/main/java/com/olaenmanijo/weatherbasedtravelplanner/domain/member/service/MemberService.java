package com.olaenmanijo.weatherbasedtravelplanner.domain.member.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olaenmanijo.weatherbasedtravelplanner.domain.member.dto.request.MemberRequest;
import com.olaenmanijo.weatherbasedtravelplanner.domain.member.dto.response.MemberResponse;
import com.olaenmanijo.weatherbasedtravelplanner.domain.member.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;

	/**
	 * 회원 정보 저장 (회원가입)
	 * 
	 * @param params - 회원 정보
	 * @return PK
	 */
	@Transactional
	public Long saveMember(final MemberRequest params) {
		params.encodingPassword(passwordEncoder);
		memberMapper.save(params);
		return params.getMemberNo();
	}

	/**
	 * 회원 상세정보 조회
	 * 
	 * @param MEMBER_ID - UK
	 * @return 회원 상세정보
	 */
	public MemberResponse findMemberByLoginId(final String memberId) {
		return memberMapper.findByLoginId(memberId);
	}

	/**
	 * 회원 정보 수정
	 * 
	 * @param params - 회원 정보
	 * @return PK
	 */
	@Transactional
	public Long updateMember(final MemberRequest params) {
		params.encodingPassword(passwordEncoder);
		memberMapper.update(params);
		return params.getMemberNo();
	}

	/**
	 * 회원 정보 삭제 (회원 탈퇴)
	 * 
	 * @param MEMBER_NO - PK
	 * @return PK
	 */
	@Transactional
	public Long deleteMemberById(final long memberNo) {
		memberMapper.deleteById(memberNo);
		return memberNo;
	}

	/**
	 * 회원 수 카운팅 (ID 중복 체크)
	 * 
	 * @param MEMBER_ID - UK
	 * @return 회원 수
	 */
	public Long countMemberByLoginId(final String memberId) {
		return memberMapper.countByLoginId(memberId);
	}

	/**
	 * 로그인
	 * 
	 * @param memberId  - 로그인 ID
	 * @param password - 비밀번호
	 * @return 회원 상세정보
	 */
	public MemberResponse login(final String memberId, final String password) {
		System.out.println("login memberId : " + memberId);
		System.out.println("login password : " + password);
		// 1. 회원 정보 및 비밀번호 조회
		MemberResponse member = findMemberByLoginId(memberId);
		System.out.println("login member : " + member);
		String encodedPassword = (member == null) ? "" : member.getPassword();
		System.out.println("login encodedPassword : " + encodedPassword);

		// 2. 회원 정보 및 비밀번호 체크
		if (member == null || passwordEncoder.matches(password, encodedPassword) == false) {
			System.out.println("회원정보 및 비밀번호 체크 해서 null 값 리턴 : member "+ member + " match " + passwordEncoder.matches(password, encodedPassword));
			return null;
		}

		// 3. 회원 응답 객체에서 비밀번호를 제거한 후 회원 정보 리턴
		member.clearPassword();
		System.out.println("회원정보 및 비밀번호 제거한 후 회원 정보 리턴");
		return member;
	}
}
