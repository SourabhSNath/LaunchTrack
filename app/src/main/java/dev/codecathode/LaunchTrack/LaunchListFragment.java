package dev.codecathode.LaunchTrack;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dev.codecathode.LaunchTrack.Adapters.LaunchAdapter;
import dev.codecathode.LaunchTrack.ViewModels.MainViewModel;
import io.supercharge.shimmerlayout.ShimmerLayout;

public class LaunchListFragment extends Fragment {
    public static final String DETAIL_TAG = "detailTag";
    private int selectedOption;

    @BindView(R.id.contentList)
    ConstraintLayout contentList;
    @BindView(R.id.shimmerLayout)
    ShimmerLayout shimmerLayout;
    @BindView(R.id.launch_list)
    RecyclerView launchRecyclerView;

    private Unbinder unbinder;
    private LaunchAdapter adapter = new LaunchAdapter();
    private MainViewModel launchListViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_launch_list, container, false);
        setHasOptionsMenu(true);

        unbinder = ButterKnife.bind(this, view);

        launchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        shimmerLayout.startShimmerAnimation();

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        launchListViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MainViewModel.class);


        getViewModelData();

        launchRecyclerView.setAdapter(adapter);

    }


    private void getViewModelData() {

        launchListViewModel.launchPagedList.observe(getViewLifecycleOwner(), launches -> {


            adapter.submitList(launches);

            if (launches != null && !launches.isEmpty()) {
                shimmerLayout.stopShimmerAnimation();
                contentList.removeView(shimmerLayout);
            } else {
                shimmerLayout.stopShimmerAnimation();
                contentList.removeView(shimmerLayout);
            }


            adapter.setClick((pos) -> {
                launchListViewModel.select(Objects.requireNonNull(launches).get(pos));

                (Objects.requireNonNull(getActivity())).getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.launch_container, new LaunchDetailsFragment())
                        .addToBackStack(null)
                        .commit();

            });

        });


    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_launch_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.deleteAll:
                launchListViewModel.refreshLaunch();
                return true;

            case R.id.getConfirmedDate:
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onDestroyView() {
        launchRecyclerView.setAdapter(null);
        unbinder.unbind();
        super.onDestroyView();
    }


}