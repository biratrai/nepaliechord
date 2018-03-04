package com.example.gooner10.nepaliechord.mainsong;

import com.example.gooner10.nepaliechord.model.Song;

import java.util.List;

/**
 * Contract for the mainActivity
 */

public interface MainSongContract {
    interface MainSongView {
        void displaySong(List<Song> songList);
    }

    interface MainSongPresenter {
        void fetchSong();
    }
}
