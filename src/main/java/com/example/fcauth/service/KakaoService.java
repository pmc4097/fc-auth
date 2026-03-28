package com.example.fcauth.service;

import com.example.fcauth.model.KakaoTokenResDto;
import com.example.fcauth.model.KakaoUserInfoResDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;

@Service
public class KakaoService {

  private final String KAKAO_AUTH_URL = "https://kauth.kakao.com";
  private final String KAKAO_USER_URL = "https://kapi.kakao.com";
  @Value("${kakao.client_id}")
  private String clientId;
  @Value("${kakao.redirect_url}")
  private String redirectUri;

  @Value("${kakao.secret}")
  private String secret;

  public String getAccessTokenFromKakao(String code) {
    KakaoTokenResDto res = WebClient.create(KAKAO_AUTH_URL)
            .post()
            .uri(uriBuilder -> uriBuilder.scheme("https")
                    .path("/oauth/token")
                    .queryParam("grant_type", "authorization_code")
                    .queryParam("client_id", clientId)
                    .queryParam("redirect_uri", redirectUri)
                    .queryParam("code", code)
                    .queryParam("client_secret", secret)
                    .build())
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .retrieve()
            .bodyToMono(KakaoTokenResDto.class)
            .block();

    return res != null ? res.getAccessToken() : null;
  }

  public KakaoUserInfoResDto getUserFromKakao(String accessToken) {
    return  WebClient.create(KAKAO_USER_URL)
            .post()
            .uri(uriBuilder -> uriBuilder.scheme("https").path("/v2/user/me").build())
            .header(HttpHeaders.AUTHORIZATION, "Bearer "+accessToken)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .header(HttpHeaders.ACCEPT_CHARSET, String.valueOf(StandardCharsets.UTF_8))
            .retrieve()
            .bodyToMono(KakaoUserInfoResDto.class)
            .block();
  }
}
