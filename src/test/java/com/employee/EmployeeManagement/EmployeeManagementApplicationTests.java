package com.employee.EmployeeManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.employee.model.Contact;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

class EmployeeManagementApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private MockMvc mockMvc;

	
		public void setUp() {
		 mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	   }
		
		  protected String mapToJson(Object obj) throws JsonProcessingException {
		      ObjectMapper objectMapper = new ObjectMapper();
		      return objectMapper.writeValueAsString(obj);
		   }
		   protected <T> T mapFromJson(String json, Class<T> clazz)
		      throws JsonParseException, JsonMappingException, IOException {
		      
		      ObjectMapper objectMapper = new ObjectMapper();
		      return objectMapper.readValue(json, clazz);
		   }
	
		@Test
		public void createEmployeeContact() throws Exception {
		   String uri = "http://localhost:8080/employee/add";
		   Contact empContact = new Contact();
			empContact.setFirstName("Test");
			empContact.setLastName("One");
			empContact.setEmail("test@one.com");
			empContact.setAddress("One town");
			empContact.setPhoneNumber("465789456");
			empContact.setPosition("IT Manager");
		   
		   String inputJson = mapToJson(empContact);
		   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
		      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(201, status);
		}

		
		@Test
		public void getEmployeeList() throws Exception {
		   String uri = "http://localhost:8080/employee/getcontacts";
		   System.out.println("mockMVC" + mockMvc );
		   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
		      .accept(MediaType.APPLICATION_JSON)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(200, status);
		   String content = mvcResult.getResponse().getContentAsString();
		   Contact[] employeeList = mapFromJson(content, Contact[].class);
		   assertTrue(employeeList.length > 0);
		}

}
