package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {
    private MediaPlayer mPlayer;
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener focusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            switch (i) {
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    mPlayer.stop();
                    break;
                case AudioManager.AUDIOFOCUS_GAIN:
                    mPlayer.start();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    releaseMediaPlayer();
                    break;
            }
        }
    };

    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private void releaseMediaPlayer() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
            mAudioManager.abandonAudioFocus(focusChangeListener);
        }
    }

    private void requestAudioFocusAndPlay(Context context, Word word) {
        int result = mAudioManager.requestAudioFocus(
                focusChangeListener,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
        );

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            mPlayer = MediaPlayer.create(context, word.getAudioResourceId());
            mPlayer.start();
            mPlayer.setOnCompletionListener(completionListener);
        }
    }

    private ArrayList<Word> words = new ArrayList<>(Arrays.asList(
            new Word("lutti", "one", R.drawable.number_one, R.raw.number_one),
            new Word("otiiko", "two", R.drawable.number_two, R.raw.number_two),
            new Word("tolookosu", "three", R.drawable.number_three, R.raw.number_three),
            new Word("oyyisa", "four", R.drawable.number_four, R.raw.number_four),
            new Word("massokka", "five", R.drawable.number_five, R.raw.number_five),
            new Word("temmokka", "six", R.drawable.number_six, R.raw.number_six),
            new Word("kenekaku", "seven", R.drawable.number_seven, R.raw.number_seven),
            new Word("kawinta", "eight", R.drawable.number_eight, R.raw.number_eight),
            new Word("wo'e", "nine", R.drawable.number_nine, R.raw.number_nine),
            new Word("na'aacha", "ten", R.drawable.number_ten, R.raw.number_ten)
    ));

    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_words, container, false);
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        final ListView listView = (ListView) rootView.findViewById(R.id.list);
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_numbers);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = (Word) listView.getItemAtPosition(i);
                releaseMediaPlayer();
                requestAudioFocusAndPlay(getActivity(), word);
            }
        });
        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

}
