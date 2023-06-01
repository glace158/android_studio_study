package com.example.webcrawlingapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class CrawlingThread extends Thread{
    private Handler handler;
    private  String string;
    private Document document;
    private Element element;
    private Bundle bundle;
    private Message message;

    public CrawlingThread(Handler handler) {
        this.handler = handler;
        bundle = new Bundle();
    }

    @Override
    public void run() {
        super.run();

        document = null;
        try {
            document = Jsoup.connect("https://www.daum.net/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        element = document.select("title").first();
        string = element.text();
        bundle.putString("Title", string);
        message = handler.obtainMessage();
        message.setData(bundle);
        handler.sendMessage(message);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}