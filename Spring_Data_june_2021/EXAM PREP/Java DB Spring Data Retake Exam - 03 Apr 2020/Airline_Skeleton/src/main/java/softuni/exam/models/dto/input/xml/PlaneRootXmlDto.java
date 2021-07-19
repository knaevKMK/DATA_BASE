package softuni.exam.models.dto.input.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "planes")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlaneRootXmlDto {

    @XmlElement(name = "plane")
private List<PlaneXmlDto> planeXmlDtos;

    public PlaneRootXmlDto() {
    }

    public List<PlaneXmlDto> getPlaneXmlDtos() {
        return planeXmlDtos;
    }

    public PlaneRootXmlDto setPlaneXmlDtos(List<PlaneXmlDto> planeXmlDtos) {
        this.planeXmlDtos = planeXmlDtos;
        return this;
    }
}
