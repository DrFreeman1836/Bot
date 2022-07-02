package main.bot.impl;

import main.bot.Command;
import main.service.impl.ManagerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class LKCommand extends Command {

  private ManagerUserService managerUserService;

  private SendMessage sendMessage = new SendMessage();

  private StringBuilder textBuilder = new StringBuilder();

  public LKCommand() {
    super("/lk");
  }

  @Autowired
  public void setManagerUserService(ManagerUserService managerUserService) {
    this.managerUserService = managerUserService;
  }

  @Override
  protected SendMessage responseToCommand(Message message, User user) {

    textBuilder.setLength(0);
    sendMessage.setChatId(message.getChatId().toString());
    textBuilder.append("Ваше имя: ")
        .append(user.getUserName() != null ? user.getUserName() : user.getFirstName())
        .append(System.lineSeparator());
    textBuilder.append("Ваш уникальный ID: ").append(message.getChatId());
    sendMessage.setText(textBuilder.toString());

    return sendMessage;
  }
}
