package com.xidian.employee_manage;

import com.xidian.controller.EmployeeController;
import com.xidian.domain.Employee;
import com.xidian.domain.EmployeeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class EmployeeManageApplicationTests {

	@Test
	public void contextLoads() {
	}

	private MockMvc mvc;
	private List<Employee> list = new ArrayList<>();

	@Before
	public void setUp() throws Exception {

		list.add(new Employee(0,"小明",20,"男"));
		list.add(new Employee(1,"小红",19,"女"));
		list.add(new Employee(2,"小智",15,"男"));
		list.add(new Employee(3,"小刚",16,"男"));
		list.add(new Employee(4,"小霞",15,"女"));

		Employee expectedEmployee = new Employee(2,"小智",15,"男");
		EmployeeRepository mockRepository = mock(EmployeeRepository.class);
		when(mockRepository.findEmployee(2)).thenReturn(expectedEmployee);
		when(mockRepository.findAll()).thenReturn(list);
		EmployeeController employeeController = new EmployeeController(mockRepository);
		mvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	}

	@Test
	public void testEmployeeController() throws Exception {

		RequestBuilder request = null;

		//add提交employee
	    request = post("/add");
		mvc.perform(request)
				.andExpect(content().string(equalTo("success")));

		request = get("/employees");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[{\"id\":0,\"name\":\"小明\",\"age\":20,\"gender\":\"男\"}," +
						"{\"id\":1,\"name\":\"小红\",\"age\":19,\"gender\":\"女\"}," +
						"{\"id\":2,\"name\":\"小智\",\"age\":15,\"gender\":\"男\"}," +
						"{\"id\":3,\"name\":\"小刚\",\"age\":16,\"gender\":\"男\"}," +
						"{\"id\":4,\"name\":\"小霞\",\"age\":15,\"gender\":\"女\"}]")));

		//delete employee
		request = delete("/1");
		mvc.perform(request)
				.andExpect(content().string(equalTo("success")));

		request = put("/2");
		mvc.perform(request)
				.andExpect(content().string(equalTo("success")));





//		//get获取employee列表
//		request = get("/employee");
//		mvc.perform(request)
//				.andExpect(status().isOk())
//				.andExpect(content().string(equalTo("[{\"id\":0,\"name\":\"小明\",\"age\":20,\"gender\":男}," +
//						"{\"id\":1,\"name\":\"小红\",\"age\":19,\"gender\":女}," +
//						"{\"id\":2,\"name\":\"小智\",\"age\":15,\"gender\":男}," +
//						"{\"id\":3,\"name\":\"小刚\",\"age\":16,\"gender\":男}," +
//						"{\"id\":4,\"name\":\"小霞\",\"age\":15,\"gender\":女}]")));
//
		// 4、put修改id为1的user
//		request = put("/1");
//		mvc.perform(request)
//				.andExpect(content().string(equalTo("success")));
//
//		// 5、get一个id为1的user
//		request = get("/users/1");
//		mvc.perform(request)
//				.andExpect(content().string(equalTo("{\"id\":1,\"name\":\"测试终极大师\",\"age\":30}")));
//
//		// 6、del删除id为1的user
//		request = delete("/users/1");
//		mvc.perform(request)
//				.andExpect(content().string(equalTo("success")));
//
//		// 7、get查一下user列表，应该为空
//		request = get("/users/");
//		mvc.perform(request)
//				.andExpect(status().isOk())
//				.andExpect(content().string(equalTo("[]")));

	}



}
