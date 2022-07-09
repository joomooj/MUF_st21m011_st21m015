package com.example.muf_mayer_zainzinger;

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

import com.example.muf_mayer_zainzinger.data.SensorData;
import com.example.muf_mayer_zainzinger.viewmodel.DataViewModel;

import java.util.ArrayList;
import java.util.List;

public class FeedbackFragment extends Fragment {

    private List<SensorData> accelerationData = new ArrayList<>();
    private DataViewModel accViewModel;
    private TextView feedback;

    private final Observer <SensorData> observer=(acceleration) -> {
        feedback.setText(
                getString(R.string.xLabel) + acceleration.getX() +"\n"+ getString(R.string.yLabel) + acceleration.getY() +"\n"+ getString(R.string.zLabel) + acceleration.getZ()
        );
        accelerationData.add(acceleration);
        if(accelerationData.size()==100) {

            //does not work:
            //accViewModel.insert(accelerationData);
            //accelerationData.clear();

            List<SensorData> buffData = accelerationData;
            accelerationData = new ArrayList<>();
            accViewModel.insert(buffData);
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accViewModel = new ViewModelProvider(
                getActivity(),
                (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())
        ).get(DataViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.feed_fragment, container, false);
        feedback = view.findViewById(R.id.feed);

        view.findViewById(R.id.buttonStart).setOnClickListener(startButton -> {
            accViewModel.dataLiveData.observeForever(observer);
        });

        view.findViewById(R.id.buttonStop).setOnClickListener(stopButton -> {
            accViewModel.dataLiveData.removeObserver(observer);
        });
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
       accViewModel.dataLiveData.removeObserver(observer);
    }
}
