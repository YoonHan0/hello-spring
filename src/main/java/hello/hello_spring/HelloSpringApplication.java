package hello.hello_spring;

import hello.hello_spring.domain.Cat;
import hello.hello_spring.domain.Dog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.HashMap;


@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);

		System.out.println("Hello World!");

	}

}
