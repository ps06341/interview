package prj.interview.wilmar.usermanagement.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prj.interview.wilmar.usermanagement.dao.RedisUserDao;
import prj.interview.wilmar.usermanagement.dao.UserDAO;
import prj.interview.wilmar.usermanagement.entity.BaseUser;
import prj.interview.wilmar.usermanagement.entity.RedisUser;
import prj.interview.wilmar.usermanagement.entity.User;

@Service
public class UserServices {
	@Autowired
	private UserDAO userDAO;

	@Autowired
	private RedisUserDao redisUserDao;

	public List<BaseUser> getAllData() {
		try {
			List<BaseUser> users = new ArrayList<>();
			redisUserDao.findAll().forEach(users::add);
			if (users != null && !users.isEmpty()) {
				return users;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return userDAO.getAllUsers();
	}

	public List<BaseUser> findDataByKeysearch(String keySearch) {
		List<BaseUser> users = new ArrayList<>();
		redisUserDao.findByKeysearch(keySearch).forEach(users::add);
		if (users != null && !users.isEmpty()) {
			return users;
		}

		return (List<BaseUser>) userDAO.findDataByKeysearch(keySearch);
	}

	public void updateData(User user) {
		RedisUser redisUser = new RedisUser();

		redisUser.setFirstName(user.getFirstName());
		redisUser.setLastName(user.getLastName());
		redisUser.setBirthday(user.getBirthday());
		redisUser.setEmail(user.getEmail());
		redisUser.setPhone(user.getPhone());
		redisUser.setGender(user.getGender());
		redisUser.setId(user.getId());

		// save db
		userDAO.saveUser(user);

		if (user.getId() == null) {
			// save update cache db
			reloadCaching();
		} else {
			// update to update cache db
			redisUserDao.save(redisUser);
		}
	}

	public void delData(User user) {
		// del cache db
		redisUserDao.deleteById(user.getId() + "");
		// save cache db
		userDAO.delUser(user);
	}
	
	public void reloadCaching() {
		redisUserDao.reloadCaching(userDAO.getAllUsersNoCast());
	}
}
