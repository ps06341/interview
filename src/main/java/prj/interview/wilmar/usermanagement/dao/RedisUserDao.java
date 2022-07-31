package prj.interview.wilmar.usermanagement.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import prj.interview.wilmar.usermanagement.entity.RedisUser;

@Repository
public interface RedisUserDao extends CrudRepository<RedisUser, String>{
	public Iterable<RedisUser> findByKeysearch(String keysearch);
	public Integer generateUniqueId();
}
