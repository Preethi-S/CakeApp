package com.waracle.cakemgr.dao;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class CakeDao {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Integer cakeId;
	@JsonProperty
	private String title;
	@JsonProperty("desc")
	private String description;
	@JsonProperty("image")
	private String imageLocation;	
	
	public CakeDao(String title, String description, String imageLocation) {		
		this.title = title;
		this.description = description;
		this.imageLocation = imageLocation;
	}

}
