package com.dudupoo.listview.list;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dudupoo.listview.FlowerPOJO;
import com.dudupoo.listview.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class ListFragment extends Fragment
{
    static List<String> listMembers;

    public static HashMap<String, FlowerPOJO> mapMembers = new HashMap<>();

    String mTime = "";

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_list, container, false);


        FlowerPOJO rose = new FlowerPOJO("Rose", "The rose is Red.", R.drawable.rose_image);
        FlowerPOJO jasmine = new FlowerPOJO("Jasmine", "The Jasmine is White.", R.drawable.jasmine);
        mapMembers.put("Rose", rose);
        mapMembers.put("Jasmine", jasmine);
        listMembers = new ArrayList<>(mapMembers.keySet());
        ArrayList<FlowerPOJO> listValues = new ArrayList<>(mapMembers.values());

        recyclerView= (RecyclerView) v.findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ListRecyclerAdapter(listValues);
        recyclerView.setAdapter(adapter);





        if (savedInstanceState != null) {
            // Restore last state
            mTime = savedInstanceState.getString("time_key");
        } else {
            mTime = "" + Calendar.getInstance().getTimeInMillis();
        }


        return v;
    }//end of onCreateView()

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString("mTime", mTime);
    }
}
