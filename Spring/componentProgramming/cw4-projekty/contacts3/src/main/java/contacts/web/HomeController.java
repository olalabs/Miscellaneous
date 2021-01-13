package contacts.web;

import contacts.data.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {

  @Autowired
  private ContactsRepository contactsRepository;

  @RequestMapping(method = RequestMethod.GET)
  public String home(Model model) {
    model.addAttribute("contacts", contactsRepository.findContacts(20, 0));
    return "home";
  }

}
