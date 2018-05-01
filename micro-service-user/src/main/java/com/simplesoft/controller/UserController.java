package com.simplesoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.simplesoft.bean.User;
import com.simplesoft.reponsitory.UserResponsitory;

@RestController
@EnableDiscoveryClient
public class UserController {
	@Autowired
	private UserResponsitory userRepository;
	
	@Autowired
	private EurekaClient eurekaClient;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@RequestMapping("/byId/{id}")
	public User userById(@PathVariable Long id) {
		return this.userRepository.findOne(id);
	}
	
	@GetMapping("/instance-info")
	public String instanceInstance() {
		InstanceInfo serverFromEureka = this.eurekaClient.getNextServerFromEureka("MICRO-SERVICE-USER", false);
		return serverFromEureka.getHomePageUrl();
	}
	@GetMapping("/service-info")
	public ServiceInstance serviceInstance() {
		ServiceInstance ServiceInstance = this.discoveryClient.getLocalServiceInstance();
		return ServiceInstance;
	}
}
