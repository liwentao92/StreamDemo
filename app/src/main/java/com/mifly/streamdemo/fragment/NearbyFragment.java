package com.mifly.streamdemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mifly.streamdemo.R;
import com.mifly.streamdemo.adapter.RecyclerViewAdapter;
import com.mifly.streamdemo.adapter.RecyclerViewAdapter1;


/**
 * A simple {@link Fragment} subclass.
 */
public class NearbyFragment extends Fragment {


    public NearbyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearby, container, false);
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.rl1);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mRecyclerView.getContext(),3));
        mRecyclerView.setAdapter(new RecyclerViewAdapter1(getActivity()));
        // Inflate the layout for this fragment
        return view;
    }

}
