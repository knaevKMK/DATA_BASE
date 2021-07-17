package com.gameHall.user;

import com.gameHall.user.models.dto.insert.GameEditDto;
import com.gameHall.user.models.dto.insert.GameInsertDto;
import com.gameHall.user.models.dto.insert.UserLoginDto;
import com.gameHall.user.models.dto.insert.UserRegisterDto;
import com.gameHall.user.services.GameService;
import com.gameHall.user.services.OrderService;
import com.gameHall.user.services.UserService;
import com.gameHall.user.util.IoUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final IoUtil ioUtil;
    private final GameService gameService;
    private final UserService userService;
    private final OrderService orderService;

    public CommandLineRunnerImpl(IoUtil ioUtil, GameService gameService, UserService userService, OrderService orderService) {
        this.ioUtil = ioUtil;
        this.gameService = gameService;

        this.userService = userService;
        this.orderService = orderService;
    }


    @Override
    public void run(String... args) throws Exception {
        try {
            while (true) {
                ioUtil.output("Enter 'end' to Terminate app or command: ");
                String[] rows = ioUtil.input();
                String result = null;
                boolean isLoged = false;
                for (String row : rows) {

                    String[] token = row.split("\\|");
                    switch (token[0]) {
                        case "end":
                            ioUtil.output("Program terminate");
                            return;
                        case "RegisterUser":
                            result = userService.registerUser(new UserRegisterDto(token[1], token[2], token[3], token[4]));
                            ;
                            break;
                        case "LoginUser":
                            result = userService.loginUser(new UserLoginDto(token[1], token[2]));
                            break;
                        case "Logout":
                            result = userService.logout();
                            break;
                        case "AddGame":
                            result = gameService.addGame(
                                    new GameInsertDto(token[1], token[2], token[3], token[4], token[5], token[6], token[7]));
                            break;
                        case "EditGame":
                            result = gameService.editGame(new GameEditDto(token));
                            break;
                        case "DeleteGame":
                            result = gameService.delete(Long.parseLong(token[1]));
                            break;
                        case "AllGames":
                            result = gameService.showAllGames();
                            break;
                        case "DetailGame":
                            System.out.println(token[1]);
                            result = gameService.detailsOfGameByName(token[1]);
                            break;
                        case "OwnedGames":
                            result = gameService.getOwnGames();
                            break;
                        case "AddItem":
                            result = orderService.addGameToCard(token[1]);
                            break;
                        case "RemoveItem":
                            result = orderService.removeGameFromCard(token[1]);
                            break;
                        case "BuyItem":
                            result = orderService.buyItems();
                            break;
                        default:
                            result = "bad input";
                            break;
                    }

                    ioUtil.output(result);

                }
            }


        } catch (Exception e) {
            ioUtil.output(e.getMessage());
        }


    }

}
