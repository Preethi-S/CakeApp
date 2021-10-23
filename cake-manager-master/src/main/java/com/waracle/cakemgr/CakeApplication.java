package com.waracle.cakemgr;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.waracle.cakemgr.service.CakeService;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class CakeApplication {
	@Autowired
	CakeService cakeService;
	public static String inputUrl = "https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json";
	
	public static void main(String[] args) {
		SpringApplication.run(CakeApplication.class, args);		

	}
	
	@PostConstruct
	public void loadAllCakes() {
		log.info("Loading Cake Details .....");
		cakeService.loadCakes(inputUrl);
	}

}



