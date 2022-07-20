package main.bot.impl;

import main.bot.Command;
import main.bot.UserBotRequest;
import main.service.impl.ManagerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class StartCommand extends Command {

  private ManagerUserService managerUserService;

  private UserBotRequest userBotRequest;

  public StartCommand() {
    super("/start");
  }

  @Autowired
  public void setManagerUserService(ManagerUserService managerUserService) {
    this.managerUserService = managerUserService;
  }

  @Autowired
  public void setUserBotRequest(UserBotRequest userBotRequest) {
    this.userBotRequest = userBotRequest;
  }

  @Override
  protected void responseToCommand(Message message, User user) {

    managerUserService.addUser(user.getUserName(), user.getFirstName(), user.getLastName(), message.getChatId());

    userBotRequest.sendMessage(message.getChatId(), user.getFirstName() + ", Привет!");

  }

}
