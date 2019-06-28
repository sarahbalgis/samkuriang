package bodoamat.samkuriang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bodoamat.samkuriang.R;
import bodoamat.samkuriang.adapter.ItemListHistoryAdapter;
import bodoamat.samkuriang.api.ConfigUtils;
import bodoamat.samkuriang.api.Service;
import bodoamat.samkuriang.models.HistoryList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryFragment extends Fragment {

    private RecyclerView recyclerViewHistory;
    private RecyclerView.Adapter adapter;
    Toolbar toolbar;


    public HistoryFragment() {
//        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);



    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity();


        toolbar = view.findViewById(R.id.setToolbar);
        toolbar.setTitle("History ");


        recyclerViewHistory = view.findViewById(R.id.recycler_view);
        recyclerViewHistory.setHasFixedSize(true);
        recyclerViewHistory.setLayoutManager(new LinearLayoutManager(getActivity()));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConfigUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);

        Call<HistoryList> call = service.getHistory();

        call.enqueue(new Callback<HistoryList>() {
            @Override
            public void onResponse(Call<HistoryList> call, Response<HistoryList> response) {
                adapter = new ItemListHistoryAdapter(response.body().getHistoryList(), getActivity());
                recyclerViewHistory.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<HistoryList> call, Throwable t) {

            }

        });


    }
}

