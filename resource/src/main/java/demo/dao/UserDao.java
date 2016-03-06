package demo.dao;

import demo.dto.Gender;
import demo.dto.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Nguyen Duong Vu on 21-Feb-16.
 */
@Repository
public class UserDao {
    List<User> users;
    {
        this.users = new ArrayList<>();
        {
            User u = new User();
            u.setId("1");
            u.setUsername("dgvu");
            u.setPassword(new BCryptPasswordEncoder().encode("123456"));
            u.setBirthDate(LocalDate.now());
            u.setFirstName("Vu");
            u.setLastName("Nguyen");
            u.setGender(Gender.MALE);
            u.setEmail("dgvu89@yahoo.com");
            users.add(u);
        }
        {
            User u = new User();
            u.setId("2");
            u.setUsername("duongvu");
            u.setPassword(new BCryptPasswordEncoder().encode("123456"));
            u.setBirthDate(LocalDate.now());
            u.setFirstName("Duong Vu");
            u.setLastName("Nguyen");
            u.setGender(Gender.FEMALE);
            u.setEmail("duongvu89@gmail.com");
            users.add(u);
        }
    }

    public List<User> getAll() {
        return users;
    }

    public void delete(String id) {
        users = this.getAll()
                .stream()
                .filter(u -> !u.getId().equals(id))
                .collect(Collectors.toList());
    }
}
