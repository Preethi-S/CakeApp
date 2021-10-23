package com.waracle.cakemgr.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.dao.CakeDao;
import com.waracle.cakemgr.service.CakeService;

@WebMvcTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class CakeAppControllerTest {

	@MockBean
	CakeService mockService;
	
	@Autowired
	private MockMvc mvc;
	

	
	@Test	
	public void testGetCakes_Status200() throws Exception {
		
		when(mockService.getAllCakes()).thenReturn("CakeList");
		
		mvc.perform(get("/")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
			      
	}
	
	
	@Test	
	public void testAddCake_Status200() throws Exception {
		
		CakeDao cakeDao = new CakeDao(1,"pineapple cake", "description", "imagelocation");	
		
		mvc.perform(post("/")
				   .contentType(MediaType.APPLICATION_JSON)
				   .content(new ObjectMapper().writeValueAsString(cakeDao)))
			       .andExpect(status().isOk());
			      
	}
	
	@Test	
	public void testDownloadCakeList_Status200() throws Exception {		
			
		mvc.perform(get("/cakes")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
			      
	}
	
	@Test
	public void testAddCakes_Status200() throws Exception {
	
		mvc.perform(post("/cakes")
				   .contentType(MediaType.APPLICATION_JSON)
				   .content(new ObjectMapper().writeValueAsString(getAllCakeData())))
			       .andExpect(status().isOk());
			      
	}
	
	private List<CakeDao> getAllCakeData(){
		return List.of( new CakeDao(1,"pineapple cake", "description", "imagelocation") ,
	                    new CakeDao(2,"strawberry cake", "description-test", "imagelocation-test"));
				
	}
	
}
