package model;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Genre {

    public static final List<String> validGenres = Arrays.asList(
            "Documentary", "Short", "Animation", "Comedy", "Romance", "Sport", "News", "Drama", "Fantasy", "Horror",
            "Biography", "Music", "War", "Crime", "Western", "Family", "Adventure", "Action", "History", "Mystery",
            "Sci-Fi", "Musical", "Thriller", "Film-Noir", "Talk-Show", "Game-Show", "Reality-TV", "Adult"
    );

    private int genreId;
    private String genreName;

    public Genre(int genreId, String genreName) {
        this.genreId = genreId;
        this.genreName = genreName;
    }
    public Genre(int genreId) {
        this.genreId = genreId;
    }

    public Genre(String genreName) {
        this.genreName = genreName;
    }


    // Getters and Setters
    public int getGenreId() {
        return genreId;
    }
    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }
    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return genreId == genre.genreId && Objects.equals(genreName, genre.genreName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreId, genreName);
    }

}
