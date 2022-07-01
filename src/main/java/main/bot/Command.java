package main.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class Command extends Bot {

    private final String command;

    public Command(String command) {
        this.command = command;
    }

    @Override
    public void onUpdateReceived(Update update){

        if(update.getMessage().getText().equals(command)){
            responseToCommand(update.getMessage(),
                    update.getMessage().getFrom());
        }

    }

    abstract protected void responseToCommand(Message message, User user);

    protected void sendMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}
