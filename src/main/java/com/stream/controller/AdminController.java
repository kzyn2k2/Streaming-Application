package com.stream.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.stream.model.dto.SportDTO;
import com.stream.model.entities.Sport;
import com.stream.model.form.ChannelForm;
import com.stream.services.ChannelService;
import com.stream.services.SportChoiceService;
import com.stream.services.SportService;
import com.stream.services.UserService;
import com.stream.services.ViewService;
import com.stream.services.ViewTimeService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	
	private SportService sportService;
	private ChannelService channelService;
	private UserService userService;
	private ViewService viewService;
	private ViewTimeService viewTimeService;
	private SportChoiceService sportChoiceService;
	
	
	@Autowired
	public AdminController(SportService sportService, ChannelService channelService, UserService userService,
			ViewService viewService, ViewTimeService viewTimeService, SportChoiceService sportChoiceService) {
		this.sportService = sportService;
		this.channelService = channelService;
		this.userService = userService;
		this.viewService = viewService;
		this.viewTimeService = viewTimeService;
		this.sportChoiceService = sportChoiceService;
	}


	@GetMapping(value = {"/sports", "/"})
	public String sportsList(Model model) {
		
		model.addAttribute("sports", sportService.getAllSports());
		return "sportslist";
	}
	
	@GetMapping("/addchannel/{id}")
	public String addChannelPage(Model model, @PathVariable("id") String id) {
		
		Long sportId = Long.parseLong(id);
		SportDTO sport = sportService.getSportById(sportId);
		model.addAttribute("sport",sport);
		model.addAttribute("form", new ChannelForm());
		
		return "addchannel";
		
	}
	
	@PostMapping("/addchannel/{id}")
	public String addChannelSave(Model model, @PathVariable("id") String id, @ModelAttribute("form") ChannelForm form,@RequestParam("video") MultipartFile video) throws IOException {
		
		Long sportId = Long.parseLong(id);
		form.setSportId(sportId);
		channelService.addChannel(form, video);
		
		return "redirect:/admin/sports";
		
	}
	
	@GetMapping("/sports/{id}")
	public String sport(Model model, @PathVariable("id") String id) {
		
		
		Long sportId = Long.parseLong(id);
		model.addAttribute("sport", sportService.getSportById(sportId));
		model.addAttribute("view", viewService.getViewCountOfSport(sportId));
		model.addAttribute("channels", channelService.getChannelBySport(sportId));
		
		return "sport";
	}
	
	@GetMapping("/users")
	public String users(Model model) {
		
		model.addAttribute("users", userService.getUsers());
		
		return "users";
		
	}
	
	@GetMapping("/user/{id}")
	public String user(@PathVariable("id") String id, Model model) {
		
		
		model.addAttribute("user", userService.getUserById(id));
		model.addAttribute("views", viewService.getUserViews(id));
		model.addAttribute("sports", sportChoiceService.getUserSportChoices(id));
		
		return "user";
		
	}
	
	@GetMapping("/viewlog/{userid}/{chanid}")
	public String viewlog(Model model, @PathVariable("userid") String userId, @PathVariable("chanid") String chanId) {
		
		model.addAttribute("vts", viewTimeService.getUserViewLog(Long.parseLong(chanId), userId));
		
		model.addAttribute("user", userService.getUserById(userId));
		
		model.addAttribute("channel", channelService.getChannelById(Long.parseLong(chanId)));
		
		
		return "viewlog";
		
	}
	
	@GetMapping("/deactivate/{id}")
	public String deactivateUser(@PathVariable("id") String id) {
		
		
		userService.deactivateUser(id);
		
		return "redirect:/admin/user/"+id;
		
	}
}

