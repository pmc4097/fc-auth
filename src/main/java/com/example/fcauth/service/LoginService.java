package com.example.fcauth.service;

import com.example.fcauth.model.KakaoUserInfoResDto;
import com.example.fcauth.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {
  private final KakaoService kakaoService;
  private final EmployeeRepository employeeRepository;

  public ResponseEntity<String> login(String code) {
    String accessToken = kakaoService.getAccessTokenFromKakao(code);
    KakaoUserInfoResDto userInfo =  kakaoService.getUserFromKakao(accessToken);
    String nickName = userInfo.getKakaoAccount().getProfile().getNickname();
    if (!employeeRepository.existsByKakaoNickName(nickName)) {
      return new ResponseEntity<>("등록된 임직원이 아닙니다.", HttpStatus.FORBIDDEN);
    }
    return new ResponseEntity<>("환영합니다. "+nickName , HttpStatus.OK);
  }
}
