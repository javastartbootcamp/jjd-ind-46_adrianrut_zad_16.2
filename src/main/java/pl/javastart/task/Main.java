package pl.javastart.task;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run(new Scanner(System.in));
    }

    public void run(Scanner scanner) {
        String dataFormat = "yyyy-MM-dd HH:mm:ss";
        String tempHour = " 00:00:00";
        DateTimeFormatter datePattern = DateTimeFormatter.ofPattern(dataFormat);
        LocalDateTime localDateTime;
        System.out.println("Podaj datę");
        String dataInput = scanner.nextLine();
        if (dataInput.length() <= 10) {
            localDateTime = LocalDateTime.parse(dataInput + tempHour, datePattern);
            System.out.println(localDateTime.format(datePattern));
        } else {
            localDateTime = LocalDateTime.parse(dataInput, datePattern);
        }
        ZonedDateTime sydney = localDateTime.atZone(ZoneId.of("Australia/Sydney"));
        System.out.println("Czas lokalny: " + localDateTime.atZone(ZoneId.systemDefault()).format(datePattern));
        System.out.println("UTC: " + localDateTime.atZone(ZoneId.of("UTC")).format(datePattern));
        System.out.println("Londyn: " + localDateTime.atZone(ZoneId.of("Europe/London")).format(datePattern));
        System.out.println("Los Angeles: " + localDateTime.atZone(ZoneId.of("America/Los_Angeles")).format(datePattern));
        System.out.println("Sydney: " + sydney.format(datePattern));

    }

}


