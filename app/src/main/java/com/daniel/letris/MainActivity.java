package com.daniel.letris;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.daniel.letris.database.StatDatabase;
import com.daniel.letris.entities.Statistics;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout let_container;
    TextView[] letters = new TextView[180];
    int[] letter_ids = {
            R.id.let_1,
            R.id.let_2,
            R.id.let_3,
            R.id.let_4,
            R.id.let_5,
            R.id.let_6,
            R.id.let_7,
            R.id.let_8,
            R.id.let_9,
            R.id.let_10,
            R.id.let_0_1,
            R.id.let_0_2,
            R.id.let_11,
            R.id.let_12,
            R.id.let_13,
            R.id.let_14,
            R.id.let_15,
            R.id.let_16,
            R.id.let_17,
            R.id.let_18,
            R.id.let_19,
            R.id.let_20,
            R.id.let_1_1,
            R.id.let_1_2,
            R.id.let_21,
            R.id.let_22,
            R.id.let_23,
            R.id.let_24,
            R.id.let_25,
            R.id.let_26,
            R.id.let_27,
            R.id.let_28,
            R.id.let_29,
            R.id.let_30,
            R.id.let_2_1,
            R.id.let_2_2,
            R.id.let_31,
            R.id.let_32,
            R.id.let_33,
            R.id.let_34,
            R.id.let_35,
            R.id.let_36,
            R.id.let_37,
            R.id.let_38,
            R.id.let_39,
            R.id.let_40,
            R.id.let_3_1,
            R.id.let_3_2,
            R.id.let_41,
            R.id.let_42,
            R.id.let_43,
            R.id.let_44,
            R.id.let_45,
            R.id.let_46,
            R.id.let_47,
            R.id.let_48,
            R.id.let_49,
            R.id.let_50,
            R.id.let_4_1,
            R.id.let_4_2,
            R.id.let_51,
            R.id.let_52,
            R.id.let_53,
            R.id.let_54,
            R.id.let_55,
            R.id.let_56,
            R.id.let_57,
            R.id.let_58,
            R.id.let_59,
            R.id.let_60,
            R.id.let_5_1,
            R.id.let_5_2,
            R.id.let_61,
            R.id.let_62,
            R.id.let_63,
            R.id.let_64,
            R.id.let_65,
            R.id.let_66,
            R.id.let_67,
            R.id.let_68,
            R.id.let_69,
            R.id.let_70,
            R.id.let_6_1,
            R.id.let_6_2,
            R.id.let_71,
            R.id.let_72,
            R.id.let_73,
            R.id.let_74,
            R.id.let_75,
            R.id.let_76,
            R.id.let_77,
            R.id.let_78,
            R.id.let_79,
            R.id.let_80,
            R.id.let_7_1,
            R.id.let_7_2,
            R.id.let_81,
            R.id.let_82,
            R.id.let_83,
            R.id.let_84,
            R.id.let_85,
            R.id.let_86,
            R.id.let_87,
            R.id.let_88,
            R.id.let_89,
            R.id.let_90,
            R.id.let_8_1,
            R.id.let_8_2,
            R.id.let_91,
            R.id.let_92,
            R.id.let_93,
            R.id.let_94,
            R.id.let_95,
            R.id.let_96,
            R.id.let_97,
            R.id.let_98,
            R.id.let_99,
            R.id.let_100,
            R.id.let_9_1,
            R.id.let_9_2,
            R.id.let_101,
            R.id.let_102,
            R.id.let_103,
            R.id.let_104,
            R.id.let_105,
            R.id.let_106,
            R.id.let_107,
            R.id.let_108,
            R.id.let_109,
            R.id.let_110,
            R.id.let_10_1,
            R.id.let_10_2,
            R.id.let_111,
            R.id.let_112,
            R.id.let_113,
            R.id.let_114,
            R.id.let_115,
            R.id.let_116,
            R.id.let_117,
            R.id.let_118,
            R.id.let_119,
            R.id.let_120,
            R.id.let_11_1,
            R.id.let_11_2,
            R.id.let_121,
            R.id.let_122,
            R.id.let_123,
            R.id.let_124,
            R.id.let_125,
            R.id.let_126,
            R.id.let_127,
            R.id.let_128,
            R.id.let_129,
            R.id.let_130,
            R.id.let_12_1,
            R.id.let_12_2,
            R.id.let_131,
            R.id.let_132,
            R.id.let_133,
            R.id.let_134,
            R.id.let_135,
            R.id.let_136,
            R.id.let_137,
            R.id.let_138,
            R.id.let_139,
            R.id.let_140,
            R.id.let_13_1,
            R.id.let_13_2,
            R.id.let_141,
            R.id.let_142,
            R.id.let_143,
            R.id.let_144,
            R.id.let_145,
            R.id.let_146,
            R.id.let_147,
            R.id.let_148,
            R.id.let_149,
            R.id.let_150,
            R.id.let_14_1,
            R.id.let_14_2
    };

    boolean[] isCancel = new boolean[180];

    TextView[] submit_word = new TextView[7];
    int[] s_ids = {
            R.id.submit_1,
            R.id.submit_2,
            R.id.submit_3,
            R.id.submit_4,
            R.id.submit_5,
            R.id.submit_6,
            R.id.submit_7,
    };

    char[] vowels = {};
    char[] consonants = {
            'z', 'x', 'q', 'u', 'y', 'j',

            'k', 'w',

            'v',
            'v',

            't', 'p', 'f', 'h', 'g',
            't', 'p', 'f', 'h', 'g',
            't', 'p', 'f', 'h', 'g',

            'r', 'l', 's', 'd',
            'r', 'l', 's', 'd',
            'r', 'l', 's', 'd',
            'r', 'l', 's', 'd',
            'c', 'b', 'n', 'm',
            'c', 'b', 'n', 'm',
            'c', 'b', 'n', 'm',
            'c', 'b', 'n', 'm',

            'a', 'i',
            'a', 'i',
            'a', 'i',
            'a', 'i',
            'a', 'i',

            'e', 'o',
            'e', 'o',
            'e', 'o',
            'e', 'o',
            'e', 'o',
            'e', 'o'};

    String[] letter_set = new String[180];
    Statistics stats;
    Button test_btn;

    boolean is_new = false;
    String[] storage = new String[365];

    TextView submit_btn, submit_counter;

    int current_letter = 0;

    int[] order = new int[7];

    TextView rules,
            review_btn;
//            buy_btn;
    ConstraintLayout rules_container;


    //    private FirebaseAnalytics mFirebaseAnalytics;
//    private InterstitialAd mInterstitialAd;
    int currentGame = 0;

    TextView share_btn;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        let_container = findViewById(R.id.let_container);
        share_btn = findViewById(R.id.share_btn);
        test_btn = findViewById(R.id.test_btn);
        submit_btn = findViewById(R.id.submit_btn);
        submit_counter = findViewById(R.id.submit_counter);

        rules_container = findViewById(R.id.rules_container);
//        buy_btn = findViewById(R.id.buy_btn);
        review_btn = findViewById(R.id.review_btn);
        rules = findViewById(R.id.rules);

//        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Date date = new Date();
        final SimpleDateFormat dtf = new SimpleDateFormat("MMM dd yyyy HH:mm", Locale.ENGLISH);
        Date baseTime = new Date();
        try {
            baseTime = dtf.parse("Mar 18 2023 00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        float fl = (date.getTime() - baseTime.getTime());
        currentGame = (int) (fl / 86400000);

        MobileAds.initialize(this, initializationStatus -> {
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Arrays.fill(isCancel, false);

        for (int i = 0; i < letters.length; i++) {
            letters[i] = findViewById(letter_ids[i]);
            letters[i].setEnabled(true);
            letters[i].setTextColor(getColor(R.color.white));
        }

        for (int i = 0; i < submit_word.length; i++) {
            submit_word[i] = findViewById(s_ids[i]);
        }

        rules.setOnClickListener(view -> {
            rules_container.setVisibility(View.VISIBLE);
        });

        rules_container.setOnClickListener(view -> {
            rules_container.setVisibility(View.GONE);
        });

        review_btn.setOnClickListener(view -> {
            String url = "https://play.google.com/store/apps/details?id=com.daniel.letris";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });

//        buy_btn.setOnClickListener(view -> {
//            //TODO open Google Play
//            String url = "https://play.google.com/store/apps/details?id=com.daniel.findstar";
//            Intent i = new Intent(Intent.ACTION_VIEW);
//            i.setData(Uri.parse(url));
//            startActivity(i);
//        });

        share_btn.setOnClickListener(v -> {
            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            String body = "https://play.google.com/store/apps/details?id=com.daniel.letris";
            myIntent.putExtra(Intent.EXTRA_TEXT, body);
            startActivity(Intent.createChooser(myIntent, "Letris"));
        });

        @SuppressLint("StaticFieldLeak")
        class GetTask extends AsyncTask<Void, Void, List<Statistics>> {
            @Override
            protected List<Statistics> doInBackground(Void... voids) {
                return StatDatabase.getDatabase(getApplicationContext()).statDao().getAll();
            }

            @Override
            protected void onPostExecute(List<Statistics> stats_arr) {
                super.onPostExecute(stats_arr);
                if (!stats_arr.isEmpty()) {
                    stats = stats_arr.get(0);
                    is_new = false;
                } else {
                    stats = new Statistics();
                    is_new = true;
                }
                if (stats.getGames_played() != currentGame) {
                    stats.setGames_played(currentGame);
                    stats.setWords_number(0);
                    String str = "";

                    createLineEN();

                    for (int i = 0; i < letters.length; i++) {
                        letters[i].setText(String.valueOf(stats.getLetter_storage().charAt(i)));
                    }
                    @SuppressLint("StaticFieldLeak")
                    class NextTask extends AsyncTask<Void, Void, Void> {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            if (is_new) {
                                StatDatabase.getDatabase(getApplicationContext()).statDao().insertStat(stats);
                            } else {
                                StatDatabase.getDatabase(getApplicationContext()).statDao().changeStat(stats);
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void unused) {
                            super.onPostExecute(unused);
                        }
                    }
                    new NextTask().execute();
                } else {
                    for (int i = 0; i < letters.length; i++) {
                        letters[i].setText(String.valueOf(stats.getLetter_storage().charAt(i)));
                    }
                }
                int i = stats.getWords_number();
                if (i >= 0 && i <= 9) {
                    submit_counter.setText("000" + i);
                } else if (i >= 10 && i <= 99) {
                    submit_counter.setText("00" + i);
                } else if (i >= 100 && i <= 999){
                    submit_counter.setText("0" + i);
                } else {
                    submit_counter.setText("" + i);
                }
            }
        }
        new GetTask().execute();

        submit_btn.setOnClickListener(view -> {
            if (current_letter > 2) {
                String word = "";
                for (int i = 0; i < submit_word.length; i++) {
                    word += submit_word[i].getText().toString();
                }

                if (checkWord(word)) {

                    for (int i = 0; i < letters.length; i++) {
                        letters[i].setEnabled(true);
                        letters[i].setTextColor(getColor(R.color.white));
                    }
                    for (int i = 0; i < submit_word.length; i++) {
                        submit_word[i].setText("");
                    }

                    for (int j = 0; j < current_letter; j++) {
                        letters[order[j]].setTextColor(Color.parseColor("#FFEA00"));
                    }

                    for (int i = 0; i < letters.length; i++) {
                        letters[i].setEnabled(false);
                    }

                    Handler h1 = new Handler();
                    h1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            for (int j = 0; j < current_letter; j++) {
                                letters[order[j]].setText("");
                            }
                            int[] ord_copy = order.clone();
                            Arrays.sort(ord_copy);
                            for (int i = 0; i < current_letter; i++) {
                                int lastL = ord_copy[ord_copy.length - 1 - i];
                                if (letters[lastL].getText().toString().trim().isEmpty()) {
                                    try {
                                        for (int j = 0; j < 15; j++) {
                                            if (!letters[lastL - 12].getText().toString().isEmpty() && letters[lastL - 12].getAnimation() == null) {
                                                TranslateAnimation animation = new TranslateAnimation(
                                                        Animation.RELATIVE_TO_SELF, 0f,
                                                        Animation.RELATIVE_TO_SELF, 0f,
                                                        Animation.RELATIVE_TO_SELF, 0f,
                                                        Animation.RELATIVE_TO_SELF, 1f);
                                                animation.setDuration(700); // duration in milliseconds
                                                letters[lastL - 12].startAnimation(animation);
                                            } else if (!letters[lastL - 24].getText().toString().isEmpty() && letters[lastL - 24].getAnimation() == null) {
                                                TranslateAnimation animation = new TranslateAnimation(
                                                        Animation.RELATIVE_TO_SELF, 0f,
                                                        Animation.RELATIVE_TO_SELF, 0f,
                                                        Animation.RELATIVE_TO_SELF, 0f,
                                                        Animation.RELATIVE_TO_SELF, 2f);
                                                animation.setDuration(700); // duration in milliseconds
                                                letters[lastL - 24].startAnimation(animation);
                                            } else if (!letters[lastL - 36].getText().toString().isEmpty() && letters[lastL - 36].getAnimation() == null) {
                                                TranslateAnimation animation = new TranslateAnimation(
                                                        Animation.RELATIVE_TO_SELF, 0f,
                                                        Animation.RELATIVE_TO_SELF, 0f,
                                                        Animation.RELATIVE_TO_SELF, 0f,
                                                        Animation.RELATIVE_TO_SELF, 3f);
                                                animation.setDuration(700); // duration in milliseconds
                                                letters[lastL - 36].startAnimation(animation);
                                            } else if (!letters[lastL - 48].getText().toString().isEmpty() && letters[lastL - 48].getAnimation() == null) {
                                                TranslateAnimation animation = new TranslateAnimation(
                                                        Animation.RELATIVE_TO_SELF, 0f,
                                                        Animation.RELATIVE_TO_SELF, 0f,
                                                        Animation.RELATIVE_TO_SELF, 0f,
                                                        Animation.RELATIVE_TO_SELF, 4f);
                                                animation.setDuration(700); // duration in milliseconds
                                                letters[lastL - 48].startAnimation(animation);
                                            } else if (!letters[lastL - 60].getText().toString().isEmpty() && letters[lastL - 60].getAnimation() == null) {
                                                TranslateAnimation animation = new TranslateAnimation(
                                                        Animation.RELATIVE_TO_SELF, 0f,
                                                        Animation.RELATIVE_TO_SELF, 0f,
                                                        Animation.RELATIVE_TO_SELF, 0f,
                                                        Animation.RELATIVE_TO_SELF, 5f);
                                                animation.setDuration(700); // duration in milliseconds
                                                letters[lastL - 60].startAnimation(animation);
                                            } else if (!letters[lastL - 72].getText().toString().isEmpty() && letters[lastL - 72].getAnimation() == null) {
                                                TranslateAnimation animation = new TranslateAnimation(
                                                        Animation.RELATIVE_TO_SELF, 0f,
                                                        Animation.RELATIVE_TO_SELF, 0f,
                                                        Animation.RELATIVE_TO_SELF, 0f,
                                                        Animation.RELATIVE_TO_SELF, 6f);
                                                animation.setDuration(700); // duration in milliseconds
                                                letters[lastL - 72].startAnimation(animation);
                                            }

                                            lastL -= 12;
                                        }
                                    } catch (Exception e) {
                                    }
                                }
                            }
                        }
                    }, 500);

                    Handler handler = new Handler();
                    String finalWord = word;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            for (int j = 0; j < current_letter; j++) {
                                letters[order[j]].setText("");
                                letters[order[j]].setTextColor(getColor(R.color.white));
                            }
                            dropLetters();
                            current_letter = 0;
                            Arrays.fill(isCancel, false);
                            Arrays.fill(order, 0);

                            int i = Integer.parseInt(String.valueOf(submit_counter.getText())) + finalWord.length();
                            if (i >= 0 && i <= 9) {
                                submit_counter.setText("000" + i);
                            } else if (i >= 10 && i <= 99) {
                                submit_counter.setText("00" + i);
                            } else if (i >= 100 && i <= 999){
                                submit_counter.setText("0" + i);
                            } else {
                                submit_counter.setText("" + i);
                            }
                            stats.setWords_number(Integer.parseInt(String.valueOf(submit_counter.getText())));
//                        Bundle bundle = new Bundle();
//                        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, stats.getGames_played() + ":" + stats.getWords_number());
//                        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Word");
//                        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "text");
//                        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

                            @SuppressLint("StaticFieldLeak")
                            class NextTask extends AsyncTask<Void, Void, Void> {
                                @Override
                                protected Void doInBackground(Void... voids) {
                                    if (is_new) {
                                        StatDatabase.getDatabase(getApplicationContext()).statDao().insertStat(stats);
                                    } else {
                                        StatDatabase.getDatabase(getApplicationContext()).statDao().changeStat(stats);
                                    }
                                    return null;
                                }

                                @Override
                                protected void onPostExecute(Void unused) {
                                    super.onPostExecute(unused);
                                }
                            }
                            new NextTask().execute();
                        }
                    }, 1220);
                } else {
                    for (int i = 0; i < letters.length; i++) {
                        letters[i].setEnabled(true);
                        letters[i].setTextColor(getColor(R.color.white));
                    }
                    for (int i = 0; i < submit_word.length; i++) {
                        submit_word[i].setText("");
                    }
                    current_letter = 0;
                    Arrays.fill(isCancel, false);
                    Arrays.fill(order, 0);
                    Toast.makeText(this, "Not eligible word", Toast.LENGTH_SHORT).show();
                }

            }
        });

        for (int i = 0; i < letters.length; i++) {
            int finalI = i;
            letters[i].setOnClickListener(view -> {
                if (isCancel[finalI]) {
                    if (current_letter >= 2) {
                        for (int j = 0; j < letters.length; j++) {
                            if (letters[j].getCurrentTextColor() != getColor(R.color.blue)) {
                                letters[j].setTextColor(getColor(R.color.white));
                            }
                        }
                        letters[finalI].setTextColor(getColor(R.color.white));

                        int order_int = current_letter - 2;

                        current_letter -= 2;

                        letters[order[order_int]].setEnabled(true);
                        letters[order[order_int]].performClick();

                        submit_word[current_letter].setText("");
                    } else {
                        for (int j = 0; j < letters.length; j++) {
                            letters[j].setEnabled(true);
                            letters[j].setTextColor(getColor(R.color.white));
                        }
                        for (int j = 0; j < submit_word.length; j++) {
                            submit_word[j].setText("");
                        }
                        current_letter = 0;
                        Arrays.fill(isCancel, false);
                        Arrays.fill(order, 0);
                    }
                    return;
                }
                if (current_letter < 7) {
                    for (int j = 0; j < letters.length; j++) {
                        letters[j].setEnabled(false);
                        if (letters[j].getCurrentTextColor() == getColor(R.color.green)) {
                            letters[j].setTextColor(getColor(R.color.white));
                        }
                    }
                    letters[finalI].setTextColor(getColor(R.color.blue));
                    if (current_letter < 6) {
                        if (finalI % 12 == 11) {
                            if (finalI == 11) {
                                try {
                                    if (letters[finalI - 1].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI - 1].setTextColor(getColor(R.color.green));
                                        letters[finalI - 1].setEnabled(true);
                                    }
                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI + 11].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI + 11].setTextColor(getColor(R.color.green));
                                        letters[finalI + 11].setEnabled(true);
                                    }

                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI + 12].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI + 12].setTextColor(getColor(R.color.green));
                                        letters[finalI + 12].setEnabled(true);
                                    }
                                } catch (Exception ignored) {
                                }
                            }
//                            else if (finalI == 143) {
//                                try {
//                                    if (letters[finalI - 13].getCurrentTextColor() != getColor(R.color.blue)) {
//                                        letters[finalI - 13].setTextColor(getColor(R.color.green));
//                                        letters[finalI - 13].setEnabled(true);
//                                    }
//                                } catch (Exception ignored) {
//                                }
//                                try {
//                                    if (letters[finalI - 12].getCurrentTextColor() != getColor(R.color.blue)) {
//                                        letters[finalI - 12].setTextColor(getColor(R.color.green));
//                                        letters[finalI - 12].setEnabled(true);
//                                    }
//                                } catch (Exception ignored) {
//                                }
//                                try {
//                                    if (letters[finalI - 1].getCurrentTextColor() != getColor(R.color.blue)) {
//                                        letters[finalI - 1].setTextColor(getColor(R.color.green));
//                                        letters[finalI - 1].setEnabled(true);
//                                    }
//                                } catch (Exception ignored) {
//                                }
//                            }
                            else {
                                try {
                                    if (letters[finalI - 13].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI - 13].setTextColor(getColor(R.color.green));
                                        letters[finalI - 13].setEnabled(true);
                                    }
                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI - 12].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI - 12].setTextColor(getColor(R.color.green));
                                        letters[finalI - 12].setEnabled(true);
                                    }
                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI - 1].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI - 1].setTextColor(getColor(R.color.green));
                                        letters[finalI - 1].setEnabled(true);
                                    }
                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI + 11].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI + 11].setTextColor(getColor(R.color.green));
                                        letters[finalI + 11].setEnabled(true);
                                    }

                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI + 12].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI + 12].setTextColor(getColor(R.color.green));
                                        letters[finalI + 12].setEnabled(true);
                                    }

                                } catch (Exception ignored) {
                                }
                            }
                        } else if (finalI % 12 == 0) {
                            if (finalI == 0) {
                                try {
                                    if (letters[finalI + 1].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI + 1].setTextColor(getColor(R.color.green));
                                        letters[finalI + 1].setEnabled(true);
                                    }
                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI + 12].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI + 12].setTextColor(getColor(R.color.green));
                                        letters[finalI + 12].setEnabled(true);
                                    }

                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI + 13].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI + 13].setTextColor(getColor(R.color.green));
                                        letters[finalI + 13].setEnabled(true);
                                    }

                                } catch (Exception ignored) {
                                }
                            }
//                            else if (finalI == 132) {
//                                try {
//                                    if (letters[finalI - 12].getCurrentTextColor() != getColor(R.color.blue)) {
//                                        letters[finalI - 12].setTextColor(getColor(R.color.green));
//                                        letters[finalI - 12].setEnabled(true);
//                                    }
//                                } catch (Exception ignored) {
//                                }
//                                try {
//                                    if (letters[finalI - 11].getCurrentTextColor() != getColor(R.color.blue)) {
//                                        letters[finalI - 11].setTextColor(getColor(R.color.green));
//                                        letters[finalI - 11].setEnabled(true);
//                                    }
//                                } catch (Exception ignored) {
//                                }
//                                try {
//                                    if (letters[finalI + 1].getCurrentTextColor() != getColor(R.color.blue)) {
//                                        letters[finalI + 1].setTextColor(getColor(R.color.green));
//                                        letters[finalI + 1].setEnabled(true);
//                                    }
//                                } catch (Exception ignored) {
//                                }
//                            }
                            else {
                                try {
                                    if (letters[finalI - 11].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI - 11].setTextColor(getColor(R.color.green));
                                        letters[finalI - 11].setEnabled(true);
                                    }
                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI - 12].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI - 12].setTextColor(getColor(R.color.green));
                                        letters[finalI - 12].setEnabled(true);
                                    }
                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI + 1].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI + 1].setTextColor(getColor(R.color.green));
                                        letters[finalI + 1].setEnabled(true);
                                    }
                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI + 12].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI + 12].setTextColor(getColor(R.color.green));
                                        letters[finalI + 12].setEnabled(true);
                                    }

                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI + 13].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI + 13].setTextColor(getColor(R.color.green));
                                        letters[finalI + 13].setEnabled(true);
                                    }

                                } catch (Exception ignored) {
                                }
                            }
                        } else {
                            try {
                                if (letters[finalI - 13].getCurrentTextColor() != getColor(R.color.blue)) {
                                    letters[finalI - 13].setTextColor(getColor(R.color.green));
                                    letters[finalI - 13].setEnabled(true);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI - 12].getCurrentTextColor() != getColor(R.color.blue)) {
                                    letters[finalI - 12].setTextColor(getColor(R.color.green));
                                    letters[finalI - 12].setEnabled(true);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI - 11].getCurrentTextColor() != getColor(R.color.blue)) {
                                    letters[finalI - 11].setTextColor(getColor(R.color.green));
                                    letters[finalI - 11].setEnabled(true);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI - 1].getCurrentTextColor() != getColor(R.color.blue)) {
                                    letters[finalI - 1].setTextColor(getColor(R.color.green));
                                    letters[finalI - 1].setEnabled(true);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 1].getCurrentTextColor() != getColor(R.color.blue)) {
                                    letters[finalI + 1].setTextColor(getColor(R.color.green));
                                    letters[finalI + 1].setEnabled(true);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 11].getCurrentTextColor() != getColor(R.color.blue)) {
                                    letters[finalI + 11].setTextColor(getColor(R.color.green));
                                    letters[finalI + 11].setEnabled(true);
                                }

                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 12].getCurrentTextColor() != getColor(R.color.blue)) {
                                    letters[finalI + 12].setTextColor(getColor(R.color.green));
                                    letters[finalI + 12].setEnabled(true);
                                }

                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 13].getCurrentTextColor() != getColor(R.color.blue)) {
                                    letters[finalI + 13].setTextColor(getColor(R.color.green));
                                    letters[finalI + 13].setEnabled(true);
                                }
                            } catch (Exception ignored) {
                            }
                        }
                    }

                    submit_word[current_letter].setText(letters[finalI].getText().toString());
                    order[current_letter] = finalI;
                    current_letter++;
                    letters[finalI].setEnabled(true);
                    for (int j = 0; j < isCancel.length; j++) {
                        isCancel[j] = false;
                    }
                    isCancel[finalI] = true;
                }
            });
        }

//        for (int i = 0; i < 3; i++) {
//            storage[i] = "";
//            for (int j = 0; j < letter_set.length; j++) {
//                char[] chars = new char[10];
//                Random r = new Random();
//                for (int k = 0; k < chars.length; k++) {
////                    if (k < 6){
//                        chars[k] = consonants[r.nextInt(consonants.length)];
////                    } else {
////                        chars[k] = vowels[r.nextInt(vowels.length)];
////                    }
//                }
//                String str = String.valueOf(chars[r.nextInt(chars.length)]);
//                letter_set[j] = str;
//                storage[i] += str;
//            }
//        }
//        String s = "";
    }

    private boolean checkWord(String currWord) {
        boolean does_exist = false;
        switch (currWord.length()) {
            case 3:
                for (int i = 0; i < EnglishVoc.three_vocab.length; i++) {
                    if (EnglishVoc.three_vocab[i].equals(currWord)) {
                        does_exist = true;
                        break;
                    }
                }
                break;

            case 4:
                for (int i = 0; i < EnglishVoc.four_vocab.length; i++) {
                    if (EnglishVoc.four_vocab[i].equals(currWord)) {
                        does_exist = true;
                        break;
                    }
                }
                break;

            case 5:
                for (int i = 0; i < EnglishVoc.five_vocab.length; i++) {
                    if (EnglishVoc.five_vocab[i].equals(currWord)) {
                        does_exist = true;
                        break;
                    }
                }
                break;
            case 6:
                if (currWord.charAt(0) > 'l') {
                    for (int i = 0; i < EnglishVoc.six_vocab_p2.length; i++) {
                        if (EnglishVoc.six_vocab_p2[i].equals(currWord)) {
                            does_exist = true;
                            break;
                        }
                    }
                } else {
                    for (int i = 0; i < EnglishVoc.six_vocab_p1.length; i++) {
                        if (EnglishVoc.six_vocab_p1[i].equals(currWord)) {
                            does_exist = true;
                            break;
                        }
                    }
                }
                break;
            case 7:
                for (int i = 0; i < EnglishVoc.seven_vocab_p1.length; i++) {
                    if (EnglishVoc.seven_vocab_p1[i].equals(currWord)) {
                        does_exist = true;
                        break;
                    }
                }
                for (int i = 0; i < EnglishVoc.seven_vocab_p2.length; i++) {
                    if (EnglishVoc.seven_vocab_p2[i].equals(currWord)) {
                        does_exist = true;
                        break;
                    }
                }
                for (int i = 0; i < EnglishVoc.seven_vocab_p3.length; i++) {
                    if (EnglishVoc.seven_vocab_p3[i].equals(currWord)) {
                        does_exist = true;
                        break;
                    }
                }
                break;
        }
        return does_exist;
    }

    @Override
    public void onBackPressed() {
        if (rules_container.getVisibility() == View.VISIBLE) {
            rules_container.setVisibility(View.GONE);
        }
    }

    private void dropLetters() {
        int lastL = 0;
        for (int k : order) {
            if (lastL < k) {
                lastL = k;
            }
        }
        Arrays.sort(order);
        for (int i = 0; i < current_letter; i++) {
            lastL = order[order.length - 1 - i];
            if (letters[lastL].getText().toString().isEmpty()) {
                try {
                    for (int j = 0; j < 15; j++) {
                        if (!letters[lastL - 12].getText().toString().isEmpty()) {
                            letters[lastL].setText(letters[lastL - 12].getText());
                            letters[lastL - 12].setText("");
                        } else if (!letters[lastL - 24].getText().toString().isEmpty()) {
                            letters[lastL].setText(letters[lastL - 24].getText());
                            letters[lastL - 24].setText("");
                        } else if (!letters[lastL - 36].getText().toString().isEmpty()) {
                            letters[lastL].setText(letters[lastL - 36].getText());
                            letters[lastL - 36].setText("");
                        } else if (!letters[lastL - 48].getText().toString().isEmpty()) {
                            letters[lastL].setText(letters[lastL - 48].getText());
                            letters[lastL - 48].setText("");
                        } else if (!letters[lastL - 60].getText().toString().isEmpty()) {
                            letters[lastL].setText(letters[lastL - 60].getText());
                            letters[lastL - 60].setText("");
                        } else if (!letters[lastL - 72].getText().toString().isEmpty()) {
                            letters[lastL].setText(letters[lastL - 72].getText());
                            letters[lastL - 72].setText("");
                        }

                        lastL -= 12;
                    }
                } catch (Exception e) {
                }
            }
        }

        for (int i = 0; i < letters.length; i++) {
            if (letters[i].getText().toString().trim().isEmpty()) {
                char[] chars = new char[10];
                Random r = new Random();
                for (int k = 0; k < chars.length; k++) {
                    chars[k] = consonants[r.nextInt(consonants.length)];
                }
                String str = String.valueOf(chars[r.nextInt(chars.length)]);
                letters[i].setText(str);
            }
        }

        String save = "";
        for (int i = 0; i < letters.length; i++) {
            save += letters[i].getText().toString();
        }
        stats.setLetter_storage(save);

        for (int i = 0; i < letters.length; i++) {
            letters[i].setTextColor(getColor(R.color.white));
        }
        for (int i = 0; i < letters.length; i++) {
            letters[i].setEnabled(true);
        }

        @SuppressLint("StaticFieldLeak")
        class NextTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                if (is_new) {
                    StatDatabase.getDatabase(getApplicationContext()).statDao().insertStat(stats);
                } else {
                    StatDatabase.getDatabase(getApplicationContext()).statDao().changeStat(stats);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
            }
        }
        new NextTask().execute();
    }

    private void createLineEN() {
        char[] abc = {
                'z', 'x', 'q', 'u', 'y', 'j',

                'k', 'w',
                'k', 'w',

                't', 'p',
                't', 'p',
                't', 'p',

                'r', 'l', 's', 'd', 'f', 'h',
                'r', 'l', 's', 'd', 'f', 'h',
                'r', 'l', 's', 'd', 'f', 'h',
                'r', 'l', 's', 'd', 'f', 'h',
                'c', 'v', 'b', 'n', 'm', 'g',
                'c', 'v', 'b', 'n', 'm', 'g',
                'c', 'v', 'b', 'n', 'm', 'g',
                'c', 'v', 'b', 'n', 'm', 'g',

                'a', 'i',
                'a', 'i',
                'a', 'i',
                'a', 'i',
                'a', 'i',

                'e', 'o',
                'e', 'o',
                'e', 'o',
                'e', 'o',
                'e', 'o',
                'e', 'o'};

        for (int i = 0; i < letters.length; i++) {
            letters[i].setText("");
        }

        String[] six_l = new String[5];
        String[] five_l = new String[14];
        Random r = new Random();

        for (int i = 0; i < six_l.length; i++) {
            String[] s = {
                    EnglishVoc.six_vocab_p1[r.nextInt(EnglishVoc.six_vocab_p1.length)],
                    EnglishVoc.six_vocab_p2[r.nextInt(EnglishVoc.six_vocab_p2.length)]
            };
            while (Character.isUpperCase(s[0].charAt(0))) {
                s[0] = EnglishVoc.six_vocab_p1[r.nextInt(EnglishVoc.six_vocab_p1.length)];
            }
            while (Character.isUpperCase(s[1].charAt(0))) {
                s[1] = EnglishVoc.six_vocab_p2[r.nextInt(EnglishVoc.six_vocab_p2.length)];
            }
            six_l[i] = s[r.nextInt(s.length)];
        }
        for (int i = 0; i < five_l.length; i++) {
            String[] s = {
                    EnglishVoc.five_vocab[r.nextInt(EnglishVoc.five_vocab.length)],
            };
            while (Character.isUpperCase(s[0].charAt(0))) {
                s[0] = EnglishVoc.five_vocab[r.nextInt(EnglishVoc.five_vocab.length)];
            }
            five_l[i] = s[r.nextInt(s.length)];
        }

        for (int i = 0; i < six_l.length; i++) {
            Log.e("tag", six_l[i] + "");
        }
        for (int i = 0; i < five_l.length; i++) {
            Log.e("tag", five_l[i] + "");
        }


        for (int i = 0; i < six_l.length; i++) {
            int finalI = 0;
            String[] available = new String[8];
            for (int j = 0; j < 6; j++) {
                if (j == 0) {
                    finalI = r.nextInt(letters.length);
                    TextView test = letters[finalI];
                    while (!test.getText().toString().isEmpty()) {
                        finalI = r.nextInt(letters.length);
                        test = letters[finalI];
                    }
                    test.setText(six_l[i].charAt(j) + "");
                } else {
                    if (finalI % 12 == 11) {
                        if (finalI == 11) {
                            try {
                                if (letters[finalI - 1].getText().toString().isEmpty()) {
                                    available[3] = String.valueOf(finalI - 1);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 11].getText().toString().isEmpty()) {
                                    available[5] = String.valueOf(finalI + 11);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 12].getText().toString().isEmpty()) {
                                    available[6] = String.valueOf(finalI + 12);
                                }
                            } catch (Exception ignored) {
                            }
                        } else if (finalI == 143) {
                            try {
                                if (letters[finalI - 13].getText().toString().isEmpty()) {
                                    available[0] = String.valueOf(finalI - 13);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI - 12].getText().toString().isEmpty()) {
                                    available[1] = String.valueOf(finalI - 12);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI - 1].getText().toString().isEmpty()) {
                                    available[3] = String.valueOf(finalI - 1);
                                }
                            } catch (Exception ignored) {
                            }
                        } else {
                            try {
                                if (letters[finalI - 13].getText().toString().isEmpty()) {
                                    available[0] = String.valueOf(finalI - 13);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI - 12].getText().toString().isEmpty()) {
                                    available[1] = String.valueOf(finalI - 12);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI - 1].getText().toString().isEmpty()) {
                                    available[3] = String.valueOf(finalI - 1);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 11].getText().toString().isEmpty()) {
                                    available[5] = String.valueOf(finalI + 11);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 12].getText().toString().isEmpty()) {
                                    available[6] = String.valueOf(finalI + 12);
                                }
                            } catch (Exception ignored) {
                            }
                        }
                    } else if (finalI % 12 == 0) {
                        if (finalI == 0) {
                            try {
                                if (letters[finalI + 1].getText().toString().isEmpty()) {
                                    available[4] = String.valueOf(finalI + 1);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 12].getText().toString().isEmpty()) {
                                    available[6] = String.valueOf(finalI + 12);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 13].getText().toString().isEmpty()) {
                                    available[7] = String.valueOf(finalI + 13);
                                }
                            } catch (Exception ignored) {
                            }
                        } else if (finalI == 132) {
                            try {
                                if (letters[finalI - 12].getText().toString().isEmpty()) {
                                    available[1] = String.valueOf(finalI - 12);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI - 11].getText().toString().isEmpty()) {
                                    available[2] = String.valueOf(finalI - 11);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 1].getText().toString().isEmpty()) {
                                    available[4] = String.valueOf(finalI + 1);
                                }
                            } catch (Exception ignored) {
                            }
                        } else {
                            try {
                                if (letters[finalI - 11].getText().toString().isEmpty()) {
                                    available[2] = String.valueOf(finalI - 11);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI - 12].getText().toString().isEmpty()) {
                                    available[1] = String.valueOf(finalI - 12);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 1].getText().toString().isEmpty()) {
                                    available[4] = String.valueOf(finalI + 1);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 12].getText().toString().isEmpty()) {
                                    available[6] = String.valueOf(finalI + 12);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 13].getText().toString().isEmpty()) {
                                    available[7] = String.valueOf(finalI + 13);
                                }
                            } catch (Exception ignored) {
                            }
                        }
                    } else {
                        try {
                            if (letters[finalI - 13].getText().toString().isEmpty()) {
                                available[0] = String.valueOf(finalI - 13);
                            }
                        } catch (Exception ignored) {
                        }
                        try {
                            if (letters[finalI - 12].getText().toString().isEmpty()) {
                                available[1] = String.valueOf(finalI - 12);
                            }
                        } catch (Exception ignored) {
                        }
                        try {
                            if (letters[finalI - 11].getText().toString().isEmpty()) {
                                available[2] = String.valueOf(finalI - 11);
                            }
                        } catch (Exception ignored) {
                        }
                        try {
                            if (letters[finalI - 1].getText().toString().isEmpty()) {
                                available[3] = String.valueOf(finalI - 1);
                            }
                        } catch (Exception ignored) {
                        }
                        try {
                            if (letters[finalI + 1].getText().toString().isEmpty()) {
                                available[4] = String.valueOf(finalI + 1);
                            }
                        } catch (Exception ignored) {
                        }
                        try {
                            if (letters[finalI + 11].getText().toString().isEmpty()) {
                                available[5] = String.valueOf(finalI + 11);
                            }
                        } catch (Exception ignored) {
                        }
                        try {
                            if (letters[finalI + 12].getText().toString().isEmpty()) {
                                available[6] = String.valueOf(finalI + 12);
                            }
                        } catch (Exception ignored) {
                        }
                        try {
                            if (letters[finalI + 13].getText().toString().isEmpty()) {
                                available[7] = String.valueOf(finalI + 13);
                            }
                        } catch (Exception ignored) {
                        }
                    }
                    int random = r.nextInt(available.length);
                    while (available[random] == null) {
                        random = r.nextInt(available.length);
                        if (areAllElementsNull(available)) {
                            break;
                        }
                    }
                    try {
                        letters[Integer.parseInt(available[random])].setText(six_l[i].charAt(j) + "");
                    } catch (Exception e) {
                        createLineEN();
                        return;
                    }
                    finalI = Integer.parseInt(available[random]);
                    Arrays.fill(available, null);
                }
            }
            String s = "s";
        }

        for (int i = 0; i < five_l.length; i++) {
            int finalI = 0;
            String[] available = new String[8];
            for (int j = 0; j < 5; j++) {
                if (j == 0) {
                    finalI = r.nextInt(letters.length);
                    TextView test = letters[finalI];
                    while (!test.getText().toString().isEmpty()) {
                        finalI = r.nextInt(letters.length);
                        test = letters[finalI];
                    }
                    test.setText(five_l[i].charAt(j) + "");
                } else {
                    if (finalI % 12 == 11) {
                        if (finalI == 11) {
                            try {
                                if (letters[finalI - 1].getText().toString().isEmpty()) {
                                    available[3] = String.valueOf(finalI - 1);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 11].getText().toString().isEmpty()) {
                                    available[5] = String.valueOf(finalI + 11);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 12].getText().toString().isEmpty()) {
                                    available[6] = String.valueOf(finalI + 12);
                                }
                            } catch (Exception ignored) {
                            }
                        } else if (finalI == 143) {
                            try {
                                if (letters[finalI - 13].getText().toString().isEmpty()) {
                                    available[0] = String.valueOf(finalI - 13);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI - 12].getText().toString().isEmpty()) {
                                    available[1] = String.valueOf(finalI - 12);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI - 1].getText().toString().isEmpty()) {
                                    available[3] = String.valueOf(finalI - 1);
                                }
                            } catch (Exception ignored) {
                            }
                        } else {
                            try {
                                if (letters[finalI - 13].getText().toString().isEmpty()) {
                                    available[0] = String.valueOf(finalI - 13);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI - 12].getText().toString().isEmpty()) {
                                    available[1] = String.valueOf(finalI - 12);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI - 1].getText().toString().isEmpty()) {
                                    available[3] = String.valueOf(finalI - 1);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 11].getText().toString().isEmpty()) {
                                    available[5] = String.valueOf(finalI + 11);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 12].getText().toString().isEmpty()) {
                                    available[6] = String.valueOf(finalI + 12);
                                }
                            } catch (Exception ignored) {
                            }
                        }
                    } else if (finalI % 12 == 0) {
                        if (finalI == 0) {
                            try {
                                if (letters[finalI + 1].getText().toString().isEmpty()) {
                                    available[4] = String.valueOf(finalI + 1);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 12].getText().toString().isEmpty()) {
                                    available[6] = String.valueOf(finalI + 12);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 13].getText().toString().isEmpty()) {
                                    available[7] = String.valueOf(finalI + 13);
                                }
                            } catch (Exception ignored) {
                            }
                        } else if (finalI == 132) {
                            try {
                                if (letters[finalI - 12].getText().toString().isEmpty()) {
                                    available[1] = String.valueOf(finalI - 12);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI - 11].getText().toString().isEmpty()) {
                                    available[2] = String.valueOf(finalI - 11);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 1].getText().toString().isEmpty()) {
                                    available[4] = String.valueOf(finalI + 1);
                                }
                            } catch (Exception ignored) {
                            }
                        } else {
                            try {
                                if (letters[finalI - 11].getText().toString().isEmpty()) {
                                    available[2] = String.valueOf(finalI - 11);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI - 12].getText().toString().isEmpty()) {
                                    available[1] = String.valueOf(finalI - 12);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 1].getText().toString().isEmpty()) {
                                    available[4] = String.valueOf(finalI + 1);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 12].getText().toString().isEmpty()) {
                                    available[6] = String.valueOf(finalI + 12);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 13].getText().toString().isEmpty()) {
                                    available[7] = String.valueOf(finalI + 13);
                                }
                            } catch (Exception ignored) {
                            }
                        }
                    } else {
                        try {
                            if (letters[finalI - 13].getText().toString().isEmpty()) {
                                available[0] = String.valueOf(finalI - 13);
                            }
                        } catch (Exception ignored) {
                        }
                        try {
                            if (letters[finalI - 12].getText().toString().isEmpty()) {
                                available[1] = String.valueOf(finalI - 12);
                            }
                        } catch (Exception ignored) {
                        }
                        try {
                            if (letters[finalI - 11].getText().toString().isEmpty()) {
                                available[2] = String.valueOf(finalI - 11);
                            }
                        } catch (Exception ignored) {
                        }
                        try {
                            if (letters[finalI - 1].getText().toString().isEmpty()) {
                                available[3] = String.valueOf(finalI - 1);
                            }
                        } catch (Exception ignored) {
                        }
                        try {
                            if (letters[finalI + 1].getText().toString().isEmpty()) {
                                available[4] = String.valueOf(finalI + 1);
                            }
                        } catch (Exception ignored) {
                        }
                        try {
                            if (letters[finalI + 11].getText().toString().isEmpty()) {
                                available[5] = String.valueOf(finalI + 11);
                            }
                        } catch (Exception ignored) {
                        }
                        try {
                            if (letters[finalI + 12].getText().toString().isEmpty()) {
                                available[6] = String.valueOf(finalI + 12);
                            }
                        } catch (Exception ignored) {
                        }
                        try {
                            if (letters[finalI + 13].getText().toString().isEmpty()) {
                                available[7] = String.valueOf(finalI + 13);
                            }
                        } catch (Exception ignored) {
                        }
                    }
                    int random = r.nextInt(available.length);
                    while (available[random] == null) {
                        random = r.nextInt(available.length);
                        if (areAllElementsNull(available)) {
                            break;
                        }
                    }
                    try {
                        letters[Integer.parseInt(available[random])].setText(five_l[i].charAt(j) + "");
                    } catch (Exception e) {
                        createLineEN();
                        return;
                    }
                    finalI = Integer.parseInt(available[random]);
                    Arrays.fill(available, null);
                }
            }
            String s = "s";
        }

        for (int i = 0; i < letters.length; i++) {
            if (letters[i].getText().toString().isEmpty()) {
                letters[i].setText(abc[r.nextInt(abc.length)] + "");
            }
        }

        String str = "";
        for (int i = 0; i < letters.length; i++) {
            str += letters[i].getText().toString();
        }
//        Log.d("FINAL LINE:", str);
        stats.setLetter_storage(str);
    }

    public static boolean areAllElementsNull(String[] array) {
        for (String element : array) {
            if (element != null) {
                return false;
            }
        }
        return true;
    }
}