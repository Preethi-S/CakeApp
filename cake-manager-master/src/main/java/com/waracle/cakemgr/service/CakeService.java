package com.waracle.cakemgr.service;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.dao.CakeDao;
import com.waracle.cakemgr.repository.CakeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CakeService {
	
	@Autowired
	CakeRepository cakeRepository;

	@Autowired
	ObjectMapper objectMapper;

	public void loadCakes(String inputUrl) {
		try {
			URL url = new URL(inputUrl);
			try (Scanner scanner = new Scanner(url.openStream())) {
				String jsonInput = "";
				while (scanner.hasNext()) {
					jsonInput += scanner.next();
				}
				CakeDao[] cakes = objectMapper.readValue(jsonInput, CakeDao[].class);
				saveAllCakes(cakes);
			} catch (IOException e) {
				log.info("IOException occured");
			}

		} catch (MalformedURLException e) {
			log.info("MalformedException occured");
		}
	}

	private void saveAllCakes(CakeDao[] cakes) {
	//	Arrays.stream(cakes).forEach(cake -> saveCake(new CakeDao(cake.getTitle(), cake.getDesc(), cake.getImage())));
		Arrays.stream(cakes).forEach(cake -> saveCake(cake));
	}

	public void saveCake(CakeDao cake) {
		cakeRepository.save(cake);
	}

	public String getAllCakes() {
		try {
			List<CakeDao> cakes = getCakeList();
			return objectMapper.writeValueAsString(cakes);			
		} catch (JsonProcessingException e) {
			log.info("Error converting Cake Object to Json" + e.getMessage());
			return "Error in processing request!";
		}
	}

	public String downloadCakeList() {
		String home = System.getProperty("user.home");
		String filePath = home+ "\\Downloads\\list.txt";
		try {
			FileWriter file = new FileWriter(filePath);
			file.write(objectMapper.writeValueAsString(getCakeList()));
			file.close();
			return "Cake List is Successfully Downloaded and available in Location:" + filePath;
		} catch (IOException e) {
			return "Error downloading Cake List!!!";
		}
	}

	public List<CakeDao> getCakeList() {
		List<CakeDao> allCakes = cakeRepository.findAll();
//		return allCakes.stream().map(cakeDao -> {
//			return new Cake(cakeDao.getTitle(), cakeDao.getDescription(), cakeDao.getImageLocation());
//		}).collect(Collectors.toList());
		return allCakes;
	}
}
