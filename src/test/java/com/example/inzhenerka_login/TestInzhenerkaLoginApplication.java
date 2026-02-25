package com.example.inzhenerka_login;

import org.springframework.boot.SpringApplication;

public class TestInzhenerkaLoginApplication {

	public static void main(String[] args) {
		SpringApplication.from(InzhenerkaLoginApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
