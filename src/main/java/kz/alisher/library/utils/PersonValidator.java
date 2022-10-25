package kz.alisher.library.utils;

import kz.alisher.library.dao.PersonDAO;
import kz.alisher.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        // Check person w same email in DB
        if (personDAO.show(person.getFio()) != null)
            errors.rejectValue("email", "", "This email is already taken");
    }
}
