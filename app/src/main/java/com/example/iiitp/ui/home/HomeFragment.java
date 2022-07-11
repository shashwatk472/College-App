package com.example.iiitp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.iiitp.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.textView4);

        ImageSlider imageSlider=root.findViewById(R.id.slider);
        List<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel("https://www.iiitp.ac.in/sites/default/files/styles/banner_image/public/2019-08/new-iitpune-banner.jpg?itok=0kETVYD3"));
        slideModels.add(new SlideModel("https://www.iiitp.ac.in/sites/default/files/styles/banner_image/public/2019-07/slider-11_1.jpg?itok=KSE5CdHz"));
        slideModels.add(new SlideModel("https://www.iiitp.ac.in/sites/default/files/styles/banner_image/public/2020-09/slider-24_1.jpg?itok=YrbZJkLA"));
        imageSlider.setImageList(slideModels,true);


        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}