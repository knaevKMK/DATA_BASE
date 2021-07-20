package softuni.exam.domain.dto.xml;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "picture")
@XmlAccessorType(XmlAccessType.FIELD)
public class PictureXmlUrlDto {
    @XmlElement(name = "url")
    private String url;

    public PictureXmlUrlDto() {
    }

    @NotNull
    public String getUrl() {
        return url;
    }

    public PictureXmlUrlDto setUrl(String url) {
        this.url = url;
        return this;
    }
}
