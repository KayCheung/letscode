package com.tuniu.understand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by nuc on 2015/11/14.
 */
public class Album {
    public String getTitle() {
        return title;
    }

    public String title;
    public List<Track> tracks = new ArrayList<>();

    public static class Track {
        public String name;
        public String artist;
        public int rating;
    }

    public static List<Album> tranditional() {
        List<Album> allAlbum = new ArrayList<>();

        List<Album> favs = new ArrayList<>();

        for (Album a : allAlbum) {
            boolean hasFavorite = false;
            for (Track t : a.tracks) {
                if (t.rating >= 4) {
                    hasFavorite = true;
                    break;
                }
            }
            if (hasFavorite) {
                favs.add(a);
            }
        }
        Collections.sort(favs, new Comparator<Album>() {
            @Override
            public int compare(Album o1, Album o2) {
                return o1.title.compareTo(o2.title);
            }
        });
        return favs;
    }

    public static List<Album> streamStyle() {
        List<Album> allAlbum = new ArrayList<>();
        List<Album> favs = allAlbum.stream().
                filter(a -> a.tracks.stream().anyMatch(t -> t.rating >= 4)).
                sorted(Comparator.comparing(a -> a.title)).
                collect(Collectors.toList());
        return favs;
    }

}
