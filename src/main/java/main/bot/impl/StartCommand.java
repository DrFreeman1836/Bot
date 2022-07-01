package main.bot.impl;

import main.bot.Command;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

public class StartCommand extends Command {

//    @Autowired
//    private final ManagerBotService managerBotService;

    public StartCommand(String command) {
        super(command);
    }

    @Override
    protected void responseToCommand(Message message, User user) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(user.getFirstName() + ", Привет!");

        sendMessage(sendMessage);

    }


}
