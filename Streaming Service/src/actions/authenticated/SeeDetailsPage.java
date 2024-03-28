package actions.authenticated;

import actions.Constants;
import actions.Page;
import actions.PrintOutput;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Database;
import input.ActionsInput;
import input.MoviesInput;

import java.util.ArrayList;

public final class SeeDetailsPage implements Page {

    private boolean isError;
    private ActionsInput action;
    private ArrayNode output;

    public SeeDetailsPage(final ActionsInput action, final ArrayNode output) {
        this.isError = false;
        this.action = action;
        this.output = output;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(final boolean error) {
        isError = error;
    }

    public ActionsInput getAction() {
        return action;
    }

    public void setAction(final ActionsInput action) {
        this.action = action;
    }

    private boolean isMovieInList() {
        for (MoviesInput movie : Database.getDatabase().getCurrentMoviesList()) {
            if (movie.getName().equals(action.getMovie())) {
                return true;
            }
        }
        return false;
    }

    private boolean isPurchasedMovie() {
        if (Database.getDatabase().getCurrentUser().getPurchasedMovies() != null) {
            for (MoviesInput movie : Database.getDatabase().getCurrentUser().getPurchasedMovies()) {
                if (movie.getName().equals(Database.getDatabase().getCurrentMoviesList().get(0)
                        .getName())) {
                    return true;
                }
            }
        } else {
            return false;
        }
        return false;
    }

    private boolean isWatchedMovie() {
        if (Database.getDatabase().getCurrentUser().getWatchedMovies() != null) {
            for (MoviesInput movie : Database.getDatabase().getCurrentUser().getWatchedMovies()) {
                if (movie.getName().equals(Database.getDatabase().getCurrentMoviesList().get(0)
                        .getName())) {
                    return true;
                }
            }
        } else {
            return false;
        }

        return false;

    }

    @Override
    public void changePage() {
        if (!Database.getDatabase().getCurrentPage().equals("movies")) {
            isError = true;
        } else if (!isMovieInList()) {
            isError = true;
        } else {
            for (MoviesInput movie : Database.getDatabase().getCurrentMoviesList()) {
                if (movie.getName().equals(action.getMovie())) {
                    Database.getDatabase().setCurrentMoviesList(new ArrayList<>());
                    Database.getDatabase().getCurrentMoviesList().add(movie);
                    break;
                }
            }
            Database.getDatabase().setCurrentPage("see details");
        }

        new PrintOutput(isError, output);
    }

    public void purchase() {
        if (!Database.getDatabase().getCurrentPage().equals("see details")) {
            isError = true;
        } else if (isPurchasedMovie()) {
            isError = true;
        } else {
            if (Database.getDatabase().getCurrentUser().getCredentials().getAccountType()
                    .equals("premium")
                    && Database.getDatabase().getCurrentUser().getNumFreePremiumMovies() > 0) {
                Database.getDatabase().getCurrentUser().getPurchasedMovies()
                        .add(Database.getDatabase().getCurrentMoviesList().get(0));
                Database.getDatabase().getCurrentUser().setNumFreePremiumMovies(Database
                        .getDatabase().getCurrentUser().getNumFreePremiumMovies() - 1);
            } else {
                if (Database.getDatabase().getCurrentUser().getTokensCount() > 2) {
                    Database.getDatabase().getCurrentUser().getPurchasedMovies()
                            .add(Database.getDatabase().getCurrentMoviesList().get(0));
                    Database.getDatabase().getCurrentUser().setTokensCount(Database.getDatabase()
                            .getCurrentUser().getTokensCount() - 2);
                } else {
                    isError = true;
                }
            }
        }
        new PrintOutput(isError, output);
    }

    public void watch() {
        if (!Database.getDatabase().getCurrentPage().equals("see details")) {
            isError = true;
        } else if (!isPurchasedMovie()) {
            isError = true;
        } else {
            Database.getDatabase().getCurrentUser().getWatchedMovies().add(Database.getDatabase()
                    .getCurrentMoviesList().get(0));
        }
        new PrintOutput(isError, output);
    }

    public void like() {
        if (!Database.getDatabase().getCurrentPage().equals("see details")) {
            isError = true;
        } else if (!isWatchedMovie()) {
            isError = true;
        } else {
            Database.getDatabase().getCurrentMoviesList().get(0).setNumLikes(Database.getDatabase()
                    .getCurrentMoviesList().get(0).getNumLikes() + 1);
            Database.getDatabase().getCurrentUser().getLikedMovies().add(Database.getDatabase()
                    .getCurrentMoviesList().get(0));
        }
        new PrintOutput(isError, output);
    }

    public void rate() {
        if (!Database.getDatabase().getCurrentPage().equals("see details")) {
            isError = true;
        } else if (!isWatchedMovie()) {
            isError = true;
        } else if (action.getRate() < Constants.MINIMUM_RATING
                || action.getRate() > Constants.MAXIMUM_RATING) {
            isError = true;
        } else {
            MoviesInput movie = Database.getDatabase().getCurrentMoviesList().get(0);
            movie.getRatingsArray().add(action.getRate());
            movie.setNumRatings(movie.getNumRatings() + 1);
            Database.getDatabase().getCurrentUser().getRatedMovies().add(movie);
            int ratingsSum = 0;
            for (int i = 0; i < movie.getRatingsArray().size(); i++) {
                ratingsSum += movie.getRatingsArray().get(i);
            }
            movie.setRating(ratingsSum / movie.getNumRatings());
        }
        new PrintOutput(isError, output);
    }
}
