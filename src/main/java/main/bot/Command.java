package main.bot;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

public abstract class Command {

    private final String command;

    public Command(String command) {
        this.command = command;
    }

    public void commandHandler(Update update){

        if(update.getMessage().getText().equals(command)){
            responseToCommand(update.getMessage(), update.getMessage().getFrom());
        }

    }

    abstract protected void responseToCommand(Message message, User user);


}
