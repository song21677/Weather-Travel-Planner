package com.olaenmanijo.weatherbasedtravelplanner.domain.member.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MemberResponse {

	private Long memberNo;
	private String memberId;
	private String password;
	private String nickname;
	private Character gender;
	private Long age;
	private String email;
	private LocalDateTime registerDate;
	private LocalDateTime updateDate;
	private Boolean deleteYN;
	
	public void clearPassword() {
        this.password = "";
    }
}
