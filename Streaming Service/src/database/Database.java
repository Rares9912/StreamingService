package database;

import actions.Constants;
import input.Input;
import input.MoviesInput;
import input.UserInput;

import java.util.ArrayList;

public final class Database {
    private String currentPage;
    private UserInput currentUser;
    private ArrayList<MoviesInput> currentMoviesList;
    private boolean isAuth;


    /** Singleton Pattern **/
    private static Database database = null;

    /** Access the database **/
    public static Database getDatabase() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    /** Method needed after accessing the platform **/
    public void instantiate(final Input input) {
        this.currentUser = new UserInput();
        this.currentMoviesList = new ArrayList<>();
        this.currentPage = "Homepage neautentificat";
        this.isAuth = false;
        for (UserInput user : input.getUsers()) {
            user.setPurchasedMovies(new ArrayList<>());
            user.setLikedMovies(new ArrayList<>());
            user.setRatedMovies(new ArrayList<>());
            user.setWatchedMovies(new ArrayList<>());
            user.setNumFreePremiumMovies(Constants.NUM_FREE_PREMIUM_MOVIES);
        }

        for (MoviesInput movie : input.getMovies()) {
            movie.setRatingsArray(new ArrayList<>());
        }
    }

    public boolean isAuth() {
        return isAuth;
    }

    public void setAuth(final boolean auth) {
        isAuth = auth;
    }

    public ArrayList<MoviesInput> getCurrentMoviesList() {
        return currentMoviesList;
    }

    public void setCurrentMoviesList(final ArrayList<MoviesInput> currentMoviesList) {
        this.currentMoviesList = currentMoviesList;
    }

    public UserInput getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(final UserInput currentUser) {
        this.currentUser = currentUser;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(final String currentPage) {
        this.currentPage = currentPage;
    }
}
