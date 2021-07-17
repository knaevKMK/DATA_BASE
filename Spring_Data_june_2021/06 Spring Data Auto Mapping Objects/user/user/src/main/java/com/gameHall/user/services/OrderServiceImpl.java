package com.gameHall.user.services;

import com.gameHall.user.models.entities.GameEntity;
import com.gameHall.user.models.entities.OrderEntity;
import com.gameHall.user.models.entities.UserEntity;
import com.gameHall.user.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final GameService gameService;
    private final ModelMapper modelMapper;
    private OrderEntity orderEntity;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, GameService gameService, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.gameService = gameService;
        this.modelMapper = modelMapper;
        orderEntity = new OrderEntity();
    }


    @Override
    public String addGameToCard(String gameName) {
        UserEntity currentUser = getUser();
        if (currentUser == null) {
            return "Please login before use your shopping card";
        }

        GameEntity game = gameService.findGameByTitle(gameName).orElse(null);
        if (game == null) {
            return ("Game with title: " + gameName + " does not exist");
        }


        if (hasGame(currentUser.getId(), game.getId())) {
            return ("You already bought this game");
        }

        if (orderEntity.getBuyer() == null) {
            orderEntity.setBuyer(currentUser);
        }
        Set<GameEntity> currentGamesInCArd =
                orderEntity.getGames() == null ? new HashSet<>() : orderEntity.getGames();

        if (currentGamesInCArd.stream().anyMatch(g -> g.getTitle().equals(gameName))) {
            return gameName + " already added to you shopping card ";
        }
        currentGamesInCArd.add(game);
        orderEntity.setGames(currentGamesInCArd);
        return gameName + " added to cart.";
    }

    private UserEntity getUser() {
        return (userService.isLoged());
    }

    private boolean hasGame(Long currentUserId, Long gameId) {
        OrderEntity order = orderRepository.findAllByUserIdAndGameId(currentUserId, gameId);
        return order != null;
    }

    @Override
    public String removeGameFromCard(String gameName) {
        UserEntity currentUser = getUser();
        if (currentUser == null) {
            return "Please login before use your shopping card";
        }
        //if order was not cleared from last use user
        if (orderEntity.getBuyer() == null || !currentUser.getId().equals(orderEntity.getBuyer().getId())) {
            clearCard();
        }
        if (orderEntity.getGames() == null || orderEntity.getGames().isEmpty()) {
            return ("Your shopping card is empty");
        }

        if (orderEntity.getGames().stream().noneMatch(gameEntity -> gameEntity.getTitle().equals(gameName))) {
            return ("Your shopping card has not the game: " + gameName);
        }
        orderEntity.getGames().removeIf(g -> g.getTitle().equals(gameName));

        return gameName + " removed from cart.";
    }

    @Override
    public String buyItems() {
        UserEntity currentUser = getUser();
        if (currentUser == null) {
            return "Please login before use your shopping card";
        }
        if (orderEntity.getBuyer() == null || !currentUser.getId().equals(orderEntity.getBuyer().getId())) {
            clearCard();
        }

        if (orderEntity.getGames() == null || orderEntity.getGames().isEmpty()) {
            return ("Nothing to buy");
        }
        orderEntity.setCompleted(true);
        orderRepository.save(orderEntity);
        String result = orderEntity.getGames().stream().map(g -> "\t-" + g.getTitle())
                .collect(Collectors.joining(System.lineSeparator()));
        clearCard();
        return "Successfully bought games: \n" +
                result;
    }


    public void clearCard() {
        this.orderEntity = new OrderEntity();
        orderEntity.setCompleted(false)
                .setGames(new HashSet<>());
    }
}
