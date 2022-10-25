package kz.alisher.library.controllers;

import kz.alisher.library.dao.PersonDAO;
import kz.alisher.library.models.Book;
import kz.alisher.library.models.Person;
import kz.alisher.library.utils.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }

    //Page with list of visitors
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    // Adding new visitor
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    //Save new visitor
    // TODO: Add validation
    @PostMapping()
    public String savePerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()){
            return "people/new";
        }
        personDAO.save(person);
        return "redirect:/people";
    }

    // Showing visitor information
    @GetMapping( "/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("book") Book book){
        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("takenBooks", personDAO.getTakenBooks(id));
        model.addAttribute("freeBooks", personDAO.getFreeBooks());
        return "people/show";
    }

    //Visitor taken book
    @PostMapping("/{id}")
    public String addPersonBook(@PathVariable("id") int personId, @ModelAttribute("book") Book book){
        personDAO.takeBook(personId, book.getId());
        return "redirect:/people/{id}";
    }


    // Edit person information
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }
    // TODO: Add validation
    @PatchMapping("/{id}")
    public String updatePerson(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult) {

        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/edit";

        personDAO.update(id, person);
        return "redirect:/people";
    }

    // Delete visitor
    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        personDAO.delete(id);
        return "redirect:/people";
    }



}
