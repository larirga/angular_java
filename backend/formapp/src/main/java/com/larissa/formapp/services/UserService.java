package com.larissa.formapp.services;

import com.larissa.formapp.DTO.UserDTO;
import com.larissa.formapp.entities.User;
import com.larissa.formapp.repositories.UserRepository;
import com.larissa.formapp.services.exceptions.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
  @Autowired
  private UserRepository repository;
  @Transactional(readOnly = true)
  public List<UserDTO> findAll() {
    List<User> list = repository.findAll();

    List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());

    return listDto;
  }

//  Optional para evitar trabalhar com valor null
  @Transactional(readOnly = true)
  public UserDTO findById(Long id) {
    Optional<User> obj = repository.findById(id);

    User entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    return new UserDTO(entity);
  }

  @Transactional
  public UserDTO insert(UserDTO dto) {
    User entity = new User();
    entity.setName(dto.getName());
    entity.setEmail(dto.getEmail());

    entity = repository.save(entity);
    return new UserDTO(entity);
  }
}
