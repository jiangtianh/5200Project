package dal;

import model.Title;

public class TitleGenreDao {
    protected ConnectionManager connectionManager;

    private static TitleGenreDao instance = null;
    protected TitleGenreDao() {
        connectionManager = new ConnectionManager();
    }
    public static TitleGenreDao getInstance() {
        if (instance == null) {
            instance = new TitleGenreDao();
        }
        return instance;
    }



}
