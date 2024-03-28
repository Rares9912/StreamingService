package implementation;

import actions.PrintOutput;
import actions.authenticated.Logout;
import actions.authenticated.MoviePage;
import actions.authenticated.SeeDetailsPage;
import actions.authenticated.UpgradePage;
import actions.unauthenticated.LoginPage;
import actions.unauthenticated.RegisterPage;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Database;
import input.ActionsInput;
import input.Input;


public class POOTV {
    public POOTV(final Input input, final ArrayNode output) {
        Database.getDatabase().instantiate(input);

        for (int i = 0; i < input.getActions().size(); i++) {
            ActionsInput action = input.getActions().get(i);
            LoginPage loginPage = new LoginPage(input, output, action);
            RegisterPage registerPage = new RegisterPage(input, output, action);
            Logout logout = new Logout(output);
            SeeDetailsPage seeDetailsPage = new SeeDetailsPage(action, output);
            UpgradePage upgradePage = new UpgradePage(action, output);
            MoviePage moviePage = new MoviePage(input, output, action);
            switch (action.getType()) {
                case "change page":
                    switch (action.getPage()) {
                        case "login" -> loginPage.changePage();
                        case "register" -> registerPage.changePage();
                        case "logout" -> logout.changePage();
                        case "movies" -> moviePage.changePage();
                        case "see details" -> seeDetailsPage.changePage();
                        case "upgrades" -> upgradePage.changePage();
                        default -> {
                        }
                    }
                    break;

                case "on page":
                    switch (action.getFeature()) {
                        case "login" -> loginPage.login();
                        case "register" -> registerPage.register();
                        case "search" -> moviePage.search();
                        case "filter" -> moviePage.filter();
                        case "purchase" -> seeDetailsPage.purchase();
                        case "watch" -> seeDetailsPage.watch();
                        case "like" -> seeDetailsPage.like();
                        case "rate" -> seeDetailsPage.rate();
                        case "buy tokens" -> upgradePage.buyTokens();
                        case "buy premium account" -> upgradePage.buyPremium();
                        default -> {

                        }
                    }
                default:
                }
            }
        }
    }


