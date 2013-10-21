package com.fdmgroup.mockito;

import java.util.HashSet;
import java.util.Set;


public class LogInManager {

	Set<String> loggedInUsers = new HashSet<String>();
	
	public boolean logIn(User user){
		
		if(user.isLoggedIn())
			return false;
		
		return loggedInUsers.add(user.getUsername());

	}
	
	public boolean logOut(User user){
		
		if(!user.isLoggedIn())
			return false;
		
		return loggedInUsers.remove(user.getUsername());
	}
}
