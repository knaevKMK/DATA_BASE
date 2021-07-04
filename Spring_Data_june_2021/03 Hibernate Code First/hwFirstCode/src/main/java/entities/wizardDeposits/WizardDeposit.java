package entities.wizardDeposits;

import entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "wizard_deposits")
public class WizardDeposit extends BaseEntity {
    private String first_name;
    private String last_name;
    private String notes;
    private int age;
    private String magicWandCreator;
    private String getMagicWandSize;
    private String depositGroup;
    private LocalDateTime depositStartDate;
    private BigDecimal depositAmount;
    private BigDecimal depositInterest;
    private BigDecimal depositCharge;
    private LocalDateTime depositExpirationDate;
    private boolean isDepositExpired;

    public WizardDeposit() {
    }

    @Column(name = "first_name", length = 50)
    public String getFirst_name() {
        return first_name;
    }

    @Column(name = "last_name", length = 60)
    public String getLast_name() {
        return last_name;
    }

    @Column(columnDefinition = "TEXT", length = 1000)
    public String getNotes() {
        return notes;
    }

    @Column(columnDefinition = "INT UNSIGNED")
    public int getAge() {
        return age;
    }
    @Column(name = "magic_wand_creator", columnDefinition = "TEXT",length = 100)
    public String getMagicWandCreator() {
        return magicWandCreator;
    }
    @Column(name = "magic_wand_size", columnDefinition = "INT")
    public String getGetMagicWandSize() {
        return getMagicWandSize;
    }

    @Column(name = "deposit_group", columnDefinition = "TEXT")
    public String getDepositGroup() {
        return depositGroup;
    }

    @Column(name = "deposit_start_date", columnDefinition = "DATETIME")
    public LocalDateTime getDepositStartDate() {
        return depositStartDate;
    }
@Column(name = "deposit_amount", precision = 10, scale = 3)
    public BigDecimal getDepositAmount() {
        return depositAmount;
    }
    @Column(name = "deposit_interest", columnDefinition = "FLOAT")
    public BigDecimal getDepositInterest() {
        return depositInterest;
    }
    @Column(name = "deposit_charge", columnDefinition = "FLOAT")
    public BigDecimal getDepositCharge() {
        return depositCharge;
    }

    @Column(name = "deposit_expiration_date", columnDefinition = "DATETIME")
    public LocalDateTime getDepositExpirationDate() {
        return depositExpirationDate;
    }

    @Column(name = "is_deposit_expired", columnDefinition = "BOOLEAN")
    public boolean isDepositExpired() {
        return isDepositExpired;
    }


    //all setters
    public WizardDeposit setFirst_name(String first_name) {
        this.first_name = first_name;
        return this;
    }

    public WizardDeposit setLast_name(String last_name) {
        this.last_name = last_name;
        return this;
    }

    public WizardDeposit setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public WizardDeposit setAge(int age) {
        this.age = age;
        return this;
    }

    public WizardDeposit setMagicWandCreator(String magicWandCreator) {
        this.magicWandCreator = magicWandCreator;
        return this;
    }

    public WizardDeposit setGetMagicWandSize(String getMagicWandSize) {
        this.getMagicWandSize = getMagicWandSize;
        return this;
    }

    public WizardDeposit setDepositGroup(String depositGroup) {
        this.depositGroup = depositGroup;
        return this;
    }

    public WizardDeposit setDepositStartDate(LocalDateTime depositStartDate) {
        this.depositStartDate = depositStartDate;
        return this;
    }

    public WizardDeposit setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
        return this;
    }

    public WizardDeposit setDepositInterest(BigDecimal depositInterest) {
        this.depositInterest = depositInterest;
        return this;
    }

    public WizardDeposit setDepositCharge(BigDecimal depositCharge) {
        this.depositCharge = depositCharge;
        return this;
    }

    public WizardDeposit setDepositExpirationDate(LocalDateTime depositExpirationDate) {
        this.depositExpirationDate = depositExpirationDate;
        return this;
    }

    public WizardDeposit setDepositExpired(boolean depositExpired) {
        isDepositExpired = depositExpired;
        return this;
    }
}
