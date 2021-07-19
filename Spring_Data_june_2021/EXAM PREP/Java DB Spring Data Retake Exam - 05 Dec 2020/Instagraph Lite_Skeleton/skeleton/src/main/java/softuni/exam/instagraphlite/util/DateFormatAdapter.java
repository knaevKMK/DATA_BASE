package softuni.exam.instagraphlite.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DateFormatAdapter {
   LocalDate toLocalDate( String date, String pattern);
   LocalDateTime toLocalDateTime(String date, String pattern);

}
