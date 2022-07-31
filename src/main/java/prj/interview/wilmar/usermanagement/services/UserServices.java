package prj.interview.wilmar.usermanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prj.interview.wilmar.usermanagement.dao.UserDAO;
import prj.interview.wilmar.usermanagement.entity.User;

@Service
public class UserServices {
	@Autowired
	private UserDAO userDAO;

	public List<User> getAllData() {
		return userDAO.getAllUsers();
	}

	public List<User> findDataByKeysearch(String keySearch) {
		return userDAO.findDataByKeysearch(keySearch);
	}

	public void updateData(User user) {
		userDAO.saveUser(user);
	}

	public void delData(User user) {
		userDAO.delUser(user);
	}
}
