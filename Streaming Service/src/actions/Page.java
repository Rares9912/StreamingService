package actions;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.ActionsInput;

public interface Page {
    ArrayNode output = null;
    ActionsInput action = null;
    void changePage();
}
