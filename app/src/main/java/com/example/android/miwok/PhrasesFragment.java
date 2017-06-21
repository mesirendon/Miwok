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
public class PhrasesFragment extends Fragment {
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
            new Word("minto wuksus", "Where are you going?", R.raw.phrase_where_are_you_going),
            new Word("tinnә oyaase'nә", "What is your name?", R.raw.phrase_what_is_your_name),
            new Word("oyaaset...", "My name is...", R.raw.phrase_my_name_is),
            new Word("michәksәs?", "How are you feeling?", R.raw.phrase_how_are_you_feeling),
            new Word("kuchi achit", "I’m feeling good.", R.raw.phrase_im_feeling_good),
            new Word("әәnәs'aa?", "Are you coming?", R.raw.phrase_are_you_coming),
            new Word("hәә’ әәnәm", "Yes, I’m coming.", R.raw.phrase_yes_im_coming),
            new Word("әәnәm", "I’m coming.", R.raw.phrase_im_coming),
            new Word("yoowutis", "Let’s go.", R.raw.phrase_lets_go),
            new Word("әnni'nem", "Come here.", R.raw.phrase_come_here)
    ));

    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_words, container, false);
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        final ListView listView = (ListView) rootView.findViewById(R.id.list);
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_phrases);
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
