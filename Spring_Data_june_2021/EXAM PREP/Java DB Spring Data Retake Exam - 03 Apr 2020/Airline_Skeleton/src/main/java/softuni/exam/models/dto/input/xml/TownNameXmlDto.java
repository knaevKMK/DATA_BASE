package softuni.exam.models.dto.input.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "town")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownNameXmlDto {

    @XmlElement(name="name")
    private String name;

    public TownNameXmlDto() {
    }

    public String getName() {
        return name;
    }

    public TownNameXmlDto setName(String name) {
        this.name = name;
        return this;
    }
}
