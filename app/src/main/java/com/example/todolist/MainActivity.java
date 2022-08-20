package com.example.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static RecyclerView toDoListRecyclerView;
    Button addToListBtn;
    EditText inputTextView;
    static ArrayList<String> toDoList;
    static MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toDoListRecyclerView = findViewById(R.id.main_activity_todolist_recycler);
        addToListBtn = findViewById(R.id.main_activity_add_btn);
        inputTextView = findViewById(R.id.main_activity_todolist_edt);
        toDoList = new ArrayList<String>();
        toDoList = loadToDoList();

        toDoListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(MainActivity.this,toDoList);
        toDoListRecyclerView.setAdapter(adapter);


        addToListBtn.setOnClickListener(view -> {
            toDoList.add(String.valueOf(inputTextView.getText()));
            updateToDoList();
            inputTextView.setText(null);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        FileHelper.writeData(toDoList,getApplicationContext());
    }

    static void updateToDoList() {
        adapter = new MyRecyclerViewAdapter(toDoListRecyclerView.getContext(), toDoList);
        toDoListRecyclerView.setAdapter(adapter);
    }

    private ArrayList<String> loadToDoList() {
        return FileHelper.readData(getApplicationContext());
    }
}