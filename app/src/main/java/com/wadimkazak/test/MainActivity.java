package com.wadimkazak.test;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SharedPreferences preferences = null;
    private boolean FLAG = true;
    ActionBar actionBar;
    private EditText search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("control", MODE_PRIVATE);

        if (preferences.getBoolean("firstrun", true)) {
            fillListOfMen(this);
            preferences.edit().putBoolean("firstrun", false).commit();
        }
        actionBar = getSupportActionBar();
        View view = LayoutInflater.from(this).inflate(R.layout.search_field, null, false);
        actionBar.setCustomView(view);
        search = actionBar.getCustomView().findViewById(R.id.etSearch);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                search(editable, MainActivity.this);
            }
        });


        recyclerView = findViewById(R.id.recyclerViewFromMainActivity);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setAdapter(Man.listAll(Man.class), this);


    }

    private void setAdapter(List<Man> mans, Context context) {
        ListAdapter listAdapter = new ListAdapter(mans, context);
        listAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(listAdapter);
    }

    private void search(Editable editable, Context context) {
        List<Man> manListBefore = Man.listAll(Man.class);
        List<Man> manListAfter = new ArrayList<>();

        for (int j = 0; j < manListBefore.size(); j++) {
            if (manListBefore.get(j).getName().contains(editable.toString())) {
                manListAfter.add(manListBefore.get(j));
                setAdapter(manListAfter, context);
            }
        }
    }


    private static void fillListOfMen(Context context) {
        String[] countOfLimbs = context.getResources().getStringArray(R.array.CountOfLimbs);
        String[] names = context.getResources().getStringArray(R.array.Names);
        String[] surnames = context.getResources().getStringArray(R.array.Surnames);
        String[] tel = context.getResources().getStringArray(R.array.Telephone_numbers);
        String[] pass = context.getResources().getStringArray(R.array.Passwords);
        for (int i = 0; i < countOfLimbs.length; i++) {
            Man man = new Man();
            man.setCountOfLimbs(countOfLimbs[i]);
            man.setName(names[i]);
            man.setSurname(surnames[i]);
            man.setTel(tel[i]);
            man.setPass(pass[i]);
            man.save();
        }
        List<Man> manList = Man.listAll(Man.class);
        Collections.sort(manList, new Comparator<Man>() {
            @Override
            public int compare(Man man, Man t1) {
                return man.getCountOfLimbs().compareTo(t1.getCountOfLimbs());
            }
        });
        Man.deleteAll(Man.class);
        Man man = new Man();
        for (int i = 0; i < manList.size(); i++) {
            man.setCountOfLimbs(manList.get(i).getCountOfLimbs());
            man.setName(manList.get(i).getName());
            man.setSurname(manList.get(i).getSurname());
            man.setTel(manList.get(i).getTel());
            man.setPass(manList.get(i).getPass());
            man.save();
            man = new Man();
        }

    }


    public void onClick(View view) {
        if (FLAG) {
            actionBar.setDisplayOptions(android.app.ActionBar.DISPLAY_SHOW_CUSTOM);
            FLAG = false;
        } else {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            search.setText("");
            setAdapter(Man.listAll(Man.class), MainActivity.this);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
            FLAG = true;
        }


    }
}
