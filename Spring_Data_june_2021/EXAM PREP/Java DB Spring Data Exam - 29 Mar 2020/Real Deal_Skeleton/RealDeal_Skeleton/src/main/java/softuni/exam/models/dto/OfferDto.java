package softuni.exam.models.dto;

import org.hibernate.validator.constraints.Length;
import softuni.exam.config.LocalDateTimeAdapter;
import softuni.exam.models.entities.CarEntity;
import softuni.exam.models.entities.PictureEntity;
import softuni.exam.models.entities.SellerEntity;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferDto {


    @XmlElement
    private BigDecimal price;
    @XmlElement
    private String description;
    @XmlElement(name = "has-gold-status")
    private boolean hasGoldStatus;
    @XmlElement(name = "added-on")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime addedOn;

    @XmlElement(name = "car")
    private CarImportXmlDto car;
    @XmlElement(name = "seller")
    private SellerImportXmlDto seller;

    public OfferDto() {
    }

//    @DecimalMin(value = "0.0")
    public BigDecimal getPrice() {
        return price;
    }

    public OfferDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

//    @Length(min = 5)
    public String getDescription() {
        return description;
    }

    public OfferDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isHasGoldStatus() {
        return hasGoldStatus;
    }

    public OfferDto setHasGoldStatus(boolean hasGoldStatus) {
        this.hasGoldStatus = hasGoldStatus;
        return this;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public OfferDto setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    public CarImportXmlDto getCar() {
        return car;
    }

    public OfferDto setCar(CarImportXmlDto car) {
        this.car = car;
        return this;
    }

    public SellerImportXmlDto getSeller() {
        return seller;
    }

    public OfferDto setSeller(SellerImportXmlDto seller) {
        this.seller = seller;
        return this;
    }


}
