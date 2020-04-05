package com.example.videoapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videoapp.R;
import com.example.videoapp.listener.ContentItemClickListener;
import com.example.videoapp.model.VideoContent;

import java.util.ArrayList;

public class VideoContentAdapter extends RecyclerView.Adapter<VideoContentAdapter.ContentViewHolder> {
    private Context mContext;
    private ArrayList<VideoContent> mVideoContentArrayList;
    private ContentItemClickListener mContentItemClickListener;

    public VideoContentAdapter(Context context, ArrayList<VideoContent> videoContents, ContentItemClickListener contentItemClickListener) {
        this.mContext = context;
        this.mVideoContentArrayList = videoContents;
        this.mContentItemClickListener = contentItemClickListener;
    }

    @NonNull
    @Override
    public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.content_item_view, parent, false);
        return new ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentViewHolder holder, int position) {
        VideoContent videoContent = mVideoContentArrayList.get(position);
        if(videoContent!=null){
            holder.tvVideoTitle.setText(videoContent.getVideoTitle());
            holder.tvVideoDecription.setText(videoContent.getVideoDescription());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mVideoContentArrayList!=null && mVideoContentArrayList.size() >0 ? mVideoContentArrayList.size() : 0;
    }

    class ContentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvVideoTitle, tvVideoDecription;
        private ImageView thumbnail;

        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            tvVideoTitle = itemView.findViewById(R.id.tv_video_title);
            tvVideoDecription = itemView.findViewById(R.id.tv_video_desc);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mContentItemClickListener.onContentItemClicked(getAdapterPosition());
        }
    }
}
