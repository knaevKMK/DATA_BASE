package softuni.exam.models.dto.input.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "passenger")
@XmlAccessorType(XmlAccessType.FIELD)
public class PassengerNameXmlDto {
    @XmlElement(name = "name")
    private String name;

    public PassengerNameXmlDto() {
    }

    public String getName() {
        return name;
    }

    public PassengerNameXmlDto setName(String name) {
        this.name = name;
        return this;
    }
}
