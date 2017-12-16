import org.telegram.telegrambots.api.methods.send.*;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import java.io.*;
import java.security.SecureRandom;
import java.util.Calendar;
import java.net.*;
import java.util.Random;

public class TimeBot extends TelegramLongPollingBot {
    final static String TIME_COMMAND = "время";
    final static String CALCULATOR_COMMAND = "калькулятор";
    final static String PASSWORD_COMMAND = "пароль";
    final static String ANEKDOT_COMMAND = "анекдот";
    final static String CREATOR_COMMAND = "создатель";

    static Random random = new SecureRandom();
    int randomAnekdot;

    private String resMessage;
    private int res;
    private double resd;

    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        System.out.println("Message: " + message);

        if(message.equalsIgnoreCase(TIME_COMMAND)) {
            sendMessage(update, "Время и дата - " + getTime());
        } else if(message.equalsIgnoreCase(CALCULATOR_COMMAND)){
            //calculator(update);
        } else if(message.equalsIgnoreCase(ANEKDOT_COMMAND)){
            sendAnekdot(update);
        } else if(message.equalsIgnoreCase(PASSWORD_COMMAND)){
            sendMessage(update, PasswordGenerator.generatePassword());
        } else if(message.equalsIgnoreCase(CREATOR_COMMAND)){
            sendMessage(update, "Вот ссылка на его страницу в вк: https://vk.com/lexakalina");
        } else if(chekTranslate(update) == true){
            translator(update);
        } else {
            sendMessage(update, "Я не понял команду");
        }
    }

    public String getBotUsername() {
        return "M1210timebot";
    }

    public String getBotToken() {
        return "501817947:AAFhOBPqJq13hpZ-Xi8BvgjOeUm5nwBB3Mo";
    }

    private void sendMessage(Update update, String m) {
        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                .setChatId(update.getMessage().getChatId())
                .setText(m);
        try {
            execute(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void sendImageFromUrl(String url, String chatId) {
        SendPhoto sendPhotoRequest = new SendPhoto();
        sendPhotoRequest.setChatId(chatId);
        sendPhotoRequest.setPhoto(url);
        try {
            sendPhoto(sendPhotoRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime().toString();
    }

    /*
    private void calculator(Update update){
        String message = update.getMessage().getText();
        sendMessage(update,"------------------");
        sendMessage(update,"Что вы хотите посчитать?");

        if (message.equals("Бот, выключи калькулятор")){
            sendMessage(update, "Калькулятор выключен");
            sendMessage(update, "------------------");
            return;
        }
        //разделяем, преобразуем боковые строки в числа и делаем с ними действие-символ №2
        String chars[] = message.split(" ");
        int a = Integer.parseInt(chars[0]);
        int b = Integer.parseInt(chars[2]);//- преобразование строки в число
        if(chars[1].equals("+")){
            res = a + b;
            resMessage.valueOf(res);
        } else if(chars[1].equals("-")){
            res = a - b;
            resMessage.valueOf(res);
        } else if(chars[2].equals("*")){
            res = a*b;
            resMessage.valueOf(res);
        } else if(chars[1].equals("/")){
            res = a/b;
            resMessage.valueOf(res);
        } else if (chars[1].equals("^")){
            resd = (double) res;
            resd = Math.pow(a,b);
            res = (int) resd;
            resMessage.valueOf(res);
        } else if(chars[1].equals("<")) {
            if (a < b) {
                res = 1;
            } else {
                res = 0;
            }
            if (res == 1) {
                resMessage = "Верно!";
            } else {
                resMessage = "Неверно!";
            }
        } else if(chars[2].equals("<")) {
            if (a < b) {
                res = 1;
            } else {
                res = 0;
            }
            if (res == 1) {
                resMessage = "Верно!";
            } else {
                resMessage = "Неверно!";
            }
        }
        sendMessage(update, "Результат:" + resMessage);
    }

    */
    public void sendAnekdot(Update update){
        randomAnekdot = random.nextInt(8);
        if(randomAnekdot == 0){
            sendImageFromUrl("http://img0.joyreactor.cc/pics/post/анекдоты-анекдоты-про-вовочку-327986.jpeg", update.getMessage().getChatId().toString());
        } else if(randomAnekdot == 1){
            sendImageFromUrl("http://img1.reactor.cc/pics/post/анекдоты-ржачные-анекдоты-209355.jpeg", update.getMessage().getChatId().toString());
        } else if(randomAnekdot == 2){
            sendImageFromUrl("http://i4.tabor.ru/feed/2017-04-14/15790550/420637_760x500.jpg", update.getMessage().getChatId().toString());
        } else if(randomAnekdot == 3){
            sendImageFromUrl("https://i2.tabor.ru/feed/2016-06-16/14092495/76618_760x500.jpg", update.getMessage().getChatId().toString());
        } else if(randomAnekdot == 4){
            sendImageFromUrl("http://okayno.club/wp-content/uploads/2017/08/1393836404_2014-02-28_162444.984.jpg", update.getMessage().getChatId().toString());
        } else if(randomAnekdot == 5){
            sendImageFromUrl("http://semicwetik.ru/wp-content/uploads/2017/09/anekdot-chut-zhivot-ne-porvala-3.jpg", update.getMessage().getChatId().toString());
        } else if(randomAnekdot == 6 ){
            sendImageFromUrl("https://i2.tabor.ru/feed/2016-09-01/12889106/151846_760x500.jpg", update.getMessage().getChatId().toString());
        } else if(randomAnekdot == 7){
            sendImageFromUrl("http://okayno.club/wp-content/uploads/2017/03/44795_760x500.jpg", update.getMessage().getChatId().toString());
        }
    }
    public boolean chekTranslate(Update update){
        String message = update.getMessage().getText();
        boolean bool = false;
        String[] words = message.split(" ");
        String chek = words[1] + " " + words[2];
        if(chek.equals("Переведи слово")){
            bool = true;
        }
        return bool;
    }
    private void translator(Update update){
        String message = update.getMessage().getText();
        String[] words = message.split(" ");
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        String result = "";
        try {
            url = new URL("https://dictionary.yandex.net/api/v1/dicservice/lookup" +
                    "?key=мой_ключ" +
                    "&lang=en-ru" +
                    "&text=" + words[3]);

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result += line;
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendMessage(update, "Перевод: " + result);
    }
}
