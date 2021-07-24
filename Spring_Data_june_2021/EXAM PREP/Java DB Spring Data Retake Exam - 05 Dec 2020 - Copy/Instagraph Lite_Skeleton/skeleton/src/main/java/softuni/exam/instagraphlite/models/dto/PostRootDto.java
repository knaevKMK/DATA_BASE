package softuni.exam.instagraphlite.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "posts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostRootDto {
    @XmlElement(name = "post")
    private Set<PostDto> postsDto;

    public PostRootDto() {
    }

    public Set<PostDto> getPostsDto() {
        return postsDto;
    }

    public void setPostsDto(Set<PostDto> postsDto) {
        this.postsDto = postsDto;
    }
}
