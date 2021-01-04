package com.example.readsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private ListView listView; private  String song[]; @Override
    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);
        listView=findViewById(R.id.ll); ArrayList<File> songs=read(Environment.getExternalStorageDirectory());
        song=new String[songs.size()];
        for (int i=0;i<songs.size();i++){ song[i]=songs.get(i).getName().toString().replace(".mp3",""); }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.song_layout,R.id.tt,song);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            startActivity(new Intent(MainActivity.this,MainActivity2.class)
                        .putExtra("pos",position).putExtra("list",songs)); }}); }
    private ArrayList<File> read(File root){
        ArrayList<File> arrayList=new ArrayList<File>();
        File[] files =root.listFiles();
           for (File file : files) {

               if (file.isDirectory()) {
                   arrayList.addAll(read(file));
               } else {
                   if (file.getName().endsWith(".mp3")||file.getName().endsWith(".wav")) {
                       arrayList.add(file); } } }
           return arrayList; }
}