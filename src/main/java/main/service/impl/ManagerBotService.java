package main.service.impl;

import lombok.AllArgsConstructor;
import main.model.User;
import main.repository.UserRepository;
import main.service.ManageBot;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ManagerBotService implements ManageBot {

    private final UserRepository userRepository;

    @Override
    public void saveUser(String userName) {
        userRepository.save(User.builder()
                .userName(userName)
                .build());
    }

    @Override
    public void saveUser(String firstName, String lastNAme) {
        userRepository.save(User.builder()
                .firstName(firstName)
                .lastName(lastNAme)
                .build());
    }
}
