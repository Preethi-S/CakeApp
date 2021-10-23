package com.waracle.cakemgr.service;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.dao.CakeDao;
import com.waracle.cakemgr.repository.CakeRepository;

@RunWith(MockitoJUnitRunner.class)
public class CakeServiceTest {
	
	
	@InjectMocks
	private CakeService cakeService = new CakeService();
	
	@Mock
	private ObjectMapper objectMapper;
	
	@Mock
	private CakeRepository cakeRepository;

	@Test

	public void testLoadCakes() throws JsonProcessingException {
		String input = "https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json";
		CakeDao[] cakelist = new CakeDao[] { new CakeDao("pineapple cake", "description", "imagelocation") ,
                new CakeDao("strawberry cake", "description-test", "imagelocation-test")};

		when(objectMapper.readValue(anyString(), eq(CakeDao[].class))).thenReturn(cakelist);
		
		cakeService.loadCakes(input);  
	    	
		assertDoesNotThrow(() -> MalformedURLException.class);
		assertDoesNotThrow(() -> IOException.class);
	}
	
	@Test
	public void testGetAllCakes() {
	    when(cakeRepository.findAll()).thenReturn(getAllCakeData());
	    
		List<CakeDao> cake = cakeService.getCakeList();
		assertEquals(cake.size(),2);
	}

	@Test
	public void testDownloadCakeList() throws JsonProcessingException{
		String home = System.getProperty("user.home");
		String filePath = home+ "\\Downloads\\list.txt";
		when(objectMapper.writeValueAsString(any(List.class))).thenReturn(getAllCakeData().toString());
	    when(cakeRepository.findAll()).thenReturn(getAllCakeData());
	    
		String cake = cakeService.downloadCakeList();
		assertEquals(cake, "Cake List is Successfully Downloaded and available in Location:"+ filePath);
	}
	
	@Test
	public void testDownloadCakeListThrowsException() throws IOException, JsonProcessingException{		
		
		when(objectMapper.writeValueAsString(any(List.class))).thenThrow(new JsonProcessingException("Error") {});
	    
		String cake = cakeService.downloadCakeList();
		assertEquals(cake, "Error downloading Cake List!!!");
	}
	
	private List<CakeDao> getAllCakeData(){
		return List.of( new CakeDao(1,"pineapple cake", "description", "imagelocation") ,
	                    new CakeDao(2,"strawberry cake", "description-test", "imagelocation-test"));
				
	}
	
}
