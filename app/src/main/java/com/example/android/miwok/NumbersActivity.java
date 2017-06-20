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

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class NumbersActivity extends AppCompatActivityUtil {
    ArrayList<Word> words = new ArrayList<>(Arrays.asList(
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);
        final ListView listView = (ListView) findViewById(R.id.list);
        assert listView != null;
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = (Word) listView.getItemAtPosition(i);
                releaseMediaPlayer();
                requestAudioFocusAndPlay(NumbersActivity.this, word);
            }
        });
    }
}
