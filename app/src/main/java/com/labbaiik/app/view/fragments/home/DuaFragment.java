package com.labbaiik.app.view.fragments.home;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.labbaiik.app.R;
import com.labbaiik.app.adapter.ShowDuaListAdapter;
import com.labbaiik.app.adapter.ShowDuaTypeAdapter;
import com.labbaiik.app.databinding.FragmentDuaBinding;
import com.labbaiik.app.model.duaList.Dua;
import com.labbaiik.app.view.MainActivity;
import com.labbaiik.app.viewModel.ViewModel;

import java.util.ArrayList;
import java.util.List;


public class DuaFragment extends Fragment {
    FragmentDuaBinding binding;
    private ShowDuaTypeAdapter duaTypeAdapter;
    private ShowDuaListAdapter duaListAdapter;
    private List<String> duaArray;
    private ViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dua, container, false);

        binding.btnBack.setOnClickListener(l -> getActivity().onBackPressed());
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);

        duaArray = new ArrayList<>();
        duaArray.add("সেভকৃত দু‘আ");
        duaArray.add("ইমান ও আকাইদ");
        duaArray.add("পোষাক-পরিচ্ছদ");
        duaArray.add("খাবার-দাবার");
        duaArray.add("খাবার-দাবার");
        duaArray.add("খাবার-দাবার");
        duaArray.add("খাবার-দাবার");
        duaArray.add("খাবার-দাবার");

        duaTypeAdapter = new ShowDuaTypeAdapter(new ArrayList<>(), getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.duaType.setLayoutManager(layoutManager);
        binding.duaType.setAdapter(duaTypeAdapter);
        duaTypeAdapter.updateDuaList(duaArray);
        duaTypeAdapter.notifyDataSetChanged();


        duaListAdapter = new ShowDuaListAdapter(new ArrayList<>(), getContext());
        LinearLayoutManager duaLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.duaList.setLayoutManager(duaLayoutManager);
        binding.duaList.setAdapter(duaListAdapter);


        duaListAdapter.setOnClickDuaList(dua -> {
            Log.d("Tanvir >> Dualist >> dua >> ", dua.getArabic());
            final Bundle bundle = new Bundle();
            Gson gson = new Gson();
            String duaString = gson.toJson(dua);
            bundle.putString("dua", duaString);

            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_dua_details, bundle);
        });

        binding.loading.setVisibility(View.VISIBLE);
        viewModel.getDuaList();
        observeDuaList();


        return binding.getRoot();
    }


    private void observeDuaList() {
        viewModel.duaListResponseMutableLiveData.observe(
                getActivity(),
                response -> {
                    if (response.getData() != null) {
                        if (response.getData().get(0).getDua() != null){

                            Log.d("Tanvir >> Dualist >> Size >> ", String.valueOf(response.getData().size()));
                            duaListAdapter.clearAll();;
                            duaListAdapter.updateDuaList(response.getData().get(0).getDua());
                            duaListAdapter.notifyDataSetChanged();
                        }
                        binding.loading.setVisibility(View.GONE);
                        viewModel.duaListResponseMutableLiveData = new MutableLiveData<>();
                    }

                }
        );
        viewModel.duaListResponseLoadError.observe(
                getViewLifecycleOwner(), isError -> {
                    if (isError != null) {
                        if (isError) {
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                            binding.loading.setVisibility(View.GONE);
                        }
                        viewModel.duaListResponseLoadError = new MutableLiveData<>();
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

    @Override
    public void onStart() {
        super.onStart();
        MainActivity.hideBottomNav();
    }

    @Override
    public void onStop() {
        super.onStop();
        MainActivity.showBottomNav();
    }
}