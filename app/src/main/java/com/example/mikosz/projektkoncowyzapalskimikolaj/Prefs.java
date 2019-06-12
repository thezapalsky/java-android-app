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
}
