package com.example.android.bakingapp.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.utilities.BakingSteps;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

/**
 * Created by medo on 30-Oct-17.
 */

public class MasterStepFragment extends Fragment {
    public MasterStepFragment(){

    }
    private boolean myPlayWhenReady = true;
    private int myCurrentWindow;
    private long myCurrentVideoPosition;
    private TextView DetailDescription;
    private ImageView img_cake;

    private static final String STEP = "step";
    private static final String POSITION_VIDEO = "position_video";
    private static final String PLAY_WHEN_READY = "play_when_ready";
    private static final String CURRENT_WINDOW = "current_window";

    private BakingSteps myStep;
    private View rootView;
    private SimpleExoPlayer myPlayer;
    private SimpleExoPlayerView myPlayerView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (rootView == null) {

            rootView = inflater.inflate(R.layout.master_step_item, container, false);
            myPlayerView=(SimpleExoPlayerView)rootView.findViewById(R.id.video_view);
            DetailDescription=(TextView)rootView.findViewById(R.id.step_instruction);
            img_cake=(ImageView)rootView.findViewById(R.id.img_thumbnail);
            Bundle b= getArguments();
            if(b!=null) {
                myStep = (BakingSteps) b.getParcelable("baking_fragment_step");
            }

        }

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(STEP)) {
                myStep = (BakingSteps) savedInstanceState.getParcelable(STEP);
            }
            if (savedInstanceState.containsKey(CURRENT_WINDOW)) {
                myCurrentWindow = (Integer)savedInstanceState.getInt(CURRENT_WINDOW);
            }
            if (savedInstanceState.containsKey(POSITION_VIDEO)) {
                myCurrentVideoPosition =(Long) savedInstanceState.getLong(POSITION_VIDEO);
            }
            if (savedInstanceState.containsKey(PLAY_WHEN_READY)) {
                myPlayWhenReady = (Boolean)savedInstanceState.getBoolean(PLAY_WHEN_READY);
            }
        }

        if (myStep != null) {
            DetailDescription.setText(myStep.getDescription());
            if (myStep.getVideoURL().isEmpty()) {
                Toast.makeText(getContext(), " Oops!, Sorry there is no video to show ", Toast.LENGTH_SHORT).show();
                myPlayerView.setVisibility(View.GONE);
            }
            if (!myStep.getThumbnailURL().isEmpty()) {
                Picasso.with(getContext()).load(myStep.getThumbnailURL())
                        .into(img_cake);
            } else {
                img_cake.setVisibility(View.GONE);
            }
        }
        return rootView;
    }

    private void initializePlayer(String url) {
        if (myPlayer == null) {
            myPlayer = ExoPlayerFactory.newSimpleInstance(
                    new DefaultRenderersFactory(getContext()),
                    new DefaultTrackSelector(),
                    new DefaultLoadControl());

            if(myPlayerView!=null){
               myPlayerView.setPlayer(myPlayer);
                myPlayerView.setResizeMode(View.ACCESSIBILITY_LIVE_REGION_ASSERTIVE);
            }

            myPlayer.setPlayWhenReady(myPlayWhenReady);

            Uri uri = Uri.parse(url);
            MediaSource mediaSource = buildMediaSource(uri);
            boolean haveResumePosition = myCurrentWindow != C.INDEX_UNSET;
            myPlayer.prepare(mediaSource, !haveResumePosition, false);
            if (haveResumePosition) {
                myPlayer.seekTo(myCurrentWindow, myCurrentVideoPosition);
            }
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource(uri,
                new DefaultDataSourceFactory(getContext(), getString(R.string.app_name)),
                new DefaultExtractorsFactory(), null, null);
    }
    private void releasePlayer() {
        if (myPlayer != null) {
            myCurrentVideoPosition = myPlayer.getCurrentPosition();
            myCurrentWindow = myPlayer.getCurrentWindowIndex();
            myPlayWhenReady = myPlayer.getPlayWhenReady();
            myPlayer.release();
            myPlayer = null;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(STEP, myStep);
        outState.putLong(POSITION_VIDEO, Math.max(0, myCurrentVideoPosition));
        outState.putInt(CURRENT_WINDOW, myCurrentWindow);
        outState.putBoolean(PLAY_WHEN_READY, myPlayWhenReady);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            if (!myStep.getVideoURL().isEmpty()) {
               initializePlayer(myStep.getVideoURL());
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if ((Util.SDK_INT <= 23 || myPlayer == null)) {
            if (!myStep.getVideoURL().isEmpty()) {
              initializePlayer(myStep.getVideoURL());
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }
}
