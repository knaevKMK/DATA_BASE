package softuni.exam.instagraphlite.models.dto.xml.input;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "post")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostXmlUserPictureDto {

    @XmlElement(name = "caption")
    private String caption;
    @XmlElement(name = "user")
    private UserXmlUsernameDto user;
    @XmlElement(name = "picture")
    private PictureXmlDto picture;

    public PostXmlUserPictureDto() {
    }

    @NotNull
    @Size(min = 21)
    public String getCaption() {
        return caption;
    }

    public PostXmlUserPictureDto setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    @NotNull
    public UserXmlUsernameDto getUser() {
        return user;
    }

    public PostXmlUserPictureDto setUser(UserXmlUsernameDto user) {
        this.user = user;
        return this;
    }

    @NotNull
    public PictureXmlDto getPicture() {
        return picture;
    }

    public PostXmlUserPictureDto setPicture(PictureXmlDto picture) {
        this.picture = picture;
        return this;
    }
}
