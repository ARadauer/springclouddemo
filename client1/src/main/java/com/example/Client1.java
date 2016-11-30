package com.example;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@RestController
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableTurbine
@EnableFeignClients
public class Client1 extends SpringBootServletInitializer {

	/*@Autowired
	private EurekaClient discoveryClient;*/

	@Autowired
	private Client2 client2;

	/*@Autowired
	private StoreIntegration storeIntegration;*/

	public static void main(String[] args) {
		SpringApplication.run(Client1.class, args);
	}

	@RequestMapping("test")
	@HystrixCommand(commandKey = "test")
	public String test(){

		String result = client2.getMessage();
		result = "ich bin 1 und rufe 2 "+result;
		System.out.println(result);
		return result;
	}


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(com.example.Client1.class);
	}


	/*@Component
	public class StoreIntegration {

		@HystrixCommand(fallbackMethod = "defaultStores")
		public String getStores() {
			InstanceInfo instanceInfo = discoveryClient.getNextServerFromEureka("TESTCLIENT2", false);
			System.out.println(instanceInfo.getAppName()+ " "+instanceInfo.getHomePageUrl());
			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject(instanceInfo.getHomePageUrl()+"test2/", String.class);
			return result;
		}

		public String defaultStores() {
			return "2 ist tod";
		}
	}*/
}
