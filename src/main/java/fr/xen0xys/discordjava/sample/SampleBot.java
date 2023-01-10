package fr.xen0xys.discordjava.sample;

import fr.xen0xys.discordjava.DJBot;

public class SampleBot {

    public static final String NAMESPACE = "DJBOT";
    public static final String TOKEN = "";

    public static void main(String[] args){
        try {
            DJBot bot = new DJBot(TOKEN, NAMESPACE);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
