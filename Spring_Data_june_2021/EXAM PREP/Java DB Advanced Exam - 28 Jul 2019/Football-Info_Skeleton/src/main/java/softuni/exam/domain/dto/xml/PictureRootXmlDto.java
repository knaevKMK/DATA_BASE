package softuni.exam.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "pictures")
@XmlAccessorType(XmlAccessType.FIELD)
public class PictureRootXmlDto {
    @XmlElement(name = "picture")
    private Set<PictureXmlUrlDto> pictureDto;

    public PictureRootXmlDto() {
    }

    public Set<PictureXmlUrlDto> getPictureDto() {
        return pictureDto;
    }

    public PictureRootXmlDto setPictureDto(Set<PictureXmlUrlDto> pictureDto) {
        this.pictureDto = pictureDto;
        return this;
    }
}
