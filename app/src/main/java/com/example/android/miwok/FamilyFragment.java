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
public class FamilyFragment extends Fragment {
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
            new Word("әpә", "father", R.drawable.family_father, R.raw.family_father),
            new Word("әṭa", "mother", R.drawable.family_mother, R.raw.family_mother),
            new Word("angsi", "son", R.drawable.family_son, R.raw.family_son),
            new Word("tune", "daughter", R.drawable.family_daughter, R.raw.family_daughter),
            new Word("taachi", "older brother", R.drawable.family_older_brother, R.raw.family_older_brother),
            new Word("chalitti", "younger brother", R.drawable.family_younger_brother, R.raw.family_younger_brother),
            new Word("teṭe", "older sister", R.drawable.family_older_sister, R.raw.family_older_sister),
            new Word("kolliti", "younger sister", R.drawable.family_younger_sister, R.raw.family_younger_sister),
            new Word("ama", "grandmother", R.drawable.family_grandmother, R.raw.family_grandmother),
            new Word("paapa", "grandfather", R.drawable.family_grandfather, R.raw.family_grandfather)
    ));

    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_words, container, false);
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        final ListView listView = (ListView) rootView.findViewById(R.id.list);
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_family);
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
