package contacts.web;

import contacts.Contact;
import contacts.data.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @RequestMapping(value = "/create", method = GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute(new Contact());
        return "contactForm";
    }

    @RequestMapping(value = "/create", method = POST)
    public String processContact(Contact contact) {
        contactRepository.insert(contact);
        return "redirect:/";
    }

}
