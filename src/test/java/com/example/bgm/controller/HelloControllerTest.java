package com.example.bgm.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

  @Autowired private MockMvc mockMvc;

  @Test
  void public_helloを実行するとhello_worldが返る() throws Exception {
    mockMvc
        .perform(get("/public/hello"))
        .andExpect(status().isOk())
        .andExpect(content().string("hello world!"));
  }

  @Test
  void private_helloを未認証で実行すると403が返る() throws Exception {
    mockMvc.perform(get("/private/hello")).andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(username = "testUser")
  void private_helloを認証済みで実行するとhello_userが返る() throws Exception {
    mockMvc
        .perform(get("/private/hello"))
        .andExpect(status().isOk())
        .andExpect(content().string("hello user"));
  }
}
