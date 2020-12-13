package com.pruebatecnica.spring.product.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EntityScan({"com.pruebatecnica.spring.commons.repository.models.entity"})
public class SpringProductBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringProductBatchApplication.class, args);
	}

}
