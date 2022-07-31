package prj.interview.wilmar.usermanagement.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import prj.interview.wilmar.usermanagement.entity.User;

@Repository
public class UserDAO {
	@Autowired
	public EntityManagerFactory entityManagerFactory;

	// @PersistenceContext
	EntityManager entityManager;

	EntityTransaction transaction;

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		List<User> list = new ArrayList<User>();
		try {
			entityManager = entityManagerFactory.createEntityManager();
			list = entityManager.createQuery("SELECT u FROM defaultUser u").getResultList();
		} catch (Exception e) {

		} finally {
			entityManager.close();
		}
		return (List<User>) list;
	}

	@SuppressWarnings("unchecked")
	public User getUserById(User user) {
		User findUser = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			findUser = returnUserById(user);
		} catch (Exception e) {

		} finally {
			entityManager.close();
		}
		return findUser;
	}

	@SuppressWarnings("unchecked")
	public void saveUser(User user) {
		try {
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();
			if (user.getId() != null) {
				// for example
				User updateUser = returnUserById(user);
				updateUser.setFirstName(user.getFirstName());
				updateUser.setLastName(user.getLastName());
				updateUser.setBirthday(user.getBirthday());
				updateUser.setEmail(user.getEmail());
				updateUser.setPhone(user.getPhone());
				updateUser.setGender(user.getGender());
				entityManager.merge(updateUser);
			} else {
				entityManager.persist(user);
			}
			transaction.begin();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			entityManager.close();
		}
	}

	@SuppressWarnings("unchecked")
	public void delUser(User user) {
		try {
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			User delUser = returnUserById(user);
			if(delUser == null) {
				return;
			}
			entityManager.remove(delUser);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			entityManager.close();
		}
	}
	
	private User returnUserById(User user) throws Exception {
		User findUser = entityManager.find(User.class, user.getId());
		return findUser;
	}
}
