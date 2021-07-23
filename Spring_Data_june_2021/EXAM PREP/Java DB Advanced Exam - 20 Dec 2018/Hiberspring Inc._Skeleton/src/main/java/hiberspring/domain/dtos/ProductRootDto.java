package hiberspring.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductRootDto {

    @XmlElement (name="product")
    private Set<ProductNameClientsBranchNameDto> productsDto;

    public ProductRootDto() {
    }

    public Set<ProductNameClientsBranchNameDto> getProductsDto() {
        return productsDto;
    }

    public void setProductsDto(Set<ProductNameClientsBranchNameDto> productsDto) {
        this.productsDto = productsDto;
    }
}
