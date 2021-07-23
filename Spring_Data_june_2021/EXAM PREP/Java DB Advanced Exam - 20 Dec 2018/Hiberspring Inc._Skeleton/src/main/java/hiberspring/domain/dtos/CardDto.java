package hiberspring.domain.dtos;

import com.google.gson.annotations.Expose;

public class CardDto {
    @Expose
    private String number;

    public CardDto() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
