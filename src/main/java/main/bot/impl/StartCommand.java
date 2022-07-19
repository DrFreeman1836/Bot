package main.bot.impl;

import main.bot.Command;
import main.bot.UserBotRequest;
import main.service.impl.ManagerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class StartCommand extends Command {

  private ManagerUserService managerUserService;

  UserBotRequest userBotRequest;

  private SendMessage sendMessage = new SendMessage();

  public StartCommand() {
    super("/start");
  }

  @Autowired
  public void setManagerUserService(ManagerUserService managerUserService) {
    this.managerUserService = managerUserService;
  }

  @Override
  protected SendMessage responseToCommand(Message message, User user) {

    managerUserService.addUser(user.getUserName(), user.getFirstName(),
        user.getLastName(), message.getChatId());

    sendMessage.setChatId(message.getChatId().toString());
    sendMessage.setText(user.getFirstName() + ", Привет!");

    userBotRequest.sendMessage(message.getChatId(), "Лох=))");

    return sendMessage;
  }



}
