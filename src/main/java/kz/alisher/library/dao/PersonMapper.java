package kz.alisher.library.dao;

import kz.alisher.library.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {

        Person person = new Person();

        person.setId(resultSet.getInt("person_id"));
        person.setFio(resultSet.getString("fio"));
        person.setYearOfBirth(resultSet.getInt("year_of_birth"));


        return person;
    }
}
