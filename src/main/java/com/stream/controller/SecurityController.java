package com.stream.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stream.events.RegisterEvent;
import com.stream.exceptions.ActivationException;
import com.stream.exceptions.UserExistsException;
import com.stream.model.dto.VaraiblesContainer;
import com.stream.model.entities.User;
import com.stream.model.form.LoginForm;
import com.stream.model.form.RegistrationForm;
import com.stream.model.form.SportChoiceForm;
import com.stream.services.ActivationService;
import com.stream.services.CountryService;
import com.stream.services.SportChoiceService;
import com.stream.services.SportService;
import com.stream.services.UserService;

@Controller
@RequestMapping("/auth")
public class SecurityController {

	
	private UserService userService;
	private CountryService countryService;
	private ApplicationEventPublisher eventPublisher;
	private ActivationService activationService;
	private SportService sportService;
	private SportChoiceService sportChoiceService;
	
	
	@Autowired
	public SecurityController(UserService userService, CountryService countryService,
			ApplicationEventPublisher eventPublisher, ActivationService activationService, SportService sportService, SportChoiceService sportChoiceService) {
		
		this.userService = userService;
		this.countryService = countryService;
		this.eventPublisher = eventPublisher;
		this.activationService = activationService;
		this.sportService = sportService;
		this.sportChoiceService = sportChoiceService;
	}
	
	@GetMapping(value = "/login")
	public String login(Model model) {
		
		model.addAttribute("form", new LoginForm());
		return "login";
		
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		
		model.addAttribute("form",new RegistrationForm());
		model.addAttribute("countries", countryService.getAllCountries());
		return "register";
		
	}
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute("form")RegistrationForm form) {
		User u = new User();
		try {
			u = userService.registerUser(form);
			
			
		} catch (UserExistsException e) {
			return "redirect:/auth/register?error=true";
		}
		
		
		
		
		return "redirect:/auth/"+u.getId()+"/sportchoice";
		
	}
	
	@GetMapping("/{id}/sportchoice")
	public String sportChoice(@PathVariable String id,  Model model) {
		
		model.addAttribute("form", new SportChoiceForm());
		model.addAttribute("sps", sportService.getAllSports());
		model.addAttribute("id", id);
		
		return "sportchoice";
		
	}
	
	@PostMapping("/{id}/sportchoice")
	public String addSportChoice(@ModelAttribute SportChoiceForm form,@PathVariable("id") String id) {
		
		sportChoiceService.addSportChoice(form, id);
		User u = userService.getUserByEmail(userService.getUserById(id).getEmail());
		eventPublisher.publishEvent(new RegisterEvent(u));
		
		return "activation";
	}
	
	
	@GetMapping("/activation/{code}")
	public String activate(@PathVariable String code, Model model) {
			

		try {
		activationService.activate(code);
		}catch (ActivationException e) {
			return "activation_error";
		}
		

		
			
		return "activate_success";
	}
	
	@GetMapping("/test")
	public String form(Model mod) {
		
		mod.addAttribute("container", new VaraiblesContainer());
		
		return "test";
		
	}
	
}
