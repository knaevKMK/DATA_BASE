package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarImportXmlDto {
    @XmlElement(name = "id")
    private Integer id;

    public CarImportXmlDto() {

    }

    public Integer getId() {
        return id;
    }

    public CarImportXmlDto setId(Integer id) {
        this.id = id;
        return this;
    }
}
