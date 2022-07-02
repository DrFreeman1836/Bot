package main.bot.impl;

import main.bot.Command;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class StartCommand extends Command {

//    @Autowired
//    private final ManagerBotService managerBotService;

    public StartCommand() {
        super("/start");
    }

    @Override
    protected SendMessage responseToCommand(Message message, User user) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(user.getFirstName() + ", Привет!");

        return sendMessage;
    }


}
