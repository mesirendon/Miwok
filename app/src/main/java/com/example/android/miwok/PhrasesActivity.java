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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class PhrasesActivity extends AppCompatActivity {

    ArrayList<Word> words = new ArrayList<>(Arrays.asList(
            new Word("minto wuksus", "Where are you going?"),
            new Word("tinnә oyaase'nә", "What is your name?"),
            new Word("oyaaset...", "My name is..."),
            new Word("michәksәs?", "How are you feeling?"),
            new Word("kuchi achit", "I’m feeling good."),
            new Word("әәnәs'aa?", "Are you coming?"),
            new Word("hәә’ әәnәm", "Yes, I’m coming."),
            new Word("әәnәm", "I’m coming."),
            new Word("yoowutis", "Let’s go."),
            new Word("әnni'nem", "Come here.")
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);
        ListView listView = (ListView) findViewById(R.id.list);
        assert listView != null;
        listView.setAdapter(adapter);
    }
}
