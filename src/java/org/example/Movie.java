package org.example;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Movie {

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Rated")
    private String viewerAgeRating; //G, PG, PG-13 etc

    @JsonProperty("imdbRating")
    private double criticalRating; //A rating score such as 5/10 or something similar

    @JsonProperty("Genre")
    private String tags;

    public Movie() {}

    public String getTitle() {
        return title;
    }
    public String getViewerAgeRating() {
        return viewerAgeRating;
    }
    public double getCriticalRating() {
        return criticalRating;
    }
    public String getTags() {
        return tags;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setViewerAgeRating(String viewerAgeRating) {
        this.viewerAgeRating = viewerAgeRating;
    }

    public void setCriticalRating(double criticalRating) {
        this.criticalRating = criticalRating;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
