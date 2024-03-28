package actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import database.Database;
import input.MoviesInput;
import input.UserInput;

import java.text.DecimalFormat;

public final class PrintOutput {

    public PrintOutput(final boolean isError, final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode info = output.addObject();
        if (isError) {
           info.put("error", "Error");
           ArrayNode currentMovies = info.putArray("currentMoviesList");
           info.put("currentUser", (JsonNode) null);
        } else {
            info.put("error", (JsonNode) null);
            ArrayNode currentMovies = info.putArray("currentMoviesList");
            if (Database.getDatabase().getCurrentUser() == null) {
                info.put("currentUser", (JsonNode) null);
            }  else {
                ObjectNode userInfo = mapper.createObjectNode();
                this.writeUser(Database.getDatabase().getCurrentUser(), userInfo, mapper);
                info.put("currentUser", userInfo);
            }

            if (Database.getDatabase().getCurrentMoviesList() != null) {
                for (MoviesInput movie: Database.getDatabase().getCurrentMoviesList()) {
                    ObjectNode movieInfo = mapper.createObjectNode();
                    this.writeMovie(movie, movieInfo, mapper);
                    currentMovies.add(movieInfo);
                }
            }
        }

    }

    /** Method to write the user in an ObjectNode **/

    public void writeUser(final UserInput user, final ObjectNode output,
                          final ObjectMapper mapper) {
       ObjectNode credentials = mapper.createObjectNode();
       credentials.put("name", user.getCredentials().getName());
       credentials.put("password", user.getCredentials().getPassword());
       credentials.put("accountType", user.getCredentials().getAccountType());
       credentials.put("country", user.getCredentials().getCountry());
       credentials.put("balance", Integer.toString(user.getCredentials().getBalance()));

       output.put("credentials", credentials);
       output.put("tokensCount", user.getTokensCount());
       output.put("numFreePremiumMovies", user.getNumFreePremiumMovies());

       ArrayNode purchasedMovies = output.putArray("purchasedMovies");
       for (MoviesInput movie: Database.getDatabase().getCurrentUser().getPurchasedMovies()) {
           ObjectNode purchasedMovie = mapper.createObjectNode();
           writeMovie(movie, purchasedMovie, mapper);
           purchasedMovies.add(purchasedMovie);
       }

       ArrayNode watchedMovies = output.putArray("watchedMovies");
        for (MoviesInput movie: Database.getDatabase().getCurrentUser().getWatchedMovies()) {
            ObjectNode watchedMovie = mapper.createObjectNode();
            writeMovie(movie, watchedMovie, mapper);
            watchedMovies.add(watchedMovie);
        }

       ArrayNode likedMovies = output.putArray("likedMovies");
        for (MoviesInput movie: Database.getDatabase().getCurrentUser().getLikedMovies()) {
            ObjectNode likedMovie = mapper.createObjectNode();
            writeMovie(movie, likedMovie, mapper);
            likedMovies.add(likedMovie);
        }

       ArrayNode ratedMovies = output.putArray("ratedMovies");
        for (MoviesInput movie: Database.getDatabase().getCurrentUser().getRatedMovies()) {
            ObjectNode ratedMovie = mapper.createObjectNode();
            writeMovie(movie, ratedMovie, mapper);
            ratedMovies.add(ratedMovie);
        }
    }

    /** Method to write a movie in an Object Node **/
    public void writeMovie(final MoviesInput movie, final ObjectNode output,
                           final ObjectMapper mapper) {
        final DecimalFormat df = new DecimalFormat("0.00");
        output.put("name", movie.getName());
        output.put("year", movie.getYear());
        output.put("duration", movie.getDuration());

        ArrayNode genres = output.putArray("genres");
        ArrayNode actors = output.putArray("actors");
        ArrayNode countriesBanned = output.putArray("countriesBanned");

        for (String genre: movie.getGenres()) {
            genres.add(genre);
        }
        for (String actor: movie.getActors()) {
            actors.add(actor);
        }
        for (String country: movie.getCountriesBanned()) {
            countriesBanned.add(country);
        }
        output.put("numLikes", movie.getNumLikes());
        output.put("rating", movie.getRating());
        output.put("numRatings", movie.getNumRatings());
    }

}
