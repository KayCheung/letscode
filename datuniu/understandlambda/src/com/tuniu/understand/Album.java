package com.tuniu.understand;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by nuc on 2015/11/14.
 */
public class Album {
    public String title;
    public List<Track> tracks = new ArrayList<>();

    public static class Track {
        public String songName;
        public String artist;
        public int rating;
    }

    //每一个歌星，不同评分的 各个歌曲（多层的map）Map<artist, Map<rating, List<Track>>>
    public static void multiLevelMap_traditional(List<Album> allAlbum) {
        Map<String, Map<Integer, List<Track>>> map = new HashMap<>();
        for (Album a : allAlbum) {
            for (Track t : a.tracks) {
                String artist = t.artist;
                if (!map.containsKey(artist)) {
                    map.put(artist, new HashMap<>());
                }
                Map<Integer, List<Track>> rating2List = map.get(artist);

                int rating = t.rating;
                if (!rating2List.containsKey(rating)) {
                    rating2List.put(rating, new ArrayList<>());
                }
                rating2List.get(rating).add(t);
            }
        }
    }

    //每一个歌星，不同评分的 各个歌曲（多层的map）Map<artist, Map<rating, List<Track>>>
    public static void multiLevelMap_streamstyle(List<Album> allAlbum) {
        Map<String, Map<Integer, List<Track>>> map =
                allAlbum.stream().flatMap(a -> a.tracks.stream()).
                        collect(Collectors.groupingBy(t -> t.artist, Collectors.groupingBy(t -> t.rating)));
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


        List<Track> tracks = new ArrayList<>();

        Map<String, List<Track>> favsByArtist =
                tracks.stream().filter(t -> t.rating >= 4).
                        collect(Collectors.groupingBy(t -> t.artist));
        return favs;
    }

    public static void flat() {
        List<Album> allAlbum = new ArrayList<>();
        Stream<Track> trackStream = allAlbum.stream().
                flatMap(a -> a.tracks.parallelStream());
    }

    public static void main(String[] args) {
        int[] rst = IntStream.range(0, 5)
                .parallel()
                .map(x -> {System.out.println(Thread.currentThread().getName());return x * 2;}).toArray();

        System.out.println(Arrays.toString(rst));
    }

}
