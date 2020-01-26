package org.lu.hypervisor.android.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.lu.hypervisor.android.R;
import org.lu.hypervisor.android.api.ApiClient;
import org.lu.hypervisor.android.api.model.Misbehavior;
import org.lu.hypervisor.android.api.model.Photo;
import org.lu.hypervisor.android.persist.AppDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ExecutorService backgroundThread = Executors.newSingleThreadExecutor();
    private View root;
    private View progressBar;
    private LinearLayout tray;
    private SwipeRefreshLayout swipeRefresh ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        progressBar = root.findViewById(R.id.misbehavior_progressBarFrame);
        tray = root.findViewById(R.id.misbehavior_tray);
        swipeRefresh = root.findViewById(R.id.misbehavior_refresh);
        swipeRefresh.setOnRefreshListener(()->{
            backgroundThread.submit(this::loadData);
            if(swipeRefresh.isRefreshing()) swipeRefresh.setRefreshing(false);
        });
        backgroundThread.submit(this::loadData);
        return root;
    }
    private void loadData(){
        String token = AppDatabase.getDb(getContext()).userDao().getALl().get(0).token;
        try {
            List<Misbehavior> misbehaviorList = ApiClient.Misbehavior.getAll(token);
            HashMap<Misbehavior, Photo> photoHashMap = new HashMap<>();
            for(Misbehavior m : misbehaviorList){
                photoHashMap.put(m,ApiClient.Subject.getPhotoBySujectId(token,m.getSubject().getId()));
            }
            getActivity().runOnUiThread(()->{
                tray.removeAllViews();
                for(Misbehavior m : misbehaviorList){
                    tray.addView(new MisbehaviorView(getContext(),m,photoHashMap.get(m)));
                }
                progressBar.setVisibility(View.GONE);
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}