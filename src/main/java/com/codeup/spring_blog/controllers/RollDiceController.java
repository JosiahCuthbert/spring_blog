package com.codeup.spring_blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RollDiceController {

    @RequestMapping("/roll-dice")
    public String diceRoll(){
        return "roll-dice";
    }

    @RequestMapping("/roll-dice/{num}")
    public String diceRoll(@PathVariable int num, Model model){
        int roll = (int)(Math.round(Math.random() * 6) + 1);

        model.addAttribute(num);
        model.addAttribute("roll", roll);

        return "roll-dice-result";
    }

}
