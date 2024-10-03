package ru.schuk.springcourse.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.schuk.springcourse.dao.BookDAO;
import ru.schuk.springcourse.dao.PersonDAO;
import ru.schuk.springcourse.models.Book;
import ru.schuk.springcourse.models.Person;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model/*, @ModelAttribute("person") Person person*/) {
        model.addAttribute("book", bookDAO.show(id));

        Person person = bookDAO.findPersonByBookId(id);

        if(person != null){
            model.addAttribute("person", person );
        } else {
            model.addAttribute("noPerson", "СВОБОДНА");
            model.addAttribute("people", personDAO.index());
            model.addAttribute("book", bookDAO.show(id));
            model.addAttribute("updatePerson", new Person());
        }
//        model.addAttribute("person", bookDAO.findPersonByBookId(id));


//        if (bookDAO.findPersonByBookId(id).isEmpty()) {
//            model.addAttribute("person", bookDAO.findPersonByBookId(id));
//        } else {
//            List<Person> person = bookDAO.findPersonByBookId(id);
//            model.addAttribute("person", person);
//        }


//        Optional<Person> bookOwner = bookDAO.getBookOwner(id);
//
//        if (bookOwner.isPresent())
//            model.addAttribute("owner", bookOwner.get());
//        else
//            model.addAttribute("people", personDAO.index());

        return "books/show";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") Book book) {
//        if (bindingResult.hasErrors()) {
//            return "people/new";
//        }
        bookDAO.save(book);
        return "redirect:/books";
    }


    @GetMapping("/{id}/edit")
    public String editBook(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") Book book, @PathVariable("id") int id) {
        bookDAO.updateBook(book, id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/return")
    public String returnbook(@PathVariable("id") int id) {
        bookDAO.returnBook(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/reserve")
    public String reservBook(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        System.out.println(id);
        System.out.println(person);
        bookDAO.reserveBook(id, person.getPerson_id());
        return "redirect:/books/" +id;
    }

}
