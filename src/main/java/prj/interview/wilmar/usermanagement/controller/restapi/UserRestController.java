package prj.interview.wilmar.usermanagement.controller.restapi;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import prj.interview.wilmar.usermanagement.controller.BaseController;
import prj.interview.wilmar.usermanagement.entity.User;

@RestController
@RequestMapping("/user")
public class UserRestController extends BaseController {

	@GetMapping(value = "/load", produces = "application/json")
	public List<User> load() {
		List<User> lstUser = userServices.getAllData();
		return lstUser;
	}

	@PostMapping(value = "/update", consumes = "application/json")
	public List<User> update(@RequestBody User user) {
		userServices.updateData(user);
		return load();
	}

	@PostMapping(value = "/del", consumes = "application/json")
	public List<User> del(@RequestBody User user) {
		userServices.delData(user);
		return load();
	}
}
