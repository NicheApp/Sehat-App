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


public class yogaadapter extends RecyclerView.Adapter<yogaadapter.ImageViewHolder>  {
    public Context mContext;
    public List<Note> mUploads;
    public List<Note> mUploadscopy;



    public yogaadapter(Context context, List<Note> uploads) {

        mContext = context;

        mUploads = uploads;
        mUploadscopy = new ArrayList<>(mUploads);

    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);


        return new ImageViewHolder(v);
    }
    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {


        Note uploadCurrent = mUploads.get(position);
       RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();

        Glide.with(mContext)
                .load(uploadCurrent.getLink())
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }


                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.yogaimage);
        holder.textViewTitle.setText(uploadCurrent.getYoga());
        holder.textViewDescription.setText(uploadCurrent.getDesc());



    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewPriority;
        ImageView yogaimage;
        public ImageViewHolder(View itemView) {
            super(itemView);
            yogaimage=itemView.findViewById(R.id.img);
            textViewTitle = itemView.findViewById(R.id.title);
            textViewDescription = itemView.findViewById(R.id.desc);
        }



    }}