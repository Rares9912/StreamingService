package input;

import java.util.ArrayList;

public final class Input {
    private ArrayList<UserInput> users;
    private ArrayList<MoviesInput> movies;
    private ArrayList<ActionsInput> actions;

    public ArrayList<MoviesInput> getMovies() {
        return movies;
    }

    public ArrayList<ActionsInput> getActions() {
        return actions;
    }

    public void setMovies(final ArrayList<MoviesInput> movies) {
        this.movies = movies;
    }

    public void setActions(final ArrayList<ActionsInput> actions) {
        this.actions = actions;
    }

    public ArrayList<UserInput> getUsers() {
        return users;
    }

    public void setUsers(final ArrayList<UserInput> users) {
        this.users = users;
    }
}
