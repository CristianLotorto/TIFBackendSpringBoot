package com.project.TFIBackendSpringBoot;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class Main {

	private static Logger LOGGER= LogManager.getLogger();

	public static void main(String[] args) {

		LOGGER.info("Metodo main ha sido ejecutado");
		SpringApplication.run(Main.class, args);
	}


}