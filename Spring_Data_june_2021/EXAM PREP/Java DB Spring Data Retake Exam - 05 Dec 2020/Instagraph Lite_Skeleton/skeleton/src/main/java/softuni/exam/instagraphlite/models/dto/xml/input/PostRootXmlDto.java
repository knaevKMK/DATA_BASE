package softuni.exam.instagraphlite.models.dto.xml.input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "posts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostRootXmlDto {
    @XmlElement(name = "post")
    private List<PostXmlUserPictureDto> postRootXmlDtos;

    public PostRootXmlDto() {
    }

    public List<PostXmlUserPictureDto> getPostRootXmlDtos() {
        return postRootXmlDtos;
    }

    public PostRootXmlDto setPostRootXmlDtos(List<PostXmlUserPictureDto> postRootXmlDtos) {
        this.postRootXmlDtos = postRootXmlDtos;
        return this;
    }
}
