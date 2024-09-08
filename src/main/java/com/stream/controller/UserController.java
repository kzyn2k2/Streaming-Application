package com.stream.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stream.exceptions.IncorrectPasswordExcpetion;
import com.stream.model.entities.User;
import com.stream.model.entities.View;
import com.stream.model.form.CommentForm;
import com.stream.services.ChannelService;
import com.stream.services.CommentService;
import com.stream.services.SportChoiceService;
import com.stream.services.SportService;
import com.stream.services.UserService;
import com.stream.services.ViewService;
import com.stream.services.ViewTimeService;
import com.stream.services.impl.MailServiceImpl;

@Controller
public class UserController {

	
	
	private SportService sportService;
	private ChannelService channelService;
	private CommentService commentService;
	private ViewService viewService;
	private ViewTimeService viewTimeService;
	private UserService userService;
	private SportChoiceService sportChoiceService;
	
	
	@Autowired
	public UserController(SportService sportService, ChannelService channelService, CommentService commentService, ViewService viewService,
			ViewTimeService viewTimeService, UserService userService, SportChoiceService sportChoiceService) {
		this.sportService = sportService;
		this.channelService = channelService;
		this.commentService = commentService;
		this.viewService = viewService;
		this.viewTimeService = viewTimeService;
		this.userService = userService;
		this.sportChoiceService = sportChoiceService;	}
	
	
	@GetMapping("favicon.ico")
	@ResponseBody
	public void noFavicon() {
		
		
	}


	
	@GetMapping(value = {"/", "/watch"})
	public String watch(Model model, Authentication authentication) {
		
		model.addAttribute("sports", sportService.getSportsWithChannel());
		model.addAttribute("u", authentication.getName());
		return "watch";
		
	}
	
	@GetMapping("/{id}/channels")
	public String channels(@PathVariable("id") String id, Model model, Authentication authentication) {
		
		Long sportId = Long.parseLong(id);
		model.addAttribute("channels", channelService.getChannelBySport(sportId));
		model.addAttribute("sport", sportService.getSportById(sportId));
		model.addAttribute("u", authentication.getName());
		
		return "channels";
		
	}
	
	@GetMapping("/channels/{id}")
	public String channel(@PathVariable("id") String id, Model model, Authentication authentication) {
		
		
		Long channelId = Long.parseLong(id);
		model.addAttribute("u", authentication.getName());
		model.addAttribute("channel", channelService.getChannelById(channelId));
		if(!authentication.getName().equals("admin")) {
			View v = viewService.addView(channelId, authentication);
			viewTimeService.addViewTime(v);
		}	
		return "channel";
	}
	
	@GetMapping("/{id}/comments")
	public String comments(@PathVariable("id") String id, Model model) {
		
		Long channelId = Long.parseLong(id);
		model.addAttribute("comments", commentService.getCommentsForChannel(channelId));
		model.addAttribute("channel", channelService.getChannelById(channelId));
		model.addAttribute("form", new CommentForm());
		return "comments";
		
	}
	
	@PostMapping("/{id}/addcomment")
	public String addComment(@PathVariable("id") String id, @ModelAttribute("form") CommentForm form, Authentication authentication) {
		
		
		Long channelId = Long.parseLong(id);
		form.setChannelId(channelId);
		commentService.addComment(form, authentication);
		
		return "redirect:/"+id+"/comments";
		
		
	}
	
	@GetMapping("/reset")
	public String resetPage() {
		
		return "reset";
	}
	
	@PostMapping("/reset")
	public String resetPassword(@RequestParam("op") String orpass, @RequestParam("pp") String newpass, Authentication authentication) {
		
		try {
			userService.resetPassword(authentication.getName(), orpass, newpass);
		} catch (IncorrectPasswordExcpetion e) {
			return "redirect:/reset?error=true";
		}
		return "redirect:/account";
	}
	
	@GetMapping("/account")
	public String account(Model model, Authentication authentication) {
		
		
		User u = userService.getUserByEmail(authentication.getName());
		
		model.addAttribute("user", userService.getUserById(u.getId()));
		model.addAttribute("sports", sportChoiceService.getUserSportChoices(u.getId()));
		model.addAttribute("u", authentication.getName());
		
		return "account";
		
	}
	
}
