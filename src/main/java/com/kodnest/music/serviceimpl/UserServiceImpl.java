package com.kodnest.music.serviceimpl;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.music.entity.User;
import com.kodnest.music.repository.UserRepository;
import com.kodnest.music.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired	
	UserRepository ur;

	@Override
	public void postUser(User user) {
		ur.save(user);

	}
	public User emailExist(User user) {
		User details=ur.findByEmail(user.getEmail());
		if(details==null) {
			return null;
		}
		else return user;
	}
	@Override
	public boolean validUser(String email, String password) {
		User user=ur.findByEmail(email);


		if(user==null) {
			return false;
		}
		String dbpwd=user.getPassword();
		if(password.equals(dbpwd)&&email.equals(user.getEmail())) {
			return true;
		}
		return false;
	}
	@Override
	public String getRole(String email) {
		User user=ur.findByEmail(email);
		return user.getRole();
	}
	@Override
	public void updatePassword(String email,String password) {

		User users=ur.findByEmail(email);


		users.setPassword(password);
		ur.save(users);



	}
	@Override
	public User getUSer(String email) {

		return ur.findByEmail(email);
	}
	@Override
	public void updateUser(User user) {
		ur.save(user);

	}
	@Override
	public boolean isPresent(String email) {
		User user=ur.findByEmail(email);
		if(user!=null) {
			return true;
		}
		return false;
	}
	@Override
	public boolean validPwdAndMail(User user) {
		// TODO Auto-generated method stub
		String password=user.getPassword();
		String email=user.getEmail();
		String pattern = "(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=])(?=\\S+$).{8,}";
		boolean p=password.matches(pattern);
		final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'+/=?^_`{|}~-]+)@(?:[a-z0-9](?:[a-z0-9-][a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
		boolean e= EMAIL_REGEX.matcher(email).matches();
		if(p&&e) {
			return true;
		}


		return false;
	}
	@Override
	public boolean isPremium(String email) {
		User user=ur.findByEmail(email);
		
		 if(user.isPremium()) {
			return true;
		}
		return false;
	}


}
