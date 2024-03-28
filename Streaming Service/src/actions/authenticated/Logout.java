package actions.authenticated;

import actions.Page;
import actions.PrintOutput;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Database;

import java.util.ArrayList;

public final class Logout implements Page {
    private ArrayNode output;

    public Logout(final ArrayNode output) {
        this.output = output;
    }


    @Override
    public void changePage() {
        if (!Database.getDatabase().isAuth()) {
            new PrintOutput(true, output);
        } else {
            Database.getDatabase().setCurrentMoviesList(new ArrayList<>());
            Database.getDatabase().setCurrentUser(null);
            Database.getDatabase().setAuth(false);
            Database.getDatabase().setCurrentPage("Homepage neautentificat");
        }
    }
}
