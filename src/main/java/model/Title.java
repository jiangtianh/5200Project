package model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Title {

    private String titleId;
    private String titleType;
    private String primaryTitle;
    private String originalTitle;
    private boolean isAdult;
    private int startYear;
    private int endYear;
    private int runtimeMinutes;

    public static List<String> allTitleTypes = Arrays.asList("movie", "short", "tvMovie", "tvSeries", "tvEpisode", "tvSpecial", "video", "videoGame");

    public Title(String titleId, String titleType, String primaryTitle, String originalTitle, boolean isAdult, int startYear, int endYear, int runtimeMinutes) {
        this.titleId = titleId;
        this.titleType = titleType;
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.isAdult = isAdult;
        this.startYear = startYear;
        this.endYear = endYear;
        this.runtimeMinutes = runtimeMinutes;
    }
    public Title (String titleId) {
        this.titleId = titleId;
    }

    // Getters and Setters
    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getTitleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    public String getPrimaryTitle() {
        return primaryTitle;
    }

    public void setPrimaryTitle(String primaryTitle) {
        this.primaryTitle = primaryTitle;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public boolean getIsAdult() {
        return isAdult;
    }

    public void setAdult(boolean isAdult) {
        this.isAdult = isAdult;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public int getRuntimeMinutes() {
        return runtimeMinutes;
    }

    public void setRuntimeMinutes(int runtimeMinutes) {
        this.runtimeMinutes = runtimeMinutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Title title = (Title) o;
        return isAdult == title.isAdult &&
                startYear == title.startYear &&
                endYear == title.endYear &&
                runtimeMinutes == title.runtimeMinutes &&
                Objects.equals(titleId, title.titleId) &&
                Objects.equals(titleType, title.titleType) &&
                Objects.equals(primaryTitle, title.primaryTitle) &&
                Objects.equals(originalTitle, title.originalTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titleId, titleType, primaryTitle, originalTitle, isAdult, startYear, endYear, runtimeMinutes);
    }
}