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
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    private final ManagerBotService managerBotService;

    @Autowired
    public Bot(ManagerBotService managerBotService) {
        this.managerBotService = managerBotService;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
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
                case "/start" : {
                    SendMessage message = new SendMessage();
                    message.setChatId(update.getMessage().getChatId().toString());
                    message.setText(firstName + ", Привет!");
                    if(userName != null){
                        managerBotService.saveUser(userName);
                    } else {
                        managerBotService.saveUser(firstName, lastName);
                    }
                    sendMessage(message);
                    break;
                }

                case "/lk" : {
                    SendMessage message = new SendMessage();
                    message.setChatId(update.getMessage().getChatId().toString());
                    message.setText("");
                    sendMessage(message);
                    break;
                }
            };
        }

    }

    private void sendMessage(SendMessage message){
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }



}
