package prj.interview.wilmar.usermanagement.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import prj.interview.wilmar.usermanagement.dao.RedisUserDao;
import prj.interview.wilmar.usermanagement.entity.RedisUser;

@Repository
public class RedisUserDaoImpl implements RedisUserDao {

	private final String hashReference = "RedisUser";

	private RedisTemplate<String, Object> redisTemplate;
	private HashOperations hashOperations;

	@Autowired
	public RedisUserDaoImpl(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public <S extends RedisUser> S save(S entity) {
		hashOperations.put(hashReference, entity.getId(), entity);
		return null;
	}

	@Override
	public <S extends RedisUser> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<RedisUser> findById(String id) {
		return Optional.ofNullable((RedisUser) hashOperations.get(hashReference, id));
	}

	@Override
	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<RedisUser> findAll() {
		Map<Integer, RedisUser> map = new HashMap<>();
		try {
			map = hashOperations.entries(hashReference);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return map.values();
	}

	@Override
	public Iterable<RedisUser> findByKeysearch(String keysearch) {
		Map<Integer, RedisUser> map = new HashMap<>();
		try {
			map = hashOperations.entries(hashReference);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		List<RedisUser> result = new ArrayList<>();
		Iterable<RedisUser> iteRedisUser = map.values();
		for (RedisUser redisUser : iteRedisUser) {
			if (redisUser.getFirstName().contains(keysearch) || redisUser.getLastName().contains(keysearch)) {
				result.add(redisUser);
			}
		}

		return result;
	}

	@Override
	public Iterable<RedisUser> findAllById(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(String id) {
		hashOperations.delete(hashReference, id);
	}

	@Override
	public void delete(RedisUser entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends RedisUser> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer generateUniqueId() {
		int id = 0;
		Iterable<RedisUser> iRedisUser = findAll();
		for (RedisUser redisUser : iRedisUser) {
			if(id <= redisUser.getId()) {
				id = redisUser.getId() + 1;
			}
		}

		return id;
	}

}
