package com.employee.EmployeeManagement;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.employee.Repository.ContactRepository;
import com.employee.model.Contact;


class TestWebApp extends EmployeeManagementApplicationTests {

	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private ContactRepository contactRepository;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

		@Test
		public void testEmployee() throws Exception {
			mockMvc.perform(get("/employee/getContacts")).andExpect(status().isOk())
					.andExpect(content().contentType("application/json;charset=UTF-8"))
					.andExpect(jsonPath("$.id").value("1")).andExpect(jsonPath("$.firstName").value("Ben"))
					.andExpect(jsonPath("$.lastName").value("Alex")).andExpect(jsonPath("$.email").value("acn2@gmail.com"));

		}
		
		@Test
		public void testCreate () {
			Contact empContact = new Contact();
			//empContact.setId(10);
			empContact.setFirstName("Test");
			empContact.setLastName("One");
			empContact.setEmail("test@one.com");
			empContact.setAddress("One town");
			empContact.setPhoneNumber("465789456");
			empContact.setPosition("IT Manager");
	
			contactRepository.save(empContact);
			assertNotNull(contactRepository.findById(10).get());
		}
	
}
