package com.MJ.Hack.Sehat;

import android.content.Context;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;


public class searchadapter extends RecyclerView.Adapter<searchadapter.ImageViewHolder>  {
    public Context mContext;
    public List<Article> mUploads;
    public List<Article> mUploadscopy;



    public searchadapter(Context context, List<Article> uploads) {

        mContext = context;

        mUploads = uploads;
        mUploadscopy = new ArrayList<>(mUploads);

    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card_item, parent, false);


        return new ImageViewHolder(v);
    }
    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {


      Article uploadCurrent = mUploads.get(position);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();

        Glide.with(mContext)
                .load(uploadCurrent.getUrlToImage())
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }


                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imageView);
        holder.title.setText(uploadCurrent.getTitle());
        holder.desc.setText(uploadCurrent.getDescription());
        holder.source.setText(uploadCurrent .getSource().getName());
        holder.time.setText(" \u2022 " + Utils.DateToTimeFormat(uploadCurrent.getPublishedAt()));
        holder.published_ad.setText(Utils.DateFormat(uploadCurrent.getPublishedAt()));
        holder.author.setText(uploadCurrent.getAuthor());




    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView title, desc, author, published_ad, source, time;
        ImageView imageView;
        ProgressBar progressBar;
        AdapterView.OnItemClickListener onItemClickListener;
        public ImageViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            author = itemView.findViewById(R.id.author);
            published_ad = itemView.findViewById(R.id.publishedAt);
            source = itemView.findViewById(R.id.source);
            time = itemView.findViewById(R.id.time);
            imageView = itemView.findViewById(R.id.img);
            progressBar = itemView.findViewById(R.id.prograss_load_photo);
    }



}}