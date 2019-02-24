package com.ctl.challenge.ctlApi.controllers;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ctl.challenge.ctlApi.models.Follower;
import com.ctl.challenge.ctlApi.models.Repo;

@RestController

public class GithubFollowers {
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("{user}/followers")
	public List<Follower> getFollowers(@PathVariable String user){
		
		RestTemplate restTemplate = new RestTemplate();	
        
        //Per Github API Documentation
        final String URL = "https://api.github.com/users/{user}/followers?per_page=5";
        List<LinkedHashMap<String,String>> followers = restTemplate.getForObject(URL, List.class, user);    
        List<Follower> result = new CopyOnWriteArrayList<>();
        if (followers == null){
        	result.add(new Follower("no followers"));
        	return result;
        }else{
        
        for(LinkedHashMap<String,String>  follower : followers)
        		
        { 
        	result.add(new Follower(follower.get("login")));
        }
        //Now that we have first level of followers, we loop through to find the second and third level 
        
        for (Follower follower:result){
        	
        	String followerlvl1 = follower.getLogin();
        	List<LinkedHashMap<String,String>> followers1 = restTemplate.getForObject(URL,List.class, followerlvl1);
    		
        	for (LinkedHashMap<String,String>  follower1: followers1){
        		String followerlvl2 = follower1.get("login");
        		result.add(new Follower(follower1.get("login")));
        		List<LinkedHashMap<String,String>> followers2 = restTemplate.getForObject(URL,List.class, followerlvl2);
        		
        		for (LinkedHashMap<String,String>  follower2: followers2) {
        			result.add(new Follower(follower2.get("login")));
        		}
        	
        	}
        	
        	
    	}

        return result;
        
	}
	}
	
	@GetMapping("{user}/repos")
	public List<Repo> getRepos(@PathVariable String user){
		
		final String URL = "https://api.github.com/users/{user}/repos?per_page=3";
		final String URL_STARRED = "https://api.github.com/{user}/starred?per_page=3";
		
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String,String>> repos = restTemplate.getForObject(URL,List.class, user);
        List<Repo> result = new CopyOnWriteArrayList<>();
        if (repos.isEmpty()){
        	
        	result.add(new Repo("no repos"));
        	return result;
        }else{
        
        for(LinkedHashMap<String,String>  repo : repos)
        		
        { 
   
        	result.add(new Repo(repo.get("name")));
        }
	
    	}
        
        
        return result;
        
	}
	}

