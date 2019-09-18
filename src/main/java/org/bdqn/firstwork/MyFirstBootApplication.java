package org.bdqn.firstwork;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.bdqn.firstwork.mapper")
@SpringBootApplication
public class MyFirstBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyFirstBootApplication.class, args);
	}

}
