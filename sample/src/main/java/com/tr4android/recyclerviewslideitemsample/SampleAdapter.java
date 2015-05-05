package com.tr4android.recyclerviewslideitemsample;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tr4android.recyclerviewslideitem.SwipeAdapter;
import com.tr4android.recyclerviewslideitem.SwipeConfiguration;

import java.util.ArrayList;

/**
 * Created by ThomasR on 28.04.2015.
 */
public class SampleAdapter extends SwipeAdapter {
    ArrayList<String> mDataset;
    int[] colors = new int[]{R.color.color_red, R.color.color_pink, R.color.color_purple, R.color.color_deep_purple, R.color.color_indigo, R.color.color_blue, R.color.color_light_blue, R.color.color_cyan, R.color.color_teal, R.color.color_green, R.color.color_light_green, R.color.color_lime, R.color.color_yellow, R.color.color_amber, R.color.color_orange, R.color.color_deep_orange, R.color.color_brown, R.color.color_grey, R.color.color_blue_grey};

    private Context mContext;

    public SampleAdapter(Context context, RecyclerView recyclerView) {
        super(recyclerView);
        mContext = context;
        // create dummy dataset
        mDataset = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            mDataset.add("person" + String.valueOf(i + 1) + "@sample.com");
        }
    }

    public class SampleViewHolder extends RecyclerView.ViewHolder {
        View avatarView;
        TextView textView;

        public SampleViewHolder(View view) {
            super(view);
            avatarView = view.findViewById(R.id.avatarView);
            textView = (TextView) view.findViewById(R.id.textView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateSwipeViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sample, parent, true);
        SampleViewHolder sampleViewHolder = new SampleViewHolder(v);
        return sampleViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder swipeViewHolder, int i) {
        super.onBindViewHolder(swipeViewHolder, i);
        SampleViewHolder sampleViewHolder = (SampleViewHolder) swipeViewHolder;
        ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
        drawable.getPaint().setColor(mContext.getResources().getColor(colors[((int) (Math.random() * (colors.length - 1)))]));
        sampleViewHolder.avatarView.setBackgroundDrawable(drawable);
        sampleViewHolder.textView.setText(mDataset.get(i));
    }

    @Override
    public SwipeConfiguration onCreateSwipeConfiguration(int position) {
        SwipeConfiguration configuration = new SwipeConfiguration();
        configuration.setBackgroundColor(mContext.getResources().getColor(R.color.color_delete));
        configuration.setDescription(mContext.getResources().getString(R.string.action_delete));
        configuration.setDescriptionTextColor(mContext.getResources().getColor(android.R.color.white));
        configuration.setDrawableResId(R.drawable.ic_delete_white_24dp);
        return configuration;
    }

    @Override
    public void onSwipe(int position) {
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}