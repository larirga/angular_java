package com.larissa.formapp.resources;

import com.larissa.formapp.DTO.UserDTO;
import com.larissa.formapp.entities.User;
import com.larissa.formapp.services.UserService;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/user")
public class UserResource {
  @Autowired
  private UserService service;
  @GetMapping
  public ResponseEntity<List<UserDTO>> findAll() {
    List<UserDTO> list = service.findAll();
    return ResponseEntity.ok().body(list);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
    UserDTO dto = service.findById(id);
    return ResponseEntity.ok().body(dto);
  }

  // uri p/ boas práticas e estabelecer o location do created

  @PostMapping
  public ResponseEntity<UserDTO> insert(@RequestBody UserDTO dto) {
    dto = service.insert(dto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(dto.getId()).toUri();
    return ResponseEntity.created(uri).body(dto);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO dto) {
    dto = service.update(id, dto);
    return ResponseEntity.ok().body(dto);
  }
}
