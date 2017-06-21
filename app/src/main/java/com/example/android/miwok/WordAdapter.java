package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mesi on 17/06/17.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    private final static int NO_BACKGROUND_COLOR_SET = -1;
    private int mBackgroundColor = NO_BACKGROUND_COLOR_SET;
    private MediaPlayer player;

    public WordAdapter(@NonNull Context context, ArrayList<Word> words, int backgroundColor) {
        super(context, 0, words);
        this.mBackgroundColor = backgroundColor;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item,
                    parent,
                    false
            );
        }

        Word word = getItem(position);

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        LinearLayout linearLayout = (LinearLayout) listItemView.findViewById(R.id.content_linear_layout);

        assert word != null;
        if (word.hasImage())
            imageView.setImageResource(word.getImageResourceId());
        else
            imageView.setVisibility(View.GONE);
        linearLayout.setBackgroundColor(ContextCompat.getColor(getContext(), mBackgroundColor));
        miwokTextView.setText(word.getMiwokTranslation());
        defaultTextView.setText(word.getDefaultTranslation());

        return listItemView;
    }
}
