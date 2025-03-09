package com.example.paf.maven_project_for_paf_labs;

import java.text.MessageFormat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MavenProjectForPafLabsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MavenProjectForPafLabsApplication.class, args);
	}

	@GetMapping("/")
	public String rootEndpoint() {
		String message = "Hello World!";
		return message;
	}

	@GetMapping("/hello")
	public String helloEndpoint(@RequestParam(value = "name") String name) {
		return MessageFormat.format("Hello World! {0}", name);
	}

	@GetMapping("/greet/{name}")
	public String greetEndpointString(@PathVariable String name, @RequestParam(required = false) String message) {
		String greetMessage = "Hello " + name + "!" + (message != null ? " " + message : "");
		return greetMessage;
	}

}
