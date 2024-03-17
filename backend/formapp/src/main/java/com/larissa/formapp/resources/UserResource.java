package com.larissa.formapp.resources;

import com.larissa.formapp.entities.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserResource {
  @GetMapping
  public ResponseEntity<List<User>> findAll() {
    List<User> list = new ArrayList<>();
    list.add(new User(1L, "Rafael", "rafael@email.com"));
    return ResponseEntity.ok().body(list);
  }
}
