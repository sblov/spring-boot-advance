package com.lov;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lov.bean.Employee;
import com.lov.mapper.EmployeeMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootCacheApplicationTests {

	@Autowired
	EmployeeMapper employeeMapper;
	
	@Test
	public void contextLoads() {
		
		System.out.println(employeeMapper.getEmpById(1));
		
		Employee employee = new Employee(3, "lisi", "lisi@gamil.com",1,2);
		employeeMapper.insertEmployee(employee);
		
		System.out.println(employeeMapper.getEmpById(3));
		
		
	}

}
