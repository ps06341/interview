package prj.interview.wilmar.usermanagement.controller.restapi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import prj.interview.wilmar.usermanagement.controller.BaseController;
import prj.interview.wilmar.usermanagement.entity.BaseUser;
import prj.interview.wilmar.usermanagement.entity.User;

@RestController
@RequestMapping("/user")
public class UserRestController extends BaseController {

	@GetMapping(value = "/load", produces = "application/json")
	@ResponseBody
	public List<BaseUser> load(@RequestParam String keySearch) {
		List<BaseUser> lstUser = new ArrayList<>();
		if (keySearch == null || keySearch.isEmpty()) {
			lstUser = userServices.getAllData();
		} else {
			lstUser = userServices.findDataByKeysearch(keySearch);
		}

		return lstUser;
	}

	@PostMapping(value = "/update", consumes = "application/json")
	public List<BaseUser> update(@RequestBody User user) {
		userServices.updateData(user);
		return new ArrayList<>();
	}

	@PostMapping(value = "/del", consumes = "application/json")
	public List<BaseUser> del(@RequestBody User user) {
		userServices.delData(user);
		return new ArrayList<>();
	}
}
