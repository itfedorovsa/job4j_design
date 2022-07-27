package ru.job4j.ood.lsp.controlquality;

import java.time.LocalDate;

public class Milk extends Food {

    public Milk(String food, LocalDate expiryDate, LocalDate createDate, float price, float discount) {
        super(food, expiryDate, createDate, price, discount);
    }

    @Override
    public String getFood() {
        return super.getFood();
    }

    @Override
    public void setFood(String food) {
        super.setFood(food);
    }

    @Override
    public LocalDate getExpiryDate() {
        return super.getExpiryDate();
    }

    @Override
    public void setExpiryDate(LocalDate expiryDate) {
        super.setExpiryDate(expiryDate);
    }

    @Override
    public LocalDate getCreateDate() {
        return super.getCreateDate();
    }

    @Override
    public void setCreateDate(LocalDate createDate) {
        super.setCreateDate(createDate);
    }

    @Override
    public float getPrice() {
        return super.getPrice();
    }

    @Override
    public void setPrice(float price) {
        super.setPrice(price);
    }

    @Override
    public float getDiscount() {
        return super.getDiscount();
    }

    @Override
    public void setDiscount(float discount) {
        super.setDiscount(discount);
    }
}
