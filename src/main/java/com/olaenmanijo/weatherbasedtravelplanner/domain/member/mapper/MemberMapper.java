package com.olaenmanijo.weatherbasedtravelplanner.domain.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.olaenmanijo.weatherbasedtravelplanner.domain.member.dto.request.MemberRequest;
import com.olaenmanijo.weatherbasedtravelplanner.domain.member.dto.response.MemberResponse;

@Mapper
public interface MemberMapper {
	/**
     * 회원 정보 저장 (회원가입)
     * @param params - 회원 정보
     */
    void save(MemberRequest params);

    /**
     * 회원 상세정보 조회
     * @param memberId - UK
     * @return 회원 상세정보
     */
    MemberResponse findByLoginId(String memberId);

    /**
     * 회원 정보 수정
     * @param params - 회원 정보
     */
    void update(MemberRequest params);

    /**
     * 회원 정보 삭제 (회원 탈퇴)
     * @param memberNo - PK
     */
    void deleteById(Long memberNo);

    /**
     * 회원 수 카운팅 (ID 중복 체크)
     * @param memberId - UK
     * @return 회원 수
     */
    long countByLoginId(String memberId);
}
