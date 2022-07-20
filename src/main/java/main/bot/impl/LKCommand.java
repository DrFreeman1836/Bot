package main.bot.impl;

import main.bot.Command;
import main.bot.UserBotRequest;
import main.service.impl.ManagerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class LKCommand extends Command {

  private ManagerUserService managerUserService;

  private UserBotRequest userBotRequest;

  private StringBuilder textBuilder = new StringBuilder();

  public LKCommand() {
    super("/lk");
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

    textBuilder.setLength(0);
    textBuilder.append("Ваше имя: ")
        .append(user.getUserName() != null ? user.getUserName() : user.getFirstName())
        .append(System.lineSeparator());
    textBuilder.append("Ваш уникальный ID: ").append(message.getChatId());
    userBotRequest.sendMessage(message.getChatId(), textBuilder.toString());

  }

}
