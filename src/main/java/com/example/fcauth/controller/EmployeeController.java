package com.example.fcauth.controller;

import com.example.fcauth.model.Employee;
import com.example.fcauth.service.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name = "기본관리 API")
public class EmployeeController {
  private final EmployeeService employeeService;

  @GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Employee>> listAll() {
    return ResponseEntity.ok(employeeService.listEmployees());
  }

  @PostMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Employee> create(
          @RequestParam String firstName,
          @RequestParam String lastName,
          @RequestParam Long departmentId,
          @RequestParam String kakaoNickName) {
     Employee employee = employeeService.createEmployee(firstName, lastName, departmentId, kakaoNickName);
     return new ResponseEntity<>(employee, HttpStatus.CREATED);
  }
}
