package softuni.exam.instagraphlite.models.dto.xml.input;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserXmlUsernameDto {

    @XmlElement(name = "username")
    private String username;

    public UserXmlUsernameDto() {
    }
@NotNull
    public String getUsername() {
        return username;
    }

    public UserXmlUsernameDto setUsername(String username) {
        this.username = username;
        return this;
    }
}
