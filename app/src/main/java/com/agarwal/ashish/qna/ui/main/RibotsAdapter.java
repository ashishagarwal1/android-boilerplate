package com.agarwal.ashish.qna.ui.main;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.agarwal.ashish.qna.R;
import com.agarwal.ashish.qna.room.entities.RibotProfile;
import com.bumptech.glide.Glide;

public class RibotsAdapter extends RecyclerView.Adapter<RibotsAdapter.RibotViewHolder> {

    private List<RibotProfile> mRibotProfiles;

    @Inject
    public RibotsAdapter() {
        mRibotProfiles = new ArrayList<>();
    }

    public void setRibots(List<RibotProfile> ribotProfiles) {
        mRibotProfiles = ribotProfiles;
    }

    @Override
    public RibotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ribot, parent, false);
        return new RibotViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RibotViewHolder holder, int position) {
        RibotProfile ribotProfile = mRibotProfiles.get(position);
        Glide.with(holder.hexColorView.getContext())
                .load(ribotProfile.getAvatar())
                .into(holder.hexColorView);


        holder.hexColorView.setBackgroundColor(Color.parseColor(ribotProfile.getHexColor()));
        holder.nameTextView.setText(String.format("%s %s",
                ribotProfile.getName().getFirst(), ribotProfile.getName().getLast()));
        holder.emailTextView.setText(ribotProfile.getEmail());
    }

    @Override
    public int getItemCount() {
        return mRibotProfiles.size();
    }

    class RibotViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.view_hex_color) ImageView hexColorView;
        @BindView(R.id.text_name) TextView nameTextView;
        @BindView(R.id.text_email) TextView emailTextView;

        public RibotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
