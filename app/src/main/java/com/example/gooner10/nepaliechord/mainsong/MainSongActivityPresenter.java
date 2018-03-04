package com.example.gooner10.nepaliechord.mainsong;

import com.example.gooner10.nepaliechord.mainsong.MainSongContract.MainSongPresenter;
import com.example.gooner10.nepaliechord.model.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Presenter for {@link MainActivity}
 */

public class MainSongActivityPresenter implements MainSongPresenter {
    private MainSongContract.MainSongView songView;

    public MainSongActivityPresenter(MainSongContract.MainSongView songView) {
        this.songView = songView;
    }

    @Override
    public void fetchSong() {

        List<Song> songList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Song song = new Song(i + ") Sugam Pokhrel", i + ": Mero Sansar", true);
            songList.add(song);
        }
        songView.displaySong(songList);
    }
}
