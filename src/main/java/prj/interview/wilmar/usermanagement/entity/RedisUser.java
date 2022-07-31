package prj.interview.wilmar.usermanagement.entity;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("RedisUser")
public class RedisUser extends BaseUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7612734847695205478L;

	private Integer id;
	private String firstName;
	private String lastName;
	private String birthday;
	private String email;
	private String phone;
	private Boolean gender;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}
}
