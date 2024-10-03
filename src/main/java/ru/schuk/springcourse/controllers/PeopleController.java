package ru.schuk.springcourse.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.schuk.springcourse.dao.PersonDAO;
import ru.schuk.springcourse.models.Book;
import ru.schuk.springcourse.models.Person;
import ru.schuk.springcourse.util.PersonValidator;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private final PersonDAO personDAO;
    @Autowired
    private final PersonValidator personValidator;


    public PeopleController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model) {
        //получим список всех людей и выведем в представление
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping(value = "{id}")
    public String show(@PathVariable("id") int id, Model model) {

        List<Book> list = new ArrayList<>();
        list = personDAO.findBookByPersonId(id);

//        if (list.isEmpty()){
//            model.addAttribute("books", "Человек пока не взял ни одной книши");
//        } else {
//            model.addAttribute("books", list);
//        }
        model.addAttribute("books", list);
        model.addAttribute("person", personDAO.show(id));

        return "people/show";
    }

    @GetMapping(value = "new") //записи идентичны с value и без
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {

        personValidator.validate(person,bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        personDAO.save(person);
        return "redirect:/people";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.deletePerson(id);
        return "redirect:/people";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        return "/people/edit";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id) {
        personDAO.updatePerson(id, person);
        return "redirect:/people";
    }


}
