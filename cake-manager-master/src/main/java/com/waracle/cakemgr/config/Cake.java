package com.waracle.cakemgr.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cake {
	@JsonProperty
	protected String title;
	@JsonProperty
	protected String desc;
	@JsonProperty
	protected String image;
}
