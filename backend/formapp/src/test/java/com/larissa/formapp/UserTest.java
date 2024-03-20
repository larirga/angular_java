package com.larissa.formapp;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.larissa.formapp.DTO.UserDTO;
import com.larissa.formapp.services.UserService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @Test
  public void FindAllShouldReturnAllUsers() throws Exception {
    List<UserDTO> users = Arrays.asList(
        new UserDTO(1L, "Rafael", "rafael@email.com"),
        new UserDTO(2L, "Thiago", "thiago@email.com")
    );


    when(userService.findAll()).thenReturn(users);

    ResultActions result = mockMvc.perform(get("/user").contentType(MediaType.APPLICATION_JSON));

    result.andExpect(status().isOk());

    result.andExpect(jsonPath("$[*].id").exists());
    result.andExpect(jsonPath("$[*].name").exists());
    result.andExpect(jsonPath("$[*].email").exists());
  }

  @Test
  public void FindUsersByIdShouldReturnUsersById() throws Exception {
    long userId1 = 1L;
    long userId2 = 2L;

    UserDTO user1 = new UserDTO(userId1, "Rafael", "rafael@email.com");
    UserDTO user2 = new UserDTO(userId2, "Thiago", "thiago@email.com");

    when(userService.findById(userId1)).thenReturn(user1);
    when(userService.findById(userId2)).thenReturn(user2);

    ResultActions resultUser1 = mockMvc.perform(get("/user/{id}", userId1).contentType(MediaType.APPLICATION_JSON));
    ResultActions resultUser2 = mockMvc.perform(get("/user/{id}", userId2).contentType(MediaType.APPLICATION_JSON));

    resultUser1
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(userId1))
        .andExpect(jsonPath("$.name").value(user1.getName()))
        .andExpect(jsonPath("$.email").value(user1.getEmail()));

    resultUser2
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(userId2))
        .andExpect(jsonPath("$.name").value(user2.getName()))
        .andExpect(jsonPath("$.email").value(user2.getEmail()));
  }
}
