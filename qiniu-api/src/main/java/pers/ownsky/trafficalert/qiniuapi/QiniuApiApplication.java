package pers.ownsky.trafficalert.qiniuapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class QiniuApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(QiniuApiApplication.class, args);
	}

}
