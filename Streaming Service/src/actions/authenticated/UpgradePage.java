package actions.authenticated;

import actions.Constants;
import actions.Page;
import actions.PrintOutput;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Database;
import input.ActionsInput;

public final class UpgradePage implements Page {
    private boolean isError;
    private ActionsInput action;
    private ArrayNode output;

    public UpgradePage(final ActionsInput action, final ArrayNode output) {
        this.action = action;
        this.output = output;
        this.isError = false;
    }

    /** Changes the page to Upgrade **/
    @Override
    public void changePage() {
        if (Database.getDatabase().getCurrentPage().equals("Homepage autentificat")
                || Database.getDatabase().getCurrentPage().equals("see details")) {
            Database.getDatabase().setCurrentPage("upgrades");
        } else {
            new PrintOutput(true, output);
        }


    }

    /** The user upgrades his account to premium **/
    public void buyPremium() {
        if (Database.getDatabase().getCurrentUser().getTokensCount() >= Constants.PREMIUM_PRICE) {
            Database.getDatabase().getCurrentUser()
                    .setTokensCount(Database.getDatabase().getCurrentUser().getTokensCount()
                            - Constants.PREMIUM_PRICE);
            Database.getDatabase().getCurrentUser().getCredentials().setAccountType("premium");
        } else {
            new PrintOutput(false, output);
        }
    }

    /** The user buys token **/
    public void buyTokens() {
        if (Database.getDatabase().getCurrentUser().getCredentials().getBalance()
                >= action.getCount()) {
            Database.getDatabase().getCurrentUser().getCredentials()
                    .setBalance(Database.getDatabase().getCurrentUser().getCredentials()
                            .getBalance() - action.getCount());
            Database.getDatabase().getCurrentUser().setTokensCount(Database.getDatabase()
                    .getCurrentUser().getTokensCount() + action.getCount());
        } else {
            new PrintOutput(false, output);
        }
    }


}
