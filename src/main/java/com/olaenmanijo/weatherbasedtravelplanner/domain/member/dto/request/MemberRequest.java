package com.olaenmanijo.weatherbasedtravelplanner.domain.member.dto.request;

import org.thymeleaf.util.StringUtils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.security.crypto.password.PasswordEncoder;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRequest {
	private Long memberNo;
	private String memberId;
	private String password;
	private String nickname;
	private String gender;
	private Long age;
	private String email;
	
	public void encodingPassword(PasswordEncoder passwordEncoder) {
        if (StringUtils.isEmpty(password)) {
            return;
        }
        password = passwordEncoder.encode(password);
    }

	
}
