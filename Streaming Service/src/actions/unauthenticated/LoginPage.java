package actions.unauthenticated;

import actions.Page;
import actions.PrintOutput;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Database;
import input.Input;
import input.ActionsInput;

public class LoginPage implements Page {

    private Input input;
    private ArrayNode output;
    private boolean isError;
    private ActionsInput action;
    public LoginPage(Input input, ArrayNode output,
                     ActionsInput action) {
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
            Database.getDatabase().setCurrentPage("login");
        }
    }

    public void login() {
        if (!Database.getDatabase().getCurrentPage().equals("login")) {
            isError = true;
        } else {
            for (int i = 0; i < input.getUsers().size(); i++) {
                if (action.getCredentials().getName().equals(input.getUsers().get(i).getCredentials()
                        .getName())) {
                    if (action.getCredentials().getPassword().equals
                            (input.getUsers().get(i).getCredentials().getPassword())) {
                        Database.getDatabase().setCurrentUser(input.getUsers().get(i));
                        Database.getDatabase().setCurrentPage("Homepage autentificat");
                        Database.getDatabase().setAuth(true);
                        break;
                    }
                    isError = true;
                    break;
                }
            }
        }
        new PrintOutput(isError, output);
    }

}
