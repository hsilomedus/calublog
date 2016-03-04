package com.netcetera.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.netcetera.model.BlogPost;
import com.netcetera.model.User;
import com.netcetera.repository.BlogPostRepository;
import com.netcetera.repository.UserRepository;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	BlogPostRepository blogPostRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("index")
	public String getIndexPage(ModelMap map) {
		List<BlogPost> posts = blogPostRepository.findAll();
		map.addAttribute("posts", posts);
		return "index";
	}
	
	
	@RequestMapping(path="new", method = RequestMethod.GET)
	public String showNewPage(ModelMap map, HttpSession httpSession) {
		if (httpSession.getAttribute("loggeduserid") != null) {
			map.addAttribute("post", new BlogPost());
			return "new";
		}
		return "forbidden";
	}
	
	@RequestMapping(path="new", method = RequestMethod.POST)
	public String processNewPage(@ModelAttribute BlogPost post, ModelMap map, HttpSession httpSession) {
		if (httpSession.getAttribute("loggeduserid") != null) {
			Long userId = (Long) httpSession.getAttribute("loggeduserid");
			User currentUser = userRepository.getOne(userId);
			
			post.setCreator(currentUser);
			
			blogPostRepository.save(post);
			
			return getIndexPage(map);
		}
		return "forbidden";
		
	}
	
	@RequestMapping(path="edit", method = RequestMethod.GET)
	public String showEditPage(@RequestParam("id") Long id, ModelMap map, HttpSession httpSession) {
		if (httpSession.getAttribute("loggeduserid") != null) {
			
			BlogPost blogPost = blogPostRepository.findOne(id);
			
			map.addAttribute("post", blogPost);
			return "edit";
		}
		return "forbidden";
	}
	
	
	@RequestMapping(path="edit", method = RequestMethod.POST)
	public String processEditPage(@ModelAttribute BlogPost post, ModelMap map, HttpSession httpSession) {
		if (httpSession.getAttribute("loggeduserid") != null) {
			
			blogPostRepository.save(post);
			return getIndexPage(map);
		}
		return "forbidden";
	}
	
}
