package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "seller")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerImportXmlDto {
    @XmlElement
    private Integer id;

    public SellerImportXmlDto() {
    }

    public Integer getId() {
        return id;
    }

    public SellerImportXmlDto setId(Integer id) {
        this.id = id;
        return this;
    }
}
