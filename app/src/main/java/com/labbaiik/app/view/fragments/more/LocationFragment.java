package com.labbaiik.app.view.fragments.more;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.labbaiik.app.R;
import com.labbaiik.app.databinding.FragmentLocationBinding;
import com.labbaiik.app.utill.GPSTracker;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class LocationFragment extends Fragment {
    FragmentLocationBinding binding;
    private static final int REQUEST_CODE = 101;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_location, container, false);

        binding.btnBack.setOnClickListener(l -> getActivity().onBackPressed());
        fetchLocation();
        return binding.getRoot();
    }

    private void fetchLocation(){
        if (ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        }else {
            Log.d("Tanvir", "tejsf");

            GPSTracker gpsTracker = new GPSTracker(getContext());


            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocation(gpsTracker.getLatitude(), gpsTracker.getLongitude(), 1);
                if (addresses.size() != 0){
                    Address obj = addresses.get(0);
                    String add = obj.getAddressLine(0);
                   // add = add + "\n" + obj.getCountryName();
                  //  add = add + "\n" + obj.getCountryCode();
                  //  add = add + "\n" + obj.getAdminArea();
                  //  add = add + "\n" + obj.getPostalCode();
                  //  add = add + "\n" + obj.getSubAdminArea();
                 //   add = add + "\n" + obj.getLocality();
                 //   add = add + "\n" + obj.getSubThoroughfare();

                    Log.d("Tanvir", "Address" + add);
                    // Toast.makeText(this, "Address=>" + add,
                    // Toast.LENGTH_SHORT).show();

                    binding.currentLocation.setText("Current location: " + add);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("Tanvir", "32132132");
                fetchLocation();

                //getLocationUpdates();
            }
        }
    }
}