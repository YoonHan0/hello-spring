package hello.hello_spring;

import hello.hello_spring.domain.Cat;
import hello.hello_spring.domain.Dog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);

		System.out.println("Hello World!");

		Dog d = new Dog(28, "BaekSam");
		d.eat();	//  부모(추상) 클래스로 부터 상속받은 추상 메소드를 직접 구현한 메소드를 실행
		d.walk();
		d.run();	// 부모(추상) 클래스의 인스턴스 메소드 실행
		System.out.println("age: " + d.age + " name: " + d.name);

		Cat c = new Cat(26, "Naong");
		c.eat();
		c.walk();
		c.run();
		System.out.println("age: " + c.age + " name: " + c.name);
	}
}
