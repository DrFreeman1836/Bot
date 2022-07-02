package main.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

public abstract class Command {

    private final String command;

    public Command(String command) {
        this.command = command;
    }

    public SendMessage commandHandler(Update update){

        if(update.getMessage().getText().equals(command)){
            return responseToCommand(update.getMessage(), update.getMessage().getFrom());
        }
        return  null;
    }

    abstract protected SendMessage responseToCommand(Message message, User user);



}
