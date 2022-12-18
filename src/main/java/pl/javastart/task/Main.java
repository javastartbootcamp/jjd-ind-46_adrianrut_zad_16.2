package pl.javastart.task;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run(new Scanner(System.in));
    }

    public void run(Scanner scanner) {
        String dataAndTimeFormat = "yyyy-MM-dd HH:mm:ss";
        String dateFormat = "yyyy-MM-dd";
        String dateAndTimeWithDotsFormat = "dd.MM.yyyy HH:mm:ss";
        DateTimeFormatter dataAndTimePattern = DateTimeFormatter.ofPattern(dataAndTimeFormat);
        DateTimeFormatter datePattern = DateTimeFormatter.ofPattern(dateFormat);
        DateTimeFormatter dateAndTimeWithDotsPattern = DateTimeFormatter.ofPattern(dateAndTimeWithDotsFormat);
        List<DateTimeFormatter> formatters = Arrays.asList(dataAndTimePattern, datePattern, dateAndTimeWithDotsPattern);
        System.out.println("Podaj datę");
        String dataInput = scanner.nextLine();
        String dateWithTime = dataInput + " 00:00:00";
        LocalDateTime dateTime = null;
        for (DateTimeFormatter formatter : formatters) {
            try {
                dateTime = LocalDateTime.parse(dataInput, formatter);
            } catch (DateTimeParseException e) {
                if (dataInput.length() <= 10) {
                    dateTime = LocalDateTime.parse(dateWithTime, dataAndTimePattern);
                }
            }
        }

        printResult(dataAndTimePattern, dateTime);

    }

    private static void printResult(DateTimeFormatter dataAndTimePattern, LocalDateTime dateTime) {
        ZonedDateTime localTime = dateTime.atZone(ZoneId.systemDefault());
        System.out.println("Czas lokalny: " + localTime.format(dataAndTimePattern));
        System.out.println("UTC: " + localTime.withZoneSameInstant(ZoneId.of("UTC")).format(dataAndTimePattern));
        System.out.println("Londyn: " + localTime.withZoneSameInstant(ZoneId.of("Europe/London")).format(dataAndTimePattern));
        System.out.println("Los Angeles: " + localTime.withZoneSameInstant(ZoneId.of("America/Los_Angeles")).format(dataAndTimePattern));
        System.out.println("Sydney: " + localTime.withZoneSameInstant(ZoneId.of("Australia/Sydney")).format(dataAndTimePattern));
    }

}


