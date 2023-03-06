package com.mii.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LeaveOvertimeServerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaveOvertimeServerAppApplication.class, args);
		System.out.println();

		System.out.println("Execute...");
		System.out.println("Server is Running...");
	}

}
