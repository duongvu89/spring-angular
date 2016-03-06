package demo;

import demo.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import demo.service.UserService;

import java.util.List;
import java.util.UUID;

/**
 * Created by Nguyen Duong Vu on 21-Feb-16.
 */
@RestController
@SpringBootApplication
@EnableRedisHttpSession
public class ResourceApplication extends WebSecurityConfigurerAdapter {
    @Autowired
    public UserService userService;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String home() {
        String id = UUID.randomUUID().toString();
        return "[id:'" + id + "', content: 'Hello']";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAll() {
        return userService.getAll();
    }

    @RequestMapping(value = "users/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable String id) {
        userService.delete(id);
    }

    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // We need this to prevent the browser from popping up a dialog on a 401
        http.httpBasic().disable();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/**").hasRole("WRITER").anyRequest().authenticated();
    }
}
