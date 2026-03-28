package com.example.fcauth.controller;

import com.example.fcauth.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/kakao")
@RequiredArgsConstructor
public class KakaoLoginController {

  private final LoginService loginService;

  @GetMapping("/callback")
  public ResponseEntity<String> callback(@RequestParam("code") String code) {
    return loginService.login(code);
  }
}
