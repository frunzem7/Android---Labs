package com.example.application3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class RecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        Intent intent = getIntent();
        String uri = intent.getStringExtra("uri");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        List<Item> items = parsingXml(new HttpClient().get(uri));
        RecyclerView recyclerView = findViewById(R.id.rssView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RssAdapter(items));
    }

    private List<Item> parsingXml(String value) {
        List<Item> items = new ArrayList<>();

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new InputSource(new StringReader(value)));
            Element element = document.getDocumentElement();
            NodeList nodeList = element.getElementsByTagName("item");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element entry = (Element) nodeList.item(i);
                Element elementTitle = (Element) entry.getElementsByTagName("title").item(0);
                Element elementDescription = (Element) entry.getElementsByTagName("description").item(0);
                Element elementLink = (Element) entry.getElementsByTagName("link").item(0);
                String title = elementTitle.getFirstChild().getNodeValue();
                String description = elementDescription.getFirstChild().getNodeValue();
                String link = elementLink.getFirstChild().getNodeValue();
                items.add(new Item(title, link, description));
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }

        return items;
    }
}