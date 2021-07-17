package com.gameHall.user.services;

public interface OrderService {

    String addGameToCard(String gameName);

    String removeGameFromCard(String gameName);

    String buyItems();
}
