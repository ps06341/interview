package prj.interview.wilmar.usermanagement.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import prj.interview.wilmar.usermanagement.entity.BaseUser;
import prj.interview.wilmar.usermanagement.entity.User;

@Repository
public class UserDAO {
	@Autowired
	public EntityManagerFactory entityManagerFactory;

	// @PersistenceContext
	EntityManager entityManager;

	EntityTransaction transaction;

	@SuppressWarnings("unchecked")
	public List<BaseUser> getAllUsers() {
		List<BaseUser> list = new ArrayList<>();
		try {
			entityManager = entityManagerFactory.createEntityManager();
			list = entityManager.createQuery("SELECT u FROM defaultUser u").getResultList();
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			entityManager.close();
		}

		return list;
	}

	public List<User> getAllUsersNoCast() {
		List<User> list = new ArrayList<>();
		try {
			entityManager = entityManagerFactory.createEntityManager();
			list = entityManager.createQuery("SELECT u FROM defaultUser u").getResultList();
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			entityManager.close();
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<? extends BaseUser> findDataByKeysearch(String keySearch) {
		List<User> list = new ArrayList<>();
		try {
			entityManager = entityManagerFactory.createEntityManager();
			Query query = entityManager
					.createQuery("SELECT u FROM defaultUser u Where u.firstName like :first or u.lastName like :last");
			query.setParameter("first", "%" + keySearch + "%");
			query.setParameter("last", "%" + keySearch + "%");
			list = query.getResultList();
		} catch (Exception e) {
			System.out.print(e.toString());
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
			System.out.println(e.toString());
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
				// update
				User updateUser = returnUserById(user);
				updateUser.setFirstName(user.getFirstName());
				updateUser.setLastName(user.getLastName());
				updateUser.setBirthday(user.getBirthday());
				updateUser.setEmail(user.getEmail());
				updateUser.setPhone(user.getPhone());
				updateUser.setGender(user.getGender());
				entityManager.merge(updateUser);
			} else {
				// save
				entityManager.persist(user);
			}
			transaction.begin();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.out.println(e.toString());
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
			if (delUser == null) {
				return;
			}
			entityManager.remove(delUser);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.out.println(e.toString());
		} finally {
			entityManager.close();
		}
	}

	private User returnUserById(User user) throws Exception {
		User findUser = entityManager.find(User.class, user.getId());
		return findUser;
	}
}
