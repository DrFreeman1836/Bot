package main.bot;

import main.service.impl.ManagerBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
public class Bot extends TelegramLongPollingBot {

  @Value("${bot.name}")
  private String BOT_NAME;

  @Value("${bot.token}")
  private String BOT_TOKEN;

  private final String START_COMMAND = "/start";

  private final String LK_COMMAND = "/lk";

  private final ManagerBotService managerBotService;

  @Autowired
  public Bot(ManagerBotService managerBotService) {
    this.managerBotService = managerBotService;
  }

  @Override
  public String getBotUsername() {
    return BOT_NAME;
  }

  @Override
  public String getBotToken() {
    return BOT_TOKEN;
  }

  @Override
  public void onUpdateReceived(Update update) {

    if (update.hasMessage() && update.getMessage().hasText()) {
      String userName = update.getMessage().getFrom().getUserName();
      String firstName = update.getMessage().getFrom().getFirstName();
      String lastName = update.getMessage().getFrom().getLastName();
      Long chatId = update.getMessage().getChatId();

      String textUpdate = update.getMessage().getText();
      switch (textUpdate) {
        case START_COMMAND: {
          SendMessage message = new SendMessage();
          message.setChatId(update.getMessage().getChatId().toString());
          message.setText(firstName + ", Привет!");
          if (userName != null) {
            managerBotService.saveUser(userName, chatId);
          } else {
            managerBotService.saveUser(firstName, lastName, chatId);
          }
          sendMessage(message);
          break;
        }

        case LK_COMMAND: {
          StringBuilder textBuilder = new StringBuilder();
          SendMessage message = new SendMessage();
          message.setChatId(update.getMessage().getChatId().toString());
          textBuilder.append("Ваше имя: ").append(userName != null ? userName : firstName);
          textBuilder.append("\n");
          textBuilder.append("Ваш уникальный ID: ").append(chatId);
          message.setText(textBuilder.toString());
          sendMessage(message);
          break;
        }
      }
      ;
    }

  }

  private void sendMessage(SendMessage message) {
    try {
      execute(message);
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }


}
