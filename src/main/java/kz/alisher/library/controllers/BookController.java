package kz.alisher.library.controllers;

import kz.alisher.library.dao.BookDAO;
import kz.alisher.library.models.Book;
import kz.alisher.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Component
@RequestMapping("/books")
public class BookController {
    final BookDAO bookDAO;


    @Autowired
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/new")
    public String addBook(@ModelAttribute("book") Book book){
        return "books/new";
    }
    @PostMapping()
    public String saveBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "books/new";
        }
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("takenPerson", bookDAO.getPerson(id));
        model.addAttribute("people", bookDAO.getFreePeople());
        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String editing(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "books/edit";
        }
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/add")
    public String addUser2Book(@PathVariable("id") int book_id,
                               @ModelAttribute("person") Person person){
        bookDAO.addUser2Book(book_id, person.getId());
        return "redirect:/books/{id}/";
    }

    @PatchMapping("/{book_id}/{person_id}")
    public String removePersonBook(@PathVariable("book_id") int book_id,
                                   @PathVariable("person_id") int person_id){
        bookDAO.removePersonBook(book_id);
        return "redirect:/people/{person_id}";
    }

    @DeleteMapping("/{book_id}/{person_id}")
    public String removePersonBook(@PathVariable("book_id") int book_id){
        bookDAO.removePersonBook(book_id);
        return "redirect:/books/{book_id}";
    }

}
