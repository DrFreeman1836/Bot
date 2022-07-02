package main.bot;

import main.bot.impl.LKCommand;
import main.bot.impl.StartCommand;
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

  private StartCommand start;

  private LKCommand lk;

  @Autowired
  public Bot(StartCommand start, LKCommand lk) {
    this.lk = lk;
    this.start = start;
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

    sendMessage(lk.commandHandler(update));
    sendMessage(start.commandHandler(update));

  }

  public void sendMessage(SendMessage message) {
    if(message == null) return;
    try {
      execute(message);
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }


}
