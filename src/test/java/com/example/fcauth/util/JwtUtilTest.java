package com.example.fcauth.util;

import com.example.fcauth.model.Employee;
import com.example.fcauth.model.Role;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

  @Test
  void test() {
    Employee employee = Employee.builder()
            .kakaoNickName("test")
            .build();

    String token = JwtUtil.createToken(employee);

    assertEquals(employee.getKakaoNickName(), JwtUtil.parseToken(token).get("nickname", String.class));
  }

  @Test
  void test_role() {
    Role role1 = Role.builder().id(1L).name("role1").build();
    Role role2 = Role.builder().id(2L).name("role2").build();

    Employee employee = Employee.builder().kakaoNickName("test").roles(Set.of(role1, role2)).build();
    String token = JwtUtil.createToken(employee);
    assertEquals(employee.getKakaoNickName(), JwtUtil.parseToken(token).get("nickname", String.class));
    assertTrue(employee.getRoles().contains(role1));
    assertTrue(employee.getRoles().contains(role2));
  }
}
