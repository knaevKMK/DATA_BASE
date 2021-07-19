package softuni.exam.instagraphlite.models.dto.json.input;

import com.google.gson.annotations.Expose;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.entities.Picture;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


public class UserJsonDto {
    @Expose
    private String username;
    @Expose
    private String password;
    @Expose
    private String profilePicture;

    public UserJsonDto() {
    }

    @NotNull
    @Size(min = 2, max = 18)
    public String getUsername() {
        return username;
    }

    public UserJsonDto setUsername(String username) {
        this.username = username;
        return this;
    }

    @NotNull
    @Size(min = 4)
    public String getPassword() {
        return password;
    }

    public UserJsonDto setPassword(String password) {
        this.password = password;
        return this;
    }

    @NotNull
    public String getProfilePicture() {
        return profilePicture;
    }

    public UserJsonDto setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }
}
