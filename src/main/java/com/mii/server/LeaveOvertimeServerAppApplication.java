package com.mii.server;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.bytebuddy.asm.Advice.Local;

@SpringBootApplication
public class LeaveOvertimeServerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaveOvertimeServerAppApplication.class, args);
		System.out.println();

		LocalDate time = LocalDate.now();
		System.out.println(time);
		System.out.println("Server is Running...");
	}

}
