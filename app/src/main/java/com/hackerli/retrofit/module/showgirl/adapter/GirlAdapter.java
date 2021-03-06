package com.hackerli.retrofit.module.showgirl.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hackerli.retrofit.R;
import com.hackerli.retrofit.base.BaseRVAdapter;
import com.hackerli.retrofit.data.entity.Girl;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by CoXier on 2016/3/20.
 */
public class GirlAdapter extends BaseRVAdapter<Girl,GirlAdapter.GirlHolder> {
    private Fragment mFragment;
    private List<Girl> mList;
    private static int mWidthPixels;


    private GirlOnClickListener mOnClickListener;


    public GirlAdapter(Fragment fragment, List<Girl> mList) {
        this.mFragment = fragment;
        this.mList = mList;
        mOnClickListener = (GirlOnClickListener) mFragment;
        mWidthPixels = mFragment.getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    public GirlHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_girl, parent, false);
        return new GirlHolder(v);
    }

    @Override
    public void onBindViewHolder(GirlHolder holder, int position) {
        Girl girl = mList.get(position);
        if (!isScrolling()) {
            Glide.with(mFragment)
                    .load(girl.getUrl())
                    .centerCrop()
                    .into(holder.imageView);
        }

        String desc = girl.getDesc();
        holder.textView.setText(desc);
        setOnItemClickListener(holder.imageView, girl);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    protected void setOnItemClickListener(View view, final Girl data) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListener!=null)
                    mOnClickListener.viewGirlPhoto(data);
            }
        });
    }

    class GirlHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_girl)
        ImageView imageView;
        @Bind(R.id.tv_date)
        TextView textView;

        GirlHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            float scale = (float) (Math.random() + 1);
            while(scale>1.6||scale<1.1){
                scale = (float) (Math.random() + 1);
            }
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            params.height = (int) (mWidthPixels * scale * 0.448);
            imageView.setLayoutParams(params);
        }

    }

}
