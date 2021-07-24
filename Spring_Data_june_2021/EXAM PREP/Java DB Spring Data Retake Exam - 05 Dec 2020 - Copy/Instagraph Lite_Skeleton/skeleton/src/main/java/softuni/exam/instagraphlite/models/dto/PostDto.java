package softuni.exam.instagraphlite.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "post")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostDto {

    @XmlElement(name = "caption")
    private String caption;

    @XmlElement(name = "user")
    private UserUsernameDto userDto;

    @XmlElement(name = "picture")
    private PicturepathDto picturepathDto;

    public PostDto() {
    }

    @NotNull
    public UserUsernameDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserUsernameDto userDto) {
        this.userDto = userDto;
    }

    @NotNull
    public PicturepathDto getPicturepathDto() {
        return picturepathDto;
    }

    public void setPicturepathDto(PicturepathDto picturepathDto) {
        this.picturepathDto = picturepathDto;
    }

    @NotNull
    @Size(min = 21)
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
