package contacts.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import contacts.Contact;
import contacts.data.ContactRepository;

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
	public String updateContact(@PathVariable String id, Model model) {
		System.out.println("******* updateContact id=" + id);
		Contact contact = contactRepository.findOne(Long.parseLong(id));
		model.addAttribute(contact);
		return "contactForm";
	}

	@RequestMapping(value = "/update/{id}", method = POST)
	public String updateContact(@Valid Contact contact, Errors errors) {
		if (errors.hasErrors()) {  
			return "contactForm";
		}
		contactRepository.update(contact);
		return "redirect:/";
	}

	@RequestMapping(value = "/delete/{id}", method = GET)
	public String deleteContact(@PathVariable Long id) {
		contactRepository.delete(id);
		return "redirect:/";
	}
}
