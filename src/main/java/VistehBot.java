import entity.TaskDescription;
import entity.Tasks;
import entity.User;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import services.TaskService;
import services.UserService;
import utills.TaskTimeUtill;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by nikita93 on 03.07.2017.
 */
public class VistehBot extends TelegramLongPollingBot {
    private Properties properties;
    private UserService userService;
    private TaskService taskService;

    public VistehBot() throws IOException, SQLException {
        properties = new Properties();
//        properties.load(new FileInputStream("src/main/resources/visteh.properties"));
        properties.load(VistehBot.class.getResourceAsStream("/visteh.properties"));
        userService = new UserService(properties);
        taskService = new TaskService();
    }



    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            try {
                if ( userService.getById(message.getFrom().getId().toString()) != null ) {
                    System.out.println("request from --- " + message.getFrom().getFirstName());
                    System.out.println("text req --- " + message.getText());
                    if (message.getText().equals("/task")) {
                        try {
                            Tasks tasks = taskService.getTasks(properties);
                            String text = "";
                            for (TaskDescription description :
                                    tasks.getTasks()) {
                                if (text.length() + description.prettyOut().length() >= 4095) {
                                    sendMsg(message, text);
                                    text = "Продолжение:\n";
                                }
                                text = text + description.prettyOut() ;
                            }
                            System.out.println(text.length());
                            sendMsg(message, text);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else if (message.getText().equals("/help")) {
                        sendMsg(message, "/help - просмотр комманд\n" +
                                "/task - просмотр заявок\n" +
                                "/subscribe - получать закрывающиеся заявки\n" +
                                "/unsubscribe - отписаться от закрывающихся заявок");
                    }
                    else if (message.getText().equals("/subscribe")) {
                        User user = userService.getById(message.getFrom().getId().toString());
                        user.setAccess(1);
                        userService.insertUser(user);
                        sendMsg(message, "Успешно подписались на закрывающиеся заявки");
                    }
                    else if (message.getText().equals("/unsubscribe")) {
                        User user = userService.getById(message.getFrom().getId().toString());
                        user.setAccess(0);
                        userService.insertUser(user);
                        sendMsg(message, "Успешно отписались от закрывающихся заявок");
                    }
                    else if(message.getText().equals("/users")) {
                        List<User> users =  userService.getAll();
                        String collect = users.stream().map((s) -> s.toString()).collect(Collectors.joining("\n"));
                        System.out.println(collect);
                        sendMsg(message, collect);
                    }
                    else {
                        sendMsg(message, "Ничем не могу тебе помочь...\nНабери /help для просмотра возможных команд");
                    }
                }

                else {
                    if ( message.getText().equals("") ) {
                        User user = new User();
                        user.setId((message.getFrom().getId()));
                        user.setAccess(0);
                        user.setChat_id(message.getChatId().toString());
                        user.setName(message.getFrom().getFirstName());
                        userService.insertUser(user);
                        sendMsg(message, "Вы успешно зарегистрировались в системе!");
                    } else {
                        sendMsg(message, "Для доступа к Боту введите пароль");
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public String getBotUsername() {
        return properties.getProperty("bot_name");
    }

    public String getBotToken() {
        return properties.getProperty("bot_token");
    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
//        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void run() {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            final VistehBot bot = new VistehBot();
//            telegramBotsApi.registerBot(new VistehBot());
            telegramBotsApi.registerBot(bot);
            Timer time = new Timer();
            TimerTask timerTask = new TimerTask() {
                public void run() {
                    try {
                        Properties properties = new Properties();
//                        properties.load(new FileInputStream("src/main/resources/visteh.properties"));
                        properties.load(VistehBot.class.getResourceAsStream("/visteh.properties"));
                        UserService userService = new UserService(properties);
                        TaskService taskService = new TaskService();
                        ArrayList<TaskDescription> closingTasks = TaskTimeUtill.getClosingTasks(taskService.getTasks(properties));
                        List<User> users = userService.getAll();
                        for (User user:
                                users)
                            if (user.getAccess() == 1) {
                                String text = "";
                                for (TaskDescription closing :
                                        closingTasks) {
                                    text = text + closing.prettyOut();
                                }
                                if ( text != "" ) {
                                    SendMessage message = new SendMessage();
                                    message.enableMarkdown(true);
                                    message.setChatId(user.getChat_id());
                                    String string = "Истекающие заявки!!!\n\n";
                                    message.setText(string + text);
                                    bot.sendMessage(message);
                                }
                            }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            };

            time.schedule(timerTask, 0, 600000);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
