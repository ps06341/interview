package prj.interview.wilmar.usermanagement.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import prj.interview.wilmar.usermanagement.entity.RedisUser;
import prj.interview.wilmar.usermanagement.entity.User;

@Repository
public interface RedisUserDao extends CrudRepository<RedisUser, String>{
	public Iterable<RedisUser> findByKeysearch(String keysearch);
	public Integer generateUniqueId();
	public <S extends User> Iterable<User> reloadCaching( Iterable<User> entities);
}
