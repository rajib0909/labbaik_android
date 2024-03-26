package com.labbaiik.app.view.fragments.video;

import static com.labbaiik.app.adapter.ShowVideoListAdapter.SPAN_COUNT_ONE;
import static com.labbaiik.app.adapter.ShowVideoListAdapter.SPAN_COUNT_TWO;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.labbaiik.app.R;
import com.labbaiik.app.adapter.ShowVideoListAdapter;
import com.labbaiik.app.databinding.FragmentVideoListBinding;
import com.labbaiik.app.model.videoList.Videos;
import com.labbaiik.app.viewModel.ViewModel;

import java.util.ArrayList;
import java.util.List;


public class VideoListFragment extends Fragment {
    FragmentVideoListBinding binding;
    private ShowVideoListAdapter showVideoListAdapter;
    private GridLayoutManager gridLayoutManager;
    private ViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_video_list, container, false);
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
       // binding.btnBack.setOnClickListener(l -> getActivity().onBackPressed());




        gridLayoutManager = new GridLayoutManager(getContext(), SPAN_COUNT_ONE);
        showVideoListAdapter = new ShowVideoListAdapter(new ArrayList<>(), gridLayoutManager);
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

        binding.loading.setVisibility(View.VISIBLE);
        viewModel.getVideoList();
        observeVideoList();

        return binding.getRoot();
    }

    private void observeVideoList() {
        viewModel.videoListResponseMutableLiveData.observe(
                getActivity(),
                response -> {
                    if (response.getData() != null) {
                        if (response.getData().get(0).getVideos() != null){

                            Log.d("Tanvir >> videoList >> Size >> ", String.valueOf(response.getData().get(0).getVideos()));
                            showVideoListAdapter.clearAll();;
                            showVideoListAdapter.updateVideoList(response.getData().get(0).getVideos());
                            showVideoListAdapter.notifyDataSetChanged();
                        }
                        binding.loading.setVisibility(View.GONE);
                        viewModel.videoListResponseMutableLiveData = new MutableLiveData<>();
                    }

                }
        );
        viewModel.videoListResponseLoadError.observe(
                getViewLifecycleOwner(), isError -> {
                    if (isError != null) {
                        if (isError) {
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                            binding.loading.setVisibility(View.GONE);
                        }
                        viewModel.videoListResponseLoadError = new MutableLiveData<>();
                    }
                }
        );

//        viewModel.noInternet.observe(
//                getViewLifecycleOwner(), isError -> {
//                    if (isError != null) {
//                        if (isError) {
//                            Glide.with(getContext()).load(R.drawable.no_internet_1).into(homeBinding.noInternet);
//                            homeBinding.loading.setVisibility(View.GONE);
//                            homeBinding.noInternet.setVisibility(View.VISIBLE);
//                            homeBinding.photoList.setVisibility(View.VISIBLE);
//                        }
//                        viewModel.noInternet = new MutableLiveData<>();
//                    }
//                }
//        );

    }


}