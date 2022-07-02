package main.service.impl;

import lombok.AllArgsConstructor;
import main.model.User;
import main.repository.UserRepository;
import main.service.ManageUser;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ManagerUserService implements ManageUser {

  private final UserRepository userRepository;

  public void addUser(String userName, String firstName, String lastName, Long chatId) {
    if(userName != null){
      saveUser(userName, chatId);
    } else {
      saveUser(firstName, lastName, chatId);
    }
  }

  @Override
  public void saveUser(String userName, Long chatId) {
    userRepository.save(User.builder()
        .userName(userName)
        .chatId(chatId)
        .build());
  }

  @Override
  public void saveUser(String firstName, String lastNAme, Long chatId) {
    userRepository.save(User.builder()
        .firstName(firstName)
        .lastName(lastNAme)
        .chatId(chatId)
        .build());
  }
}
