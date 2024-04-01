package ru.netology.containerstask1.repository;

import org.springframework.stereotype.Repository;
import ru.netology.containerstask1.service.AuthorizationService.Authorities;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    public List<Authorities> getUserAuthorities(String user, String password) {
        List<Authorities> listAuthorities = new ArrayList<>();

        if (user.equals("Administrator") && (password.equals("123456789"))) {
            listAuthorities = List.of(Authorities.READ, Authorities.WRITE, Authorities.DELETE);
        } else if (user.equals("Petya") && (password.equals("123"))) {
            listAuthorities = List.of(Authorities.READ);
        }
        return listAuthorities;

    }
}