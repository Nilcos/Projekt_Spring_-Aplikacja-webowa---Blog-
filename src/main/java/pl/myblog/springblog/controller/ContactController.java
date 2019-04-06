package pl.myblog.springblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ContactController {

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @PostMapping("/send")
    public String sendMessage(@Valid @ModelAttribute Contact contact, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
            model.addAttribute("contact", new Contact());
            return "contact";
        } else {
            contactService.save(contact);
            return "index";
        }



    }
}
