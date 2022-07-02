package main.bot.impl;

import main.bot.Command;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class LKCommand extends Command {

  //  @Autowired
//  private final ManagerBotService managerBotService;
  private StringBuilder textBuilder = new StringBuilder();

  public LKCommand() {
    super("/lk");
  }

  @Override
  protected SendMessage responseToCommand(Message message, User user) {

    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(message.getChatId().toString());
    textBuilder.append("Ваше имя: ")
        .append(user.getUserName() != null ? user.getUserName() : user.getFirstName())
        .append(System.lineSeparator());
    textBuilder.append("Ваш уникальный ID: ").append(message.getChatId());
    sendMessage.setText(textBuilder.toString());

    return sendMessage;
  }
}
