package dev.codecathode.LaunchTrack;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.card.MaterialCardView;
import com.jaredrummler.cyanea.app.CyaneaFragment;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dev.codecathode.LaunchTrack.Adapters.ItemViewHolder;
import dev.codecathode.LaunchTrack.Adapters.PayloadAdapter;
import dev.codecathode.LaunchTrack.Adapters.StringAdapter;
import dev.codecathode.LaunchTrack.Database.Model.Agency;
import dev.codecathode.LaunchTrack.Database.Model.Launch;
import dev.codecathode.LaunchTrack.Database.Model.Lsp;
import dev.codecathode.LaunchTrack.Database.Model.Mission;
import dev.codecathode.LaunchTrack.Database.Model.Pad;
import dev.codecathode.LaunchTrack.Database.Model.Payload;
import dev.codecathode.LaunchTrack.Database.Model.Rocket;
import dev.codecathode.LaunchTrack.Database.Network.NetworkState;
import dev.codecathode.LaunchTrack.ViewModels.MainViewModel;


public class LaunchDetailsFragment extends CyaneaFragment {

    //Auto generated with ButterKnife and plugin
    @BindView(R.id.launchName)
    TextView launchName;
    @BindView(R.id.launchSubTitle)
    TextView launchSubTitle;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.missionDetail)
    TextView missionDetail;
    @BindView(R.id.windowStart)
    TextView windowStart;
    @BindView(R.id.windowEnd)
    TextView windowEnd;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.locationText)
    TextView locationText;
    @BindView(R.id.countryCode)
    TextView countryCode;
    @BindView(R.id.countryCodeText)
    TextView countryCodeText;
    @BindView(R.id.locationInfo)
    TextView locationInfo;
    @BindView(R.id.padInfoURL)
    TextView padInfoURL;
    @BindView(R.id.wikiInfo)
    TextView wikiInfo;
    @BindView(R.id.wikiInfoURL)
    TextView wikiInfoURL;
    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.padLocation)
    TextView padLocation;
    @BindView(R.id.mapCard)
    MaterialCardView mapCard;
    @BindView(R.id.rocketHead)
    TextView rocketHead;
    @BindView(R.id.rocketName)
    TextView rocketName;
    @BindView(R.id.config)
    TextView config;
    @BindView(R.id.configurationRocket)
    TextView configurationRocket;
    @BindView(R.id.fam)
    TextView fam;
    @BindView(R.id.familyName)
    TextView familyName;
    @BindView(R.id.wikiR)
    TextView wikiR;
    @BindView(R.id.wikiRocket)
    TextView wikiRocket;
    @BindView(R.id.rocketAge)
    TextView rocketAgency;
    @BindView(R.id.agencyName)
    TextView agencyName;
    @BindView(R.id.abbrev)
    TextView abbrev;
    @BindView(R.id.abbrevRocket)
    TextView abbrevRocket;
    @BindView(R.id.wikiURA)
    TextView wikiURA;
    @BindView(R.id.wikiRAge)
    TextView wikiRocketAgency;
    @BindView(R.id.infoURA)
    TextView infoURA;
    @BindView(R.id.infoURLsRAge)
    RecyclerView infoURLsRAge;
    @BindView(R.id.payload)
    TextView payload;
    @BindView(R.id.payloadView)
    RecyclerView payloadView;
    @BindView(R.id.detailConstraint)
    NestedScrollView detailConstraint;
    @BindView(R.id.line4)
    View line4;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.typeNameMission)
    TextView typeNameMission;
    @BindView(R.id.vid)
    TextView vid;
    @BindView(R.id.videoLaunchURL)
    TextView videoLaunchURL;
    @BindView(R.id.wikiLaunchM)
    TextView wikiLaunchM;
    @BindView(R.id.wikiURLLaunch)
    TextView wikiURLLaunch;
    @BindView(R.id.lsp)
    TextView lsp;
    @BindView(R.id.launchServiceProvider)
    TextView launchServiceProvider;
    @BindView(R.id.lspMainHead)
    TextView lspMainHead;
    @BindView(R.id.lspName)
    TextView lspName;
    @BindView(R.id.lspLine)
    View lspLine;
    @BindView(R.id.abbrevLsp)
    TextView abbrevLsp;
    @BindView(R.id.wikiLsp)
    TextView wikiLsp;
    @BindView(R.id.wikiLspURL)
    TextView wikiLspURl;
    @BindView(R.id.infoLsphead)
    TextView infoLsphead;
    @BindView(R.id.infoURLLsp)
    RecyclerView infoURLLsp;
    @BindView(R.id.year)
    TextView year;
    @BindView(R.id.URLCheck)
    TextView URLCheck;

    @BindView(R.id.progressResult)
    TextView progressResult;
    @BindView(R.id.progress_circular)
    ProgressBar progressCircular;

    private Unbinder unbinder;

    private GoogleMap mGoogleMap;
    private LatLng tag;
    private String mapMarkerTitle;


    private PayloadAdapter adapter = new PayloadAdapter();
    private List<Payload> payloads;

    private StringAdapter stringAdapter;
    private List<String> lspUrls;
    private List<String> rocketInfo;

    private MainViewModel detailViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.launch_details_fragment, container, false);

        unbinder = ButterKnife.bind(this, view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        //Makes the layout measure for the recyclerView too, since the recyclerView is in NestedScrollView
        layoutManager.isAutoMeasureEnabled();

        //This would make all Items of RecyclerView appear together, also recyclerView wouldn't be scrollable
        //useful here because it should be part of the nestedScrollView and not be as a separate scrollable view
        payloadView.setNestedScrollingEnabled(false);

        payloadView.setLayoutManager(layoutManager);


        RecyclerView.LayoutManager lspLayoutManager = new LinearLayoutManager(getContext());
        infoURLLsp.setNestedScrollingEnabled(false);
        infoURLLsp.setLayoutManager(lspLayoutManager);


        RecyclerView.LayoutManager rocketLspLayoutManager = new LinearLayoutManager(getContext());
        infoURLsRAge.setNestedScrollingEnabled(false);
        infoURLsRAge.setLayoutManager(rocketLspLayoutManager);


        setHasOptionsMenu(true);


        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.setClickable(false);


        mapView.getMapAsync(googleMap -> {
            mGoogleMap = googleMap;
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(tag));

            if (mapMarkerTitle != null) {
                mGoogleMap.addMarker(new MarkerOptions().position(tag));

            }
            mGoogleMap.getUiSettings().setAllGesturesEnabled(false);
            mGoogleMap.getUiSettings().setMapToolbarEnabled(false);
            mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            mGoogleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(Objects.requireNonNull(getContext()), R.raw.map_light_style_json));
        });


        return view;
    }


    @SuppressLint("SetTextI18n")
    //To remove warning for text directly set without using String xml resource
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        detailViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MainViewModel.class);

        detailViewModel.getSelected().observe(getViewLifecycleOwner(), launch -> {


            String fullTitle = launch.getName();
            String sub = fullTitle.substring(0, fullTitle.indexOf(" |"));
            String main = fullTitle.substring(fullTitle.indexOf("|") + 2);
            launchName.setText(main);
            launchSubTitle.setText(sub);

            lsp.setVisibility(View.GONE);
            launchServiceProvider.setVisibility(View.GONE);
            if (launch.getLsp() != null) {
                lsp.setVisibility(View.VISIBLE);
                launchServiceProvider.setVisibility(View.VISIBLE);
                launchServiceProvider.setText(launch.getLsp().getName());
            } else {
                lspName.setVisibility(View.GONE);
                lspLine.setVisibility(View.GONE);
                abbrevLsp.setVisibility(View.GONE);
            }


            vid.setVisibility(View.GONE);
            videoLaunchURL.setVisibility(View.GONE);
            if (launch.getVidURLs() != null && !launch.getVidURLs().isEmpty()) {
                vid.setVisibility(View.VISIBLE);
                videoLaunchURL.setVisibility(View.VISIBLE);
                URLCheck.setVisibility(View.VISIBLE);
                List<String> vidUrl = launch.getVidURLs();
                for (String v :
                        vidUrl) {
                    videoLaunchURL.setText(v);
                    videoLaunchURL.setLinkTextColor(getResources().getColor(R.color.cyanea_accent_reference));
                }
            }

            missionDetail.setVisibility(View.GONE);
            wikiLaunchM.setVisibility(View.GONE);
            wikiURLLaunch.setVisibility(View.GONE);

            if (launch.getMissions() != null) {

                for (Mission m :
                        launch.getMissions()) {
                    if (m != null) {
                        missionDetail.setVisibility(View.VISIBLE);
                        missionDetail.setText(m.getDescription());

                        /*
                         *Separately calling mission again to get the payloads
                         * This is because payload details aren't present when calling all the launches
                         *
                         * TODO: Check if the api source has been updated to include payload details in the first call itself
                         * Currently the api version is 1.4, if it is updated the api version number would be higher
                         */
                        getPayloads(m.getId(), detailViewModel);


                        if (m.getTypeName() != null && !m.getTypeName().isEmpty()) {
                            type.setVisibility(View.VISIBLE);
                            typeNameMission.setVisibility(View.VISIBLE);
                            typeNameMission.setText(m.getTypeName());
                        }

                        if (m.getWikiURL() != null && !m.getWikiURL().isEmpty()) {
                            wikiLaunchM.setVisibility(View.VISIBLE);
                            wikiURLLaunch.setVisibility(View.VISIBLE);
                            wikiURLLaunch.setText(m.getWikiURL());
                            wikiURLLaunch.setLinkTextColor(getResources().getColor(R.color.cyanea_accent_reference));
                        }

                    }
                }
            }


            getLaunchPadInfo(launch.getLocation().getPads());
            locationText.setText(launch.getLocation().getName());
            countryCodeText.setText(launch.getLocation().getCountryCode());

            getDate(launch);

            getRockets(launch);

            getLsp(launch);

            compare(lspUrls, rocketInfo);

        });


    }


    /**
     * This needs RxJava, which I do not know.
     * Right now if you show the progress bar when MissionList itself is null, the progress bar will never be removed
     * That's why I have decided to get rid of the progressbar right now
     * <p>
     * This issue can be solved by using RxJava or by doing the network call in the fragment itself (shown in the commented out part)
     */

    private void getPayloads(int missionID, MainViewModel detailViewModel) {


        detailViewModel.getNetworkStateMutableLiveData().observe(getViewLifecycleOwner(), networkState -> {

            if (networkState == NetworkState.LOADING) {
                progressCircular.setVisibility(View.VISIBLE);
            } else if (networkState == NetworkState.LOADED) {
                progressCircular.setVisibility(View.GONE);
            } else if (networkState == NetworkState.FAILED) {
                progressCircular.setVisibility(View.GONE);
                payload.setVisibility(View.GONE);
                progressResult.setVisibility(View.VISIBLE);
            }

        });

        detailViewModel.getMission(missionID).observe(getViewLifecycleOwner(), missionResponse -> {

            //reduced if else statements because there were stutters when loading recyclerView

            List<Mission> missionList = missionResponse.getMissions();

            if (missionList.size() > 0) {
                progressResult.setVisibility(View.VISIBLE);
            }

            for (Mission m : Objects.requireNonNull(missionList)) {

                if (m != null) {
                    payloads = m.getPayloads();


                    //Submitting the list of payloads to payloadAdapter
                    adapter.submitList(payloads);

                    if (payloads != null && !payloads.isEmpty()) {
                        payload.setVisibility(View.VISIBLE);
                        progressResult.setVisibility(View.GONE);


                    } else {
                        payload.setVisibility(View.GONE);
                        progressResult.setVisibility(View.VISIBLE);
                    }


                }

            }
        });


        payloadView.setAdapter(adapter);


//        GetLaunchDataService getLaunchDataService = RetrofitInstance.getLaunchDataService();
//        getLaunchDataService.getPayloads(missionID).enqueue(new Callback<MissionResponse>() {
//            @Override
//            public void onResponse(@Nullable Call<MissionResponse> call, @NonNull Response<MissionResponse> response) {
//                MissionResponse missionResponse = response.body();
//
//                if (missionResponse != null) {
//                    missionList = missionResponse.getMissions();
//                    if (missionList.isEmpty()) {
//                        progressCircular.setVisibility(View.GONE);
//                        progressResult.setVisibility(View.VISIBLE);
//
//                    }
//
//                    for (Mission m : missionList) {
//                        if (m != null) {
//
//                            payloads = m.getPayloads();
//
//                            //Submitting the list of payloads to payloadAdapter
//                            adapter.submitList(payloads);
//
//                            if (payloads != null && !payloads.isEmpty()) {
//                                payload.setVisibility(View.VISIBLE);
//                            } else {
//                                progressResult.setVisibility(View.VISIBLE);
//                            }
//                            progressCircular.setVisibility(View.GONE);
//
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(@Nullable Call<MissionResponse> call, @NonNull Throwable t) {
//                payload.setVisibility(View.GONE);
//                progressResult.setVisibility(View.VISIBLE);
//            }
//        });

    }


    private void getLaunchPadInfo(List<Pad> pads) {
        padInfoURL.setVisibility(View.GONE);
        locationInfo.setVisibility(View.GONE);
        wikiInfo.setVisibility(View.GONE);
        wikiInfoURL.setVisibility(View.GONE);

        if (pads != null) {
            for (Pad p : pads) {
                tag = new LatLng(p.getLatitude(), p.getLongitude());
                mapMarkerTitle = p.getName();
                padLocation.setText(p.getName());
                String wikiU = p.getWikiURL();
                String infoU = p.getInfoURL();


                if (wikiU != null && wikiU.trim().length() > 0 && !wikiU.equals(infoU)) {
                    wikiInfo.setVisibility(View.VISIBLE);
                    wikiInfoURL.setVisibility(View.VISIBLE);
                    wikiInfoURL.setText(p.getWikiURL());
                    wikiInfoURL.setLinkTextColor(getResources().getColor(R.color.cyanea_accent_reference));
                }

                if (infoU != null && infoU.trim().length() > 0) {
                    locationInfo.setVisibility(View.VISIBLE);
                    padInfoURL.setVisibility(View.VISIBLE);
                    padInfoURL.setText(p.getInfoURL());
                    padInfoURL.setLinkTextColor(getResources().getColor(R.color.cyanea_accent_reference));
                }
            }
        }


    }


    //*get Launch Service Provider
    private void getLsp(Launch launch) {

        if (launch.getLsp() != null) {
            Lsp lsp = launch.getLsp();

            lspName.setText(lsp.getName());
            abbrevLsp.setText(Html.fromHtml("<b>" + "Abbreviation " + "</b>" + lsp.getAbbrev()));

            if (lsp.getWikiURL() != null) {
                wikiLsp.setVisibility(View.VISIBLE);
                wikiLspURl.setVisibility(View.VISIBLE);
                wikiLspURl.setText(lsp.getWikiURL());
                wikiLspURl.setLinkTextColor(getResources().getColor(R.color.cyanea_accent_reference));
            }


            if (lsp.getInfoURLs() != null && !lsp.getInfoURLs().isEmpty()) {

                infoLsphead.setVisibility(View.VISIBLE);
                infoURLLsp.setVisibility(View.VISIBLE);
                lspUrls = lsp.getInfoURLs();

                // this URL is wrong and is redirected to some private account, so removing it if it exists.
                lspUrls.remove("https://www.facebook.com/SpaceX");
                getLSpInfoUrls(lspUrls);
            }
        }
    }

    //Using StringAdapter which is a generic adapter
    private void getLSpInfoUrls(List<String> lspUrlSort) {
        stringAdapter = new StringAdapter<String>(lspUrlSort) {
            @Override
            public RecyclerView.ViewHolder setViewHolder(ViewGroup parent) {
                final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.string_list_item, parent, false);
                return new ItemViewHolder(view);
            }

            @Override
            public void onBindData(RecyclerView.ViewHolder holder, int val) {

                ItemViewHolder holder1 = (ItemViewHolder) holder;
                holder1.Url.setText(lspUrlSort.get(val));
                ((ItemViewHolder) holder).Url.setLinkTextColor(getResources().getColor(R.color.cyanea_accent_reference));
            }
        };

        infoURLLsp.setAdapter(stringAdapter);
    }

    private void getRockets(Launch launch) {

        Rocket rocket = launch.getRocket();
        rocketName.setText(rocket.getName());

        if (rocket.getConfiguration() != null) {
            line4.setVisibility(View.VISIBLE);
            config.setVisibility(View.VISIBLE);
            configurationRocket.setVisibility(View.VISIBLE);
            configurationRocket.setText(rocket.getConfiguration());
        }

        if (rocket.getFamilyname() != null) {
            fam.setVisibility(View.VISIBLE);
            familyName.setVisibility(View.VISIBLE);
            familyName.setText(rocket.getFamilyname());
        }

        if (rocket.getWikiURL() != null) {
            wikiR.setVisibility(View.VISIBLE);
            wikiRocket.setVisibility(View.VISIBLE);
            wikiRocket.setText(rocket.getWikiURL());
            wikiRocket.setLinkTextColor(getResources().getColor(R.color.cyanea_accent_reference));
        }

        if (rocket.getAgencies() != null) {
            List<Agency> rocketAgency = rocket.getAgencies();

            if (rocketAgency != null) {
                for (Agency aR : rocketAgency) {

                    this.rocketAgency.setVisibility(View.VISIBLE);
                    agencyName.setVisibility(View.VISIBLE);
                    agencyName.setText(aR.getName());

                    abbrev.setVisibility(View.VISIBLE);
                    abbrevRocket.setVisibility(View.VISIBLE);
                    abbrevRocket.setText(aR.getAbbrev());

                    if (aR.getWikiURL() != null) {
                        wikiURA.setVisibility(View.VISIBLE);
                        wikiRocketAgency.setVisibility(View.VISIBLE);
                        wikiRocketAgency.setText(aR.getWikiURL());
                        wikiRocketAgency.setLinkTextColor(getResources().getColor(R.color.cyanea_accent_reference));
                    }

                    if (aR.getInfoURLs() != null) {
                        rocketInfo = aR.getInfoURLs();
                    }
                }
            }
        }
    }


    //Comparing rocket and lsp URLs to remove URLs that could be same
    private void compare(List<String> lspUrls, List<String> rocketInfo) {

        if ((lspUrls != null) && (rocketInfo != null)) {
            List<String> rocketInfoSort = new ArrayList<>(rocketInfo);
            rocketInfoSort.removeAll(lspUrls);
            //removing wrong URL if it exists
            rocketInfoSort.remove("https://www.facebook.com/SpaceX");


            if (!rocketInfoSort.isEmpty()) {

                infoURA.setVisibility(View.VISIBLE);

                stringAdapter = new StringAdapter<String>(rocketInfoSort) {
                    @Override
                    public RecyclerView.ViewHolder setViewHolder(ViewGroup parent) {
                        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.string_list_item, parent, false);
                        return new ItemViewHolder(view);
                    }

                    @Override
                    public void onBindData(RecyclerView.ViewHolder holder, int pos) {
                        ItemViewHolder holder1 = (ItemViewHolder) holder;
                        holder1.Url.setText(rocketInfoSort.get(pos));
                        ((ItemViewHolder) holder).Url.setLinkTextColor(getResources().getColor(R.color.cyanea_accent_reference));
                    }
                };

                infoURLsRAge.setAdapter(stringAdapter);
            }


        }
    }


    @SuppressLint("SetTextI18n")
    private void getDate(Launch launch) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofPattern("MMMM d, yyyy HH:mm:ss", Locale.ENGLISH);

        int tbdTime = launch.getTbdtime();
        int tbdDate = launch.getTbddate();

        String startDateTime = launch.getWindowstart();
        startDateTime = startDateTime.substring(0, startDateTime.indexOf(" U"));
        startDateTime = LocalDateTime.parse(startDateTime, dateTimeFormatter)
                .atOffset(ZoneOffset.UTC)
                .atZoneSameInstant(ZoneId.systemDefault())
                .format(dateTimeFormatter);
        int startYear = LocalDateTime.parse(startDateTime, dateTimeFormatter).getYear();

        String startAmpm = startDateTime.substring(startDateTime.length() - 8);
        startAmpm = LocalTime.parse(startAmpm, DateTimeFormatter.ofPattern("HH:mm:ss"))
                .format(DateTimeFormatter.ofPattern("hh:mm:ss a"));


        String endDateTime = launch.getWindowend();
        endDateTime = endDateTime.substring(0, endDateTime.indexOf(" U"));
        endDateTime = LocalDateTime.parse(endDateTime, dateTimeFormatter)
                .atOffset(ZoneOffset.UTC)
                .atZoneSameInstant(ZoneId.systemDefault())
                .format(dateTimeFormatter);


        String endAmpm = endDateTime.substring(endDateTime.length() - 8);
        endAmpm = LocalTime.parse(endAmpm, DateTimeFormatter.ofPattern("HH:mm:ss"))
                .format(DateTimeFormatter.ofPattern("hh:mm:ss a"));


        year.setText(Integer.toString(startYear));


        if (tbdTime == 1 && tbdDate < 1) {
            String timeTBD = "Time TBD";
            startDateTime = startDateTime.substring(0, startDateTime.indexOf(",")) + " | " + timeTBD;

            endDateTime = endDateTime.substring(0, endDateTime.indexOf(",")) + " | " + timeTBD;


        } else if (tbdTime < 1 && tbdDate == 1) {
            String dateTBD = "Date TBD";
            startDateTime = startDateTime.substring(0, startDateTime.indexOf(",")) + " | " + dateTBD;

            endDateTime = endDateTime.substring(0, endDateTime.indexOf(",")) + " | " + dateTBD;


        } else if (tbdDate == 1 && tbdTime == 1) {
            String dateTimeTBD = "Date & Time TBD";
            startDateTime = startDateTime.substring(0, startDateTime.indexOf(" ")) + " | " + dateTimeTBD;

            endDateTime = endDateTime.substring(0, endDateTime.indexOf(" ")) + " | " + dateTimeTBD;


        } else {
            startDateTime = startDateTime.substring(0, startDateTime.indexOf(",")) + " at " + startAmpm;

            endDateTime = endDateTime.substring(0, endDateTime.indexOf(",")) + " at " + endAmpm;


        }


        windowStart.setText(startDateTime);
        windowEnd.setText(endDateTime);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //Clear Menu, else two copies of the menu option would appear on the Toolbar
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();  //Required for MapView Lite
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();  //Required for MapView Lite

        //To Hide the toolbar

        //this is set expanded in the background, else the fragment will open at the wrong scroll position
        ((LaunchListActivity) Objects.requireNonNull(getActivity())).appbar.setExpanded(true, false);
        Objects.requireNonNull(((LaunchListActivity) getActivity()).getSupportActionBar()).hide();
    }

    @Override
    public void onStop() {
        super.onStop();

        //To show Toolbar when going back
        ((LaunchListActivity) Objects.requireNonNull(getActivity())).appbar.setExpanded(false, true);
        Objects.requireNonNull(((LaunchListActivity) getActivity()).getSupportActionBar()).show();

    }

    @Override
    public void onDestroyView() {
//      Trying to reduce Memory leaks
        payloadView.setAdapter(null);
        infoURLLsp.setAdapter(null);
        infoURLsRAge.setAdapter(null);
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
        mGoogleMap.clear();
        unbinder.unbind();

        super.onDestroyView();
    }
}


