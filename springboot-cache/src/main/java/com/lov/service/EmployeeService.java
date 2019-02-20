package com.lov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.lov.bean.Employee;
import com.lov.mapper.EmployeeMapper;

@CacheConfig(cacheNames="emp")	//抽取缓存的公共配置
@Service
public class EmployeeService {

	@Autowired
	EmployeeMapper  employeeMapper;
	/*
	 * 	将方法的运行结果进行缓存，之后再要相同的 数据，直接从缓存中获取，不需要调用方法
	 * 
	 * 	CacheManager管理多个Cache组件，对缓存的真正crud操作在cache组件中，每一个缓存组件有自己唯一的名字；
	 * 	属性：
	 * 		cacheNames/value:指定缓存组件的名字
	 * 		key:缓存数据使用的key；默认是使用方法参数的值，
	 * 		key/keyGenerator:key的生成器，可以自己指定key的生成器的组件id
	 * 		cacheManager:指定缓存管理器；或者cacheResolver指定获取解析器
	 * 		condition:指定符合条件的情况下才缓存
	 * 		unless:否定缓存；当unless指定条件为true时，方法返回值不会被缓存
	 * 		sync:是否使用异步模式
	 */
	//@Cacheable(cacheNames= {"emp"},keyGenerator="myKeyGenerator")	//key=getEmp[1]
	@Cacheable(cacheNames= {"emp"})
	public Employee getEmp(Integer id) {
		System.out.println("查询"+id+"号员工");
		Employee employee = employeeMapper.getEmpById(id);
		return employee;
	}
	
	
	/*
	 * @CachePut：既调用方法，又更新缓存数据；同步更新缓存
	 * 	修改了数据库的某个数据，同时更新缓存
	 * 	运行时机：
	 * 	1、先调用目标方法
	 * 	2、将目标方法的结果缓存起来
	 * 
	 * 	测试：
	 * 	1、查询1号emp，查到结果放入缓存： key：1,value: emp
	 * 	2、更新1号emp：
	 * 		将方法的返回值放入缓存：key：传入的emp对象	,value: emp
	 * 	3、查询还是之前的值：由于两者key不一致
	 * 		更新：
	 * 			key="#employee.id"	使用传入的参数
	 * 			key="#result.id"	使用返回的结果
	 */
	@CachePut(value="emp",key="#employee.id")
	public Employee updateEmp(Employee employee) {
		System.out.println("updateEmp:"+employee 	);
		employeeMapper.updateEmp(employee);
		return employee;
	}
	
	/*
	 * @CacheEvit:缓存清除
	 * 	key：指定要清除的数据
	 * 	allEntries=tue:指定清除所有数据
	 * 	beforeInvocation=false:缓存的清除是否在方法之前执行
	 * 		默认代表在方法执行之后执行，如果出现异常缓存不会被清除；为true时无论怎样都会清除
	 */
	@CacheEvict(value="emp",key="#id")
	public void deleteEmp(Integer id) {
		System.out.println("deleteEmp:"+id);
//		employeeMapper.deleteEmpByid(id);
	}
	
	@Caching(
			cacheable= {
					@Cacheable(key="#lastName")
			},
			put= {
					@CachePut(key="#result.email"),
					@CachePut(key="#result.id")
			}
			)
	public Employee getEmpByLastName(String lastName) {
		return employeeMapper.getEmpByLastName(lastName);
	}
	
}
