package com.larissa.formapp.services;

import com.larissa.formapp.entities.User;
import com.larissa.formapp.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserRepository repository;
  public List<User> findAll() {
    return repository.findAll();
  }
}
