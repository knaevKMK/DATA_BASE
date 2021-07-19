package softuni.exam.models.dto.input.xml;


import javax.xml.bind.annotation.*;

import java.util.List;
import java.util.Set;

@XmlRootElement(name = "tickets")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketRootXmlDto {
    @XmlElement(name = "ticket")

    private List<TicketXmlDto> ticketXmlDtoList;

    public TicketRootXmlDto() {
    }

    public List<TicketXmlDto> getTicketXmlDtoList() {
        return ticketXmlDtoList;
    }

    public TicketRootXmlDto setTicketXmlDtoList(List<TicketXmlDto> ticketXmlDtoList) {
        this.ticketXmlDtoList = ticketXmlDtoList;
        return this;
    }
}
