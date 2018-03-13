package ru.bellintegrator.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("reminder")
public class ReminderController {
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public String getReminder(Model model) {
        return "My reminder";
    }
    @RequestMapping(value = "/get2", method = RequestMethod.GET)
    @ResponseBody
    public String getReminder2(Model model) {
        return "My reminder";
    }
}
