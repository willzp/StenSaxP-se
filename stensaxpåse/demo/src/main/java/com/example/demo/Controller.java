package com.example.demo;

import java.util.Random;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@RequestMapping(path = "/stensaxpåse", method = RequestMethod.POST)
	public String game(String move) {
		String computer = computerchoice();
	    String result = whoWon(move, computer);
		return "Ditt val = " + move + ", datorns val = " + computer + ". " + result;
	}
	
	@RequestMapping(path = "/stensaxpåse", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
	public String gameResult() {
		 return getResults();
	}
	
	public String computerchoice() {
		
		 Random random = new Random();
	        int randomNumber = random.nextInt(3);
	        if(randomNumber == 0 ) {
	        	return "sten";
	        }
	        if(randomNumber == 1 ) {
	        	return "påse";
	        }
	        
	        if(randomNumber == 2 ) {
	        	return "sax";
	        }
	        else return "error";
	}
	
	public String whoWon(String userchoice, String computerchoice) {
		
		GameBean.gameCount++;
		
		if (computerchoice.equals("sten") && userchoice.equals("påse")) {
			GameBean.wins ++;
		    return "Du vann";
		}
		if (computerchoice.equals("sten") && userchoice.equals("sax")) {
			GameBean.losses++;
		    return "Datorn vann";
		}
		if (computerchoice.equals("påse") && userchoice.equals("sax")) {
			GameBean.wins++;
		    return "Du vann";
		}
		if (computerchoice.equals("påse") && userchoice.equals("sten")) {
			GameBean.losses++;
			return "Datorn vann";
		}
		if (computerchoice.equals("sax") && userchoice.equals("sten")) {
			GameBean.wins++;
			return "Du vann";
		}
		if (computerchoice.equals("sax") && userchoice.equals("påse")) {
			GameBean.losses++;
			return "Datorn vann";
		}
		
		GameBean.draws++;
		return "Det blev lika, testa igen";
	}
	
	public String getResults() {
		String pattern = "{ \"games\":\"%s\", \"wins\":\"%s\", \"losses\":\"%s\", \"ties\": \"%s\"}";
		return String.format(pattern, GameBean.gameCount, GameBean.wins, GameBean.losses, GameBean.draws);
	}
	
   
}
