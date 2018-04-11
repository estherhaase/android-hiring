package at.allaboutapps.a3hiring.api.models;

import java.util.Comparator;

public class SortByValue implements Comparator<Club> {

    @Override
    public int compare(Club club, Club t1) {
        return (club.getValue() - t1.getValue());
    }
}
