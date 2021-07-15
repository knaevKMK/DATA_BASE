package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sellers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerRootDto {
    @XmlElement(name="seller")
    private List<SellerDto> sellersDto;

    public SellerRootDto() {
    }

    public List<SellerDto> getSellersDto() {
        return sellersDto;
    }

    public SellerRootDto setSellersDto(List<SellerDto> sellersDto) {
        this.sellersDto = sellersDto;
        return this;
    }
}
