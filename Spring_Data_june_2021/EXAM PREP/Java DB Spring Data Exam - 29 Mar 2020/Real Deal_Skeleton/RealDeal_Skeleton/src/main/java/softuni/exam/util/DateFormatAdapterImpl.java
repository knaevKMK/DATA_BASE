package softuni.exam.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatAdapterImpl implements DateFormatAdapter {
    @Override
    public LocalDate toLocalDate(String date, String pattern) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
    }

    @Override
    public LocalDateTime toLocalDateTime(String date, String pattern) {
        LocalDateTime time=null;
        try {
            time = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return time;
    }

}
