package com.larissa.formapp;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class User {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;
  @Autowired
  private ObjectMapper objectMapper;

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

}
