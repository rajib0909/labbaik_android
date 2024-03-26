package com.labbaiik.app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.labbaiik.app.R;
import com.labbaiik.app.model.duaList.Dua;
import com.labbaiik.app.model.videoList.Videos;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;

public class ShowVideoListAdapter extends RecyclerView.Adapter<ShowVideoListAdapter.ItemViewHolder> {
    public static final int SPAN_COUNT_ONE = 1;
    public static final int SPAN_COUNT_TWO = 2;

    private static final int VIEW_TYPE_SMALL = 1;
    private static final int VIEW_TYPE_BIG = 2;

    private List<Videos> mItems;
    private GridLayoutManager mLayoutManager;

    public ShowVideoListAdapter(List<Videos> items, GridLayoutManager layoutManager) {
        mItems = items;
        mLayoutManager = layoutManager;
    }


    public void clearAll() {
        this.mItems.clear();
    }


    public void updateVideoList(List<Videos> allResultList) {
        this.mItems.addAll(allResultList);
    }



    @Override
    public int getItemViewType(int position) {
        int spanCount = mLayoutManager.getSpanCount();
        if (spanCount == SPAN_COUNT_ONE) {
            return VIEW_TYPE_BIG;
        } else {
            return VIEW_TYPE_SMALL;
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_BIG) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_grid, parent, false);
        }
        return new ItemViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Videos item = mItems.get(position);
        holder.title.setText(item.getTitle());
        // Split the embed link by "/"
        String[] parts = item.getLink().split("/");

        // Get the last part of the split, which contains the video ID
        String videoId = parts[parts.length - 1];
        holder.youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.cueVideo(videoId, 0);
               // youTubePlayer.pause();
            }
        });
        //holder.youTubePlayerView.setId();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        YouTubePlayerView youTubePlayerView;

        ItemViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == VIEW_TYPE_BIG) {
                title = (TextView) itemView.findViewById(R.id.duaTitle);
                youTubePlayerView = (YouTubePlayerView) itemView.findViewById(R.id.youtube_player_view);
            } else {
                title = (TextView) itemView.findViewById(R.id.duaTitle);
                youTubePlayerView = (YouTubePlayerView) itemView.findViewById(R.id.youtube_player_view);
            }
        }
    }
}