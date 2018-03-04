package com.example.gooner10.nepaliechord.mainsong;

import com.example.gooner10.nepaliechord.model.Song;

import java.util.List;

/**
 * Contract for the mainActivity
 */

public interface MainSongContract {
    interface MainSongView {
        List<Song> displaySong();
    }

    interface MainSongPresenter {
        List<Song> fetchSong();
    }
}
