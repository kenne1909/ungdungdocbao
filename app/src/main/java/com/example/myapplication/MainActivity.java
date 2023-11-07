package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvitem;
    ArrayList<Item> arrayTittle;
    ArrayList<String> arrayLink;
    ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvitem=(ListView) findViewById(R.id.listviewtieude);
        arrayTittle =new ArrayList<>();
        arrayLink= new ArrayList<>();

        adapter=new ItemAdapter(MainActivity.this, R.layout.item,arrayTittle);
        lvitem.setAdapter(adapter);


        new ReadRSS().execute("https://vnexpress.net/rss/the-gioi.rss");

        lvitem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(MainActivity.this,NewsActivity.class);
                intent.putExtra("linkTicTuc",arrayLink.get(i));
                startActivity(intent);
            }
        });
    }

    private class ReadRSS extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {

            StringBuilder content=new StringBuilder();

            try {
                URL url=new URL(strings[0]);

                InputStreamReader inputStreamReader=new InputStreamReader(url.openConnection().getInputStream());

                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    content.append(line+"\n");
                }
                bufferedReader.close();


            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            XMLDOMParser parser=new XMLDOMParser();

            Document document=parser.getDocument(s);

            NodeList nodeList=document.getElementsByTagName("item");

            String tieude="";
            String date="";
            String imageLink="";
            String htmlDesc="";

            for(int i=0;i<nodeList.getLength();i++){
                Element element= (Element) nodeList.item(i);
                tieude = parser.getValue(element,"title");
                date = parser.getValue(element,"pubDate");
                htmlDesc = parser.getValueDesc(element,"description");
                imageLink = parser.getImageLink(htmlDesc);
                arrayTittle.add(new Item(tieude,date,imageLink));
                arrayLink.add(parser.getValue(element,"link"));
            }
            adapter.notifyDataSetChanged();
        }
    }
}