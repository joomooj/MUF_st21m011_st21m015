package com.example.muf_mayer_zainzinger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.muf_mayer_zainzinger.viewmodel.DataViewModel;


public class MonitoringFragment extends Fragment {


    protected RecyclerView mRecyclerView;

    // adapted source: https://github.com/android/views-widgets-samples/blob/main/RecyclerView/Application/src/main/java/com/example/android/recyclerview/RecyclerViewFragment.java

    //protected RecyclerView.LayoutManager mLayoutManager;
    //private enum LayoutManagerType {LINEAR_LAYOUT_MANAGER}
    //protected LayoutManagerType mCurrentLayoutManagerType;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.mon_fragment,container,false);

        View rootView = inflater.inflate(R.layout.mon_fragment, container, false);

        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(mLayoutManager);

        DataViewModel dataViewModel = new ViewModelProvider(getActivity(),
                (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(DataViewModel.class);

        dataViewModel.getAccLiveData().observe(getViewLifecycleOwner(),(data) -> {mRecyclerView.setAdapter(new MyAdapter(data));});

        return rootView;
    }
}
