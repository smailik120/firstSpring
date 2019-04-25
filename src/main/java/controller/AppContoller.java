package controller;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import model.Fatal;
import model.User;
import repository.UserRepository;
import services.UserService;
import services.UserServiceImpl;
import validator.UserValidator;

@Controller
@SessionAttributes(value = "user")
public class AppContoller {
	@Autowired
	private UserServiceImpl userService = new UserServiceImpl();
	private UserValidator userValidator = new UserValidator();
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(@ModelAttribute("user") User user, @ModelAttribute("error") Fatal error) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("register");
		modelAndView.addObject(user);
		System.out.println(error.getError());
		return modelAndView;
	}
	
	@ModelAttribute("user")
    public User createUser() {
        return new User();
    }
 
	@RequestMapping("/")
	public String welcome() {
		System.out.println("AppController");
		//System.out.println(userRepository.findAll());
		return "index";
	}
	
	@RequestMapping(value = "/cabinet", method = RequestMethod.POST)
	public ModelAndView cabinet(@ModelAttribute("user") User user, BindingResult result) {
		userValidator.validate(user, result);
		if (!result.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("cabinet");
			userService.addUser(user);
			modelAndView.addObject(user);
			System.out.println("user " + user.getLogin());
			System.out.println("password " + user.getPassword());
			return modelAndView;
		}
		else {
			ModelAndView modelAndView = new ModelAndView();
			Fatal error = new Fatal();
			error.setError(userValidator.getError());
			modelAndView.addObject("error", error);
			modelAndView.setViewName("register");
			return modelAndView;
		}
		
	}
}
