package com.gujarex.towntour.HelperClasses.HomeAdapter;

public class CatHelperClass {
    int Catimage;
    String Cattitle;

    public CatHelperClass(int catimage, String cattitle) {
        this.Catimage = catimage;
        this.Cattitle = cattitle;

    }

    public int getImage() {
        return Catimage;
    }

    public String getTitle() {
        return Cattitle;
    }
}

