package edu.miracosta.cs134.sandiegomusicevents;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import edu.miracosta.cs134.sandiegomusicevents.model.MusicEvent;

public class MusicEventListAdapter extends ArrayAdapter {

    private Context mContext;
    private int mResourse;
    private List<MusicEvent> mMusicEventList;

    public MusicEventListAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        mContext = context;
        mResourse = resource;
        mMusicEventList = objects;
    }

    //Override a methiod called getView
    //Ctrl + O => override method

    @NonNull
    @Override
    public View getView(int position, @Nullable View ConvertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourse, null);

        MusicEvent selectedEvent = mMusicEventList.get(position);
        //inflate the information about the artist name and date

        TextView musicEventListTextView = view.findViewById(R.id.musicEventListTextView);
        musicEventListTextView.setText(selectedEvent.getArtist());

        TextView musicEventListDateTextView = view.findViewById(R.id.musicEventListDateTextView);
        musicEventListDateTextView.setText(selectedEvent.getDate());

        ImageView musicEventImageView = view.findViewById(R.id.musicEventListImageView);
        AssetManager am = mContext.getAssets();
        try{
            InputStream stream = am.open(selectedEvent.getImageName());
            Drawable image = Drawable.createFromStream(stream, selectedEvent.getArtist());
            musicEventImageView.setImageDrawable(image);
        } catch (IOException e){
            Log.e ("SD Music Events", "Error Loading "+ selectedEvent.getArtist(),e);
        }
        return view;
    }

}