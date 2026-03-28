package com.example.fcauth.service;


import com.example.fcauth.model.Employee;

import com.example.fcauth.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class EmployeeService {
  private final EmployeeRepository employeeRepository;

  public List<Employee> listEmployees() {
    return employeeRepository.findAll();
  }

  @Transactional
  public Employee createEmployee(String firstName, String lastName, Long departmentId,String kakaoNickName) {

    if(employeeRepository.existsByKakaoNickName(kakaoNickName)) {
      throw new DuplicateKeyException("같은 카카오 닉네임이 존재합니다.");
    }

    Employee employee = Employee.builder()
            .firstName(firstName)
            .lastName(lastName)
            .departmentId(departmentId)
            .kakaoNickName(kakaoNickName)
            .build();
    return employeeRepository.save(employee);
  }


}

