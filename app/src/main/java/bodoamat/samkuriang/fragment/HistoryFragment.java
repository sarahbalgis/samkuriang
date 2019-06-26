package bodoamat.samkuriang.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bodoamat.samkuriang.R;
import bodoamat.samkuriang.adapter.RecyclerAdapter;

public class HistoryFragment extends Fragment {

    //deklarasi variabel reyclerview
    RecyclerView recyclerView;
//
//    public HistoryFragment() {
//        // Required empty public constructor
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity();


        //        recyclerView= (RecyclerView) container.findViewById(R.id.recycler_view);
        //menampilkan reyclerview yang ada pada file layout dengan id reycler view

        RecyclerAdapter adapter=new RecyclerAdapter(this);
        //membuat adapter baru untuk reyclerview
        recyclerView.setAdapter(adapter);
        //menset nilai dari adapter
        recyclerView.setHasFixedSize(true);
        //menset setukuran
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //menset layoutmanager dan menampilkan daftar/list
        //dalam bentuk linearlayoutmanager pada class saat ini
        }
    }



