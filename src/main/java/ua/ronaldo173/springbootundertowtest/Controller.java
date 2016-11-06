package ua.ronaldo173.springbootundertowtest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@RequestMapping(value = "/test")
	public String helloTest() {
		return "SUCESS!!";
	}
}
