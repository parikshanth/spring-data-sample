package com.demo.data.data;

import com.demo.data.data.services.WriteDataToConsole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DataApplication implements CommandLineRunner {

	@Autowired
	private WriteDataToConsole writeDataToConsole;

	public static void main(String[] args) {
		SpringApplication.run(DataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		writeDataToConsole.Run();
	}
}
