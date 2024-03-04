package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ex21.R;
import com.example.model.Song;

import java.util.List;
public class SongAdapter extends ArrayAdapter<Song> {
    private Context context;
    private List<Song> songs;
    private OnLikeClickListener likeClickListener;
    private OnDislikeClickListener dislikeClickListener;

    public interface OnLikeClickListener {
        void onLikeClick(Song song);
    }

    public interface OnDislikeClickListener {
        void onDislikeClick(Song song);
    }

    public SongAdapter(Context context, List<Song> songs) {
        super(context, 0, songs);
        this.context = context;
        this.songs = songs;
    }

    public void setOnLikeClickListener(OnLikeClickListener listener) {
        this.likeClickListener = listener;
    }

    public void setOnDislikeClickListener(OnDislikeClickListener listener) {
        this.dislikeClickListener = listener;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.layout1, parent, false);
        }

        final Song currentSong = getItem(position);

        TextView idTextView = listItemView.findViewById(R.id.txtId);
        idTextView.setText(currentSong.getId());

        TextView nameTextView = listItemView.findViewById(R.id.txtViewName);
        nameTextView.setText(currentSong.getName());

        TextView artistTextView = listItemView.findViewById(R.id.txtViewArtist);
        artistTextView.setText(currentSong.getArtist());

        ImageButton likeButton = listItemView.findViewById(R.id.imgbtnLike);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (likeClickListener != null) {
                    likeClickListener.onLikeClick(currentSong);
                }
            }
        });

        ImageButton dislikeButton = listItemView.findViewById(R.id.imgbtnDislike);
        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dislikeClickListener != null) {
                    dislikeClickListener.onDislikeClick(currentSong);
                }
            }
        });

        return listItemView;
    }
}