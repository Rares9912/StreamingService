package actions.unauthenticated;

import actions.Page;
import actions.PrintOutput;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Database;
import input.Input;
import input.ActionsInput;
import input.UserInput;

import java.util.ArrayList;

public class RegisterPage implements Page {
    private Input input;
    private ArrayNode output;
    private boolean isError;
    private ActionsInput action;
    public RegisterPage(final Input input, final ArrayNode output, final ActionsInput action) {
        this.input = input;
        this.output = output;
        this.isError = false;
        this.action = action;
    }

    @Override
    public void changePage() {
        if (Database.getDatabase().isAuth()) {
            new PrintOutput(true, output);
        } else {
            Database.getDatabase().setCurrentPage("register");
        }

    }

    public void register() {
        if (!Database.getDatabase().getCurrentPage().equals("register")) {
            isError = true;
        } else {
            for (int i = 0; i < input.getUsers().size(); i++) {
                if (action.getCredentials().getName().equals(input.getUsers().get(i)
                        .getCredentials().getName())) {
                    isError = true;
                } else {
                    input.getUsers().add(new UserInput(action.getCredentials()));
                    Database.getDatabase().setCurrentPage("Homepage autentificat");
                    Database.getDatabase().setCurrentUser(input.getUsers()
                            .get(input.getUsers().size()- 1));
                    Database.getDatabase().setAuth(true);
                    Database.getDatabase().getCurrentUser().setWatchedMovies(new ArrayList<>());
                    Database.getDatabase().getCurrentUser().setRatedMovies(new ArrayList<>());
                    Database.getDatabase().getCurrentUser().setLikedMovies(new ArrayList<>());
                    Database.getDatabase().getCurrentUser().setPurchasedMovies(new ArrayList<>());
                    Database.getDatabase().getCurrentUser().setTokensCount(0);
                    Database.getDatabase().getCurrentUser().setNumFreePremiumMovies(15);
                    break;
                }
            }
        }
        new PrintOutput(isError, output);
    }
}
