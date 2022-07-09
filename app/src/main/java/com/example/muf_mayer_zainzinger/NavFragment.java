package com.example.muf_mayer_zainzinger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class NavFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nav_fragment,container,false);

        view.findViewById(R.id.feed).setOnClickListener(v -> {
            androidx.navigation.Navigation.findNavController(view).navigate(R.id.action_navFragment_to_feedbackFragment2);
        });

        view.findViewById(R.id.mon).setOnClickListener(v -> {
            androidx.navigation.Navigation.findNavController(view).navigate(R.id.action_navFragment_to_monitoringFragment);
        });
        return view;
    }
}
