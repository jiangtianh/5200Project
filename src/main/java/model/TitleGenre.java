package model;

public class TitleGenre {
    private Title title;
    private Genre genre;

    public TitleGenre(Title title, Genre genre) {
        this.title = title;
        this.genre = genre;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
