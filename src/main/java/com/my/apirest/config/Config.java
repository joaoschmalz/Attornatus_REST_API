package com.my.apirest.config;

import com.my.apirest.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("Dev")
public class Config
{
	@Autowired
	private DBService dbService;

	@Bean
	public void startDataBaseLoad(){
		this.dbService.firstLoad();
	}
}
