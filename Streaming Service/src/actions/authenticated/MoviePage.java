package actions.authenticated;

import actions.Page;
import actions.PrintOutput;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Database;
import input.ActionsInput;
import input.Input;
import input.MoviesInput;

import java.util.ArrayList;
import java.util.Collections;

public final class MoviePage implements Page {

    private boolean isBanned;
    private Input input;
    private ArrayNode output;
    private ActionsInput action;
    private boolean isError;

    public MoviePage(final Input input, final ArrayNode output, final ActionsInput action) {
        this.input = input;
        this.output = output;
        this.action = action;
        this.isError = false;
    }

    private void insertMoviesInList() {
        Database.getDatabase().setCurrentMoviesList(new ArrayList<>());
        for (MoviesInput movie : input.getMovies()) {
            isBanned = false;
            for (String countryBanned : movie.getCountriesBanned()) {
                if (Database.getDatabase().getCurrentUser().getCredentials().getCountry()
                        .equals(countryBanned)) {
                    isBanned = true;
                    break;
                }
            }
            if (!isBanned) {
                Database.getDatabase().getCurrentMoviesList().add(movie);
            }
        }

    }

    @Override
    public void changePage() {
        if (!Database.getDatabase().isAuth()) {
            isError = true;
        } else {
            insertMoviesInList();
            Database.getDatabase().setCurrentPage("movies");
        }
        new PrintOutput(isError, output);
    }

    /** Shows only movies that start with the given string **/
    public void search() {
        ArrayList<MoviesInput> moviesList = Database.getDatabase().getCurrentMoviesList();
        if (!Database.getDatabase().getCurrentMoviesList().isEmpty()) {
            for (int i = 0; i < moviesList.size(); i++) {
                if (!moviesList.get(i).getName().startsWith(action.getStartsWith())) {
                    moviesList.remove(i);
                    i--;
                }
            }
        }
        new PrintOutput(false, output);
    }

    /** Shows only movies that match the given filters **/
    public void filter() {
        insertMoviesInList();
        ArrayList<MoviesInput> moviesList = Database.getDatabase().getCurrentMoviesList();
        boolean isDurationEmpty = action.getFilters().getSort().getDuration().isEmpty();
        boolean isRatingEmpty = action.getFilters().getSort().getRating().isEmpty();

            if (!isDurationEmpty) {
                for (int i = 0; i < moviesList.size(); i++) {
                    for (int j = i + 1; j < moviesList.size(); j++) {
                        if (moviesList.get(i).getDuration() == moviesList.get(j).getDuration()
                                && !isRatingEmpty) {
                            filterByRating(moviesList, i, j);
                        } else if (action.getFilters().getSort().getDuration()
                                .equals("decreasing")) {
                            if (moviesList.get(i).getDuration() < moviesList.get(j).getDuration()) {
                                Collections.swap(moviesList, i, j);
                            }
                        } else {
                            if (moviesList.get(i).getDuration() > moviesList.get(j).getDuration()) {
                                Collections.swap(moviesList, i, j);
                            }
                        }
                    }
                }
            } else {
                for (int i = 0; i < moviesList.size(); i++) {
                    for (int j = i + 1; j < moviesList.size(); j++) {
                        filterByRating(moviesList, i, j);
                    }
                }
            }

        if (!Database.getDatabase().getCurrentPage().equals("movies")) {
            isError = true;
        } else if (!Database.getDatabase().getCurrentMoviesList().isEmpty()) {
                if (!action.getFilters().getContains().getActors().isEmpty()) {
                    filterByActor(moviesList);
                }
                if (!action.getFilters().getContains().getGenre().isEmpty()) {
                    filterByGenre(moviesList);
                }

        }
        new PrintOutput(isError, output);
    }


    private void filterByRating(final ArrayList<MoviesInput> moviesList, final int i, final int j) {
        if (action.getFilters().getSort().getRating().equals("decreasing")) {
            if (moviesList.get(i).getRating() < moviesList.get(j).getRating()) {
                Collections.swap(moviesList, i, j);
            }
        } else {
            if (moviesList.get(i).getRating() > moviesList.get(j).getRating()) {
                Collections.swap(moviesList, i, j);
            }
        }
    }

    private void filterByActor(final ArrayList<MoviesInput> moviesList) {
        for (int i = 0; i < moviesList.size(); i++) {
            for (int j = 0; j < action.getFilters().getContains().getActors().size(); j++) {
                boolean isActor = false;
                for (String actor: moviesList.get(i).getActors()) {
                    isActor = actor.equals(action.getFilters().getContains().getActors().get(j));
                    if (isActor) {
                        break;
                    }
                }
                if (!isActor) {
                    moviesList.remove(i);
                    i--;
                }
            }
        }
    }

    private void filterByGenre(final ArrayList<MoviesInput> moviesList) {
        for (int i = 0; i < moviesList.size(); i++) {
            for (int j = 0; j < action.getFilters().getContains().getGenre().size(); j++) {
                boolean isGenre = false;
                for (String genre: moviesList.get(i).getGenres()) {
                    isGenre = genre.equals(action.getFilters().getContains().getGenre().get(j));
                    if (isGenre) {
                        break;
                    }
                }
                if (!isGenre) {
                    moviesList.remove(i);
                    i--;
                }
            }
        }
    }


}
