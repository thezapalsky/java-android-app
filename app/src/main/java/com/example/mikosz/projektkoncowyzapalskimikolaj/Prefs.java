package com.example.mikosz.projektkoncowyzapalskimikolaj;

/**
 * Created by 4ib1 on 2016-10-07.
 */
public class Prefs {


    public static void setFolderinio(String folderinio) {
        Prefs.folderinio = folderinio;
    }
    public static String getFolderinio() {
        return folderinio;
    }
    private static String folderinio = "";

    public static void setWysokosc(int wysokosc) {
        Prefs.wysokosc = wysokosc;
    }
    public static int getWysokosc() {
        return wysokosc;
    }
    private static int wysokosc;

    public static void setSzerokosc(int szerokosc) {
        Prefs.szerokosc = szerokosc;
    }
    public static int getSzerokosc() {
        return szerokosc;
    }
    private static int szerokosc;

    private static int ktory_kolaz = 0;
    public static void set_ktory_kolaz(int ktory_kolaz){
        Prefs.ktory_kolaz = ktory_kolaz;
    }
    public static int get_ktory_kolaz(){
        return ktory_kolaz;
    }



}
