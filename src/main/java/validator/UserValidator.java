package validator;

import java.util.ArrayList;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import model.User;

public class UserValidator  implements Validator{
	private ArrayList<String> error = new ArrayList<String>();
	private String result = "";
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		if(user.getLogin().length() < 4) {
			errors.rejectValue("login", "login error");
			error.add("login must be bigger that 3");
		}
		if(user.getPassword().length() < 4) {
			errors.rejectValue("password", "password error");
			error.add("password must be bigger that 3");
		}
		
	}
	
	public String getError() {
		for(String current: error) {
			result = result + current + " ";
		}
		return result;
	}
}
