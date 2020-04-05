package com.example.videoapp.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videoapp.R;
import com.example.videoapp.Utils.AppUtils;
import com.example.videoapp.adapters.VideoContentAdapter;
import com.example.videoapp.listener.ContentItemClickListener;
import com.example.videoapp.model.VideoContent;

import java.util.ArrayList;

public class VideoPlayerFragment extends Fragment implements ContentItemClickListener {
    private Context mContext;
    private VideoView videoView;
    private ProgressBar mProgressBar;
    private TextView tvVideoTitle;
    private RecyclerView rcvVideoContent;
    private ArrayList<VideoContent> mVideoContentArrayList;
    private int mLastSelectedPosition = 0;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.mContext = context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVideoContentArrayList = AppUtils.parseAndGetVideoContent(mContext);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.video_player_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
    }

    /**
     * Initialize UI elements.
     */
    private void initUI(View view) {
        mProgressBar = view.findViewById(R.id.progressBar);
        videoView = view.findViewById(R.id.videoView);
        tvVideoTitle = view.findViewById(R.id.tvVideoTitle);
        rcvVideoContent = view.findViewById(R.id.rcvVideoContent);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        rcvVideoContent.setLayoutManager(layoutManager);
        VideoContentAdapter videoContentAdapter = new VideoContentAdapter(mContext, mVideoContentArrayList, this);
        rcvVideoContent.setAdapter(videoContentAdapter);
        onContentItemClicked(mLastSelectedPosition);
    }

    @Override
    public void onContentItemClicked(int position) {
        if (mVideoContentArrayList != null && mVideoContentArrayList.size() > 0) {
            VideoContent videoContent = mVideoContentArrayList.get(position);
            if (videoContent != null) {
                MediaController mediaController = new MediaController(mContext);
                String videoPath = videoContent.getVideoPath();
                Uri videoURI = Uri.parse(videoPath);
                videoView.setVideoURI(videoURI);
                videoView.setMediaController(mediaController);
                mediaController.setAnchorView(videoView);
                tvVideoTitle.setText(videoContent.getVideoTitle());
                mProgressBar.setVisibility(View.VISIBLE);
                videoView.requestFocus();
                videoView.start();
                try {
                    videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mProgressBar.setVisibility(View.GONE);
                        }
                    });
                } catch (Exception ex) {
                    Toast.makeText(mContext, "can not play ", Toast.LENGTH_SHORT).show();
                }
            } else {
                tvVideoTitle.setText("Can't play...please try again");
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", videoView.getCurrentPosition());
        videoView.pause();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            mLastSelectedPosition = savedInstanceState.getInt("position");
        }
        videoView.seekTo(mLastSelectedPosition);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){

        }
    }
}
