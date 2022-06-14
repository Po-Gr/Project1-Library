package org.example.dao;

import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getAllPeople() {
        return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
    }

    public Person getPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE person_id=?", new Object[]{id},
                new PersonMapper()).stream().findAny().orElse(null);
    }

//    public Optional<Person> getPerson(String email) {
//        return jdbcTemplate.query("SELECT * FROM person WHERE email=?", new Object[]{email},
//                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
//    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person (full_name, birth_year) VALUES (?, ?)",
                person.getFullName(), person.getBirthYear());
    }

    public void update(Person person, int id) {
        jdbcTemplate.update("UPDATE person SET full_name=?, birth_year=? WHERE person_id=?",
                person.getFullName(), person.getBirthYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE person_id=?", id);
    }

    //////////////////////////////////////////////////////
    //// Тестируем производитльность пакетной вставки ////
    //////////////////////////////////////////////////////
//
//    public void testMultipleUpdate() {
//        List<Person> people = create1000people();
//
//        long before = System.currentTimeMillis();
//
//        for (Person person: people) {
//            jdbcTemplate.update("INSERT INTO person VALUES (?, ?, ?, ?)",
//                    person.getId(), person.getFullName(), person.getBirthYaer(),person.getEmail());
//        }
//
//        long after = System.currentTimeMillis();
//        System.out.println("Time for standard INSERT= " + (after - before));
//    }
//
//    public void testBatchUpdate() {
//        List<Person> people = create1000people();
//
//        long before = System.currentTimeMillis();
//
//        jdbcTemplate.batchUpdate("INSERT INTO person VALUES (?, ?, ?, ?, ?)",
//                new BatchPreparedStatementSetter() {
//                    @Override
//                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
//                        preparedStatement.setInt(1, people.get(i).getId());
//                        preparedStatement.setString(2, people.get(i).getFullName());
//                        preparedStatement.setInt(3, people.get(i).getBirthYaer());
//                        preparedStatement.setString(4, people.get(i).getEmail());
//                        preparedStatement.setString(5, people.get(i).getAddress());
//                    }
//
//                    @Override
//                    public int getBatchSize() {
//                        return people.size();
//                    }
//                });
//
//        long after = System.currentTimeMillis();
//        System.out.println("Time for batch INSERT= " + (after - before));
//    }
//
//    public List<Person> create1000people() {
//        List<Person> people = new ArrayList<>();
//
//        for (int i = 0; i < 1000; i++) {
//            people.add(new Person(i, "Name" + i, 30, "test" + i + "mail.ru", "some address"));
//        }
//        return people;
//    }
}
