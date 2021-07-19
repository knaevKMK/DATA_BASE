package softuni.exam.models.dto.input.xml;

//import softuni.exam.config.LocalDateTimeAdapter;
import softuni.exam.models.entities.PassengerEntity;
import softuni.exam.models.entities.PlaneEntity;
import softuni.exam.models.entities.TownEntity;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.FIELD)

public class TicketXmlDto {

    @XmlElement(name = "serial-number")
    private String serialNumber;
    @XmlElement
    private BigDecimal price;

    @XmlElement(name = "take-off")
//    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private String takeoff;

    @XmlElement(name = "from-town")
    private TownNameXmlDto fromTown;

    @XmlElement(name = "passenger")
    private PassengerEntity passenger;

    @XmlElement(name = "plane")
    private PlaneXmlDto plane;

    @XmlElement(name = "to-town")
    private TownNameXmlDto toTown;

    public TicketXmlDto() {
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public TicketXmlDto setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public TicketXmlDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getTakeoff() {
        return takeoff;
    }

    public TicketXmlDto setTakeoff(String takeoff) {
        this.takeoff = takeoff;
        return this;
    }

    public TownNameXmlDto getFromTown() {
        return fromTown;
    }

    public TicketXmlDto setFromTown(TownNameXmlDto fromTown) {
        this.fromTown = fromTown;
        return this;
    }

    public PassengerEntity getPassenger() {
        return passenger;
    }

    public TicketXmlDto setPassenger(PassengerEntity passenger) {
        this.passenger = passenger;
        return this;
    }

    public PlaneXmlDto getPlane() {
        return plane;
    }

    public TicketXmlDto setPlane(PlaneXmlDto plane) {
        this.plane = plane;
        return this;
    }

    public TownNameXmlDto getToTown() {
        return toTown;
    }

    public TicketXmlDto setToTown(TownNameXmlDto toTown) {
        this.toTown = toTown;
        return this;
    }
}
