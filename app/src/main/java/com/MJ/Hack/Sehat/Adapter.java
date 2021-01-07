package com.MJ.Hack.Sehat;

import android.content.Context;
import android.content.Intent;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.mapzen.speakerbox.Speakerbox;

import java.util.List;

public class Adapter extends PagerAdapter {

    private List<Note> models;
    private LayoutInflater layoutInflater;
    private Context context;
    boolean x=true;
  Speakerbox speakerbox;
    public Adapter(List<Note> models, Context context,Speakerbox speakerbox) {
        this.models = models;
        this.context = context;
        this.speakerbox=speakerbox;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, container, false);

        ImageView imageView;
        TextView title, desc;
        Button play =(Button) view.findViewById(R.id.play);
        imageView = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        desc = view.findViewById(R.id.desc);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();

        Glide.with(context)
                .load(models.get(position).getLink())
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
                .into(imageView);

        //imageView.setImageResource(models.get(position).getLink());
        title.setText(models.get(position).getYoga());
        desc.setText(models.get(position).getDesc());
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                speakerbox.play(models.get(position).getDesc());


            }
        });
       view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                speakerbox.play(models.get(position).getDesc());
            }
        });

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}