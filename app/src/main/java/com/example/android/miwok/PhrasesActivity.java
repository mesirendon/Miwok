/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class PhrasesActivity extends AppCompatActivityUtil {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);
        final ListView listView = (ListView) findViewById(R.id.list);
        assert listView != null;
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = (Word) listView.getItemAtPosition(i);
                releaseMediaPlayer();
                requestAudioFocusAndPlay(PhrasesActivity.this, word);
            }
        });
    }
}
