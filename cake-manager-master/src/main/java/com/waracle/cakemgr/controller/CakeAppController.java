package com.waracle.cakemgr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waracle.cakemgr.dao.CakeDao;
import com.waracle.cakemgr.service.CakeService;

@RestController
@RequestMapping
public class CakeAppController {

	@Autowired
	CakeService cakeService;
	
	@GetMapping
	public String getAllCakes() {
		return cakeService.getAllCakes();
	}
	
	@PostMapping
	public ResponseEntity<String> addCake(@RequestBody CakeDao cake) {		
		cakeService.saveCake(cake);		
		return ResponseEntity.ok(cake.getTitle() +" is added!!!");
	
	}
	
	@GetMapping(value = "/cakes", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public String downloadCakeList() {		
		return cakeService.downloadCakeList();
		
	}
	
	@PostMapping(value ="/cakes", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addCakes(@RequestBody List<CakeDao> cakes) {	 
		cakes.forEach(cake -> cakeService.saveCake(cake));
		return ResponseEntity.ok("Cakes are added");
	}

}
