package com.labbaiik.app.view.fragments.video;

import static com.labbaiik.app.adapter.ShowVideoListAdapter.SPAN_COUNT_ONE;
import static com.labbaiik.app.adapter.ShowVideoListAdapter.SPAN_COUNT_TWO;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.labbaiik.app.R;
import com.labbaiik.app.adapter.ShowVideoListAdapter;
import com.labbaiik.app.databinding.FragmentVideoListBinding;

import java.util.ArrayList;
import java.util.List;


public class VideoListFragment extends Fragment {
    FragmentVideoListBinding binding;
    private ShowVideoListAdapter showVideoListAdapter;
    private GridLayoutManager gridLayoutManager;
    private List<String> items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_video_list, container, false);

       // binding.btnBack.setOnClickListener(l -> getActivity().onBackPressed());


        items = new ArrayList<>();
        items.add("আপনি বাবা হলে এই বয়ানটি আপনার জন্য");
        items.add("আপনি বাবা হলে এই বয়ানটি আপনার জন্য");
        items.add("আপনি বাবা হলে এই বয়ানটি আপনার জন্য");
        items.add("আপনি বাবা হলে এই বয়ানটি আপনার জন্য");
        items.add("আপনি বাবা হলে এই বয়ানটি আপনার জন্য");
        items.add("আপনি বাবা হলে এই বয়ানটি আপনার জন্য");
        items.add("আপনি বাবা হলে এই বয়ানটি আপনার জন্য");
        items.add("আপনি বাবা হলে এই বয়ানটি আপনার জন্য");

        gridLayoutManager = new GridLayoutManager(getContext(), SPAN_COUNT_ONE);
        showVideoListAdapter = new ShowVideoListAdapter(items, gridLayoutManager);
        binding.videoList.setAdapter(showVideoListAdapter);
        binding.videoList.setLayoutManager(gridLayoutManager);

        binding.btnGrid.setOnClickListener(l->{
            binding.btnGrid.setImageResource(R.drawable.grid_view_active);
            binding.btnList.setImageResource(R.drawable.list_view);
            gridLayoutManager.setSpanCount(SPAN_COUNT_TWO);
            showVideoListAdapter.notifyItemRangeChanged(0, showVideoListAdapter.getItemCount());
        });

        binding.btnList.setOnClickListener(l->{
            binding.btnGrid.setImageResource(R.drawable.grid_view);
            binding.btnList.setImageResource(R.drawable.list_view_active);
            gridLayoutManager.setSpanCount(SPAN_COUNT_ONE);
            showVideoListAdapter.notifyItemRangeChanged(0, showVideoListAdapter.getItemCount());
        });

        return binding.getRoot();
    }


}