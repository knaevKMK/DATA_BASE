package softuni.exam.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "teams")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamRootXmlDto {

    @XmlElement(name = "team")
    private Set<TeamXmlNameDto>teamXmlNameDtos;

    public TeamRootXmlDto() {
    }

    public Set<TeamXmlNameDto> getTeamXmlNameDtos() {
        return teamXmlNameDtos;
    }

    public TeamRootXmlDto setTeamXmlNameDtos(Set<TeamXmlNameDto> teamXmlNameDtos) {
        this.teamXmlNameDtos = teamXmlNameDtos;
        return this;
    }
}
