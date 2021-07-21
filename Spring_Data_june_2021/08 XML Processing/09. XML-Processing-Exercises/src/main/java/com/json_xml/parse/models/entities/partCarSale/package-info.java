@XmlJavaTypeAdapters({
        @XmlJavaTypeAdapter(type=LocalDateTime.class, value= LocalDateTimeAdapter.class)
})
package com.json_xml.parse.models.entities.partCarSale;

import com.json_xml.parse.config.LocalDateTimeAdapter;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;