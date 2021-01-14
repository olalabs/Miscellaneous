package contacts.web;

import contacts.Contact;
import contacts.data.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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
    public String processContact(@Valid Contact contact, Errors errors) {
        if (errors.hasErrors()) {
            return "contactForm";
        }
        contactRepository.insert(contact);
        return "redirect:/";
    }

    @RequestMapping(value = "/update/{id}", method = GET)
    public String getUpdatedContact(@PathVariable Long id, Model model) {
        Contact updatedContact = contactRepository.findOne(id);
        model.addAttribute(updatedContact);
        return "contactForm";
    }

    @RequestMapping(value = "/update/{id}", method = POST)
    public String updateContact(@PathVariable Long id, @Valid Contact contact, Errors errors) {
        if (errors.hasErrors()) {
            return "contactForm";
        }
        contactRepository.update(contact);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = GET)
    public String getUpdatedContact(@PathVariable Long id) {
        contactRepository.delete(id);
        return "redirect:/";
    }
}
