package softuni.exam.models.dto.input.xml;

import org.springframework.lang.NonNull;
import softuni.exam.models.entities.TicketEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "plane")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlaneXmlDto {
    @XmlElement(name = "register-number")
    private String registerNumber;
    @XmlElement
    private int capacity;
    @XmlElement
    private String airline;

    public PlaneXmlDto() {
    }

    @Size(min = 5)
    @NotNull
    public String getRegisterNumber() {
        return registerNumber;
    }

    public PlaneXmlDto setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
        return this;
    }

    @Positive
    public int getCapacity() {
        return capacity;
    }

    public PlaneXmlDto setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    @Size(min = 2)
    public String getAirline() {
        return airline;
    }

    public PlaneXmlDto setAirline(String airline) {
        this.airline = airline;
        return this;
    }
}
