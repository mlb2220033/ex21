package com.example.ex21;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;
import android.widget.TabHost;
import com.example.adapter.SongAdapter;
import com.example.model.Song;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Song> allSongs;
    private List<Song> loveSongs;
    private SongAdapter songAdapter;
    private ListView lvAll;
    private ListView lvLove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allSongs = new ArrayList<>();
        loveSongs = new ArrayList<>();
        addViews();
        addEvents();
        populateSongs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    private void addEvents() {
        lvAll = findViewById(R.id.lvAll);
        lvLove = findViewById(R.id.lvLove);

        songAdapter = new SongAdapter(this, allSongs);
        lvAll.setAdapter(songAdapter);
        final SongAdapter loveSongAdapter = new SongAdapter(this, loveSongs);
        lvLove.setAdapter(loveSongAdapter);

        //Like button click
        songAdapter.setOnLikeClickListener(new SongAdapter.OnLikeClickListener() {
            @Override
            public void onLikeClick(Song song) {
                if (!loveSongs.contains(song)) {
                    loveSongs.add(song);
                    loveSongAdapter.notifyDataSetChanged();
                }
            }
        });

        //Dislike button click
        songAdapter.setOnDislikeClickListener(new SongAdapter.OnDislikeClickListener() {
            @Override
            public void onDislikeClick(Song song) {
                loveSongs.remove(song);
                loveSongAdapter.notifyDataSetChanged();
            }
        });

        //Dislike button click for lvLove
        loveSongAdapter.setOnDislikeClickListener(new SongAdapter.OnDislikeClickListener() {
            @Override
            public void onDislikeClick(Song song) {
                // Remove song from loveSongs list
                loveSongs.remove(song);
                loveSongAdapter.notifyDataSetChanged();
            }
        });
    }

    private void addViews() {
        TabHost tabHost = findViewById(R.id.mytab);
        tabHost.setup();

        TabHost.TabSpec allTabSpec = tabHost.newTabSpec("All");
        allTabSpec.setIndicator("All");
        allTabSpec.setContent(R.id.All);
        tabHost.addTab(allTabSpec);

        TabHost.TabSpec loveTabSpec = tabHost.newTabSpec("Love");
        loveTabSpec.setIndicator("Love");
        loveTabSpec.setContent(R.id.Love);
        tabHost.addTab(loveTabSpec);
    }

    private void populateSongs() {
        allSongs.add(new Song("53101", "Ải Chi Lăng", "Lưu Hữu Phước - Mai Văn Bộ"));
        allSongs.add(new Song("50070", "Ai Cho Em Tình Yêu", "Ngọc Lễ"));
        allSongs.add(new Song("50012", "Ai Cho Em Tình Yêu", "Trúc Phương"));
        allSongs.add(new Song("52859", "Ai Đi Ngoài Sương Gió", "Nguyễn Hữu Thiết"));
        allSongs.add(new Song("50038", "Ai Đưa Em Về", "Nguyễn Ánh Chín"));
        allSongs.add(new Song("50210", "Ai Lên Xứ Hoa Đào", "Hoàng Nguyên"));
    }
}