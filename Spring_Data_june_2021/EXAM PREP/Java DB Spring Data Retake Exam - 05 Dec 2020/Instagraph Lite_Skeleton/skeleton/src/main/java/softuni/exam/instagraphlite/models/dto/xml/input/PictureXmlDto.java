package softuni.exam.instagraphlite.models.dto.xml.input;


import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "picture")
@XmlAccessorType(XmlAccessType.FIELD)
public class PictureXmlDto {
    @XmlElement(name = "path")
    private String path;

    public PictureXmlDto() {
    }

    @NotNull
    public String getPath() {
        return path;
    }

    public PictureXmlDto setPath(String path) {
        this.path = path;
        return this;
    }
}
