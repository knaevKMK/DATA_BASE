@XmlJavaTypeAdapters({
        @XmlJavaTypeAdapter(type=LocalDateTime.class, value= LocalDateTimeAdapter.class)
})
package softuni.exam.models.entities;

import softuni.exam.config.LocalDateTimeAdapter;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;