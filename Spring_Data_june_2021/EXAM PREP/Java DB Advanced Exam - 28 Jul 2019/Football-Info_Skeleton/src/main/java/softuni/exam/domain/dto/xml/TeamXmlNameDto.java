package softuni.exam.domain.dto.xml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "team")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamXmlNameDto {

    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "picture")
    private PictureXmlUrlDto picture;

    public TeamXmlNameDto() {
    }

    @NotNull
    public PictureXmlUrlDto getPicture() {
        return picture;
    }

    public TeamXmlNameDto setPicture(PictureXmlUrlDto picture) {
        this.picture = picture;
        return this;
    }

    @NotNull
    @NotBlank
    public String getName() {
        return name;
    }

    public TeamXmlNameDto setName(String name) {
        this.name = name;
        return this;
    }
}
