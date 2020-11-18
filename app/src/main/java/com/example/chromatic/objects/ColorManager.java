package com.example.chromatic.objects;

import java.util.Random;

public class ColorManager {

    public Color red = new Color();
    public Color yellow = new Color();
    public Color blue = new Color();
    public Color green = new Color();
    public Color purple = new Color();
    public Color orange = new Color();
    public Color black = new Color();
    public Color cellDefault = new Color();

    public int colorRed = 0;
    public int colorBlue = 0;
    public int colorYellow = 0;


    public ColorManager(){
        setBlue();
        setRed();
        setYellow();
        setGreen();
        setOrange();
        setPurple();
        setBlue();
        setBlack();
        setCellDefault();
    }

    private void setBlue() {
        blue.b = 1;
        blue.r = 0;
        blue.y = 0;
    }

    private void setRed() {
        red.r = 1;
        red.b = 0;
        red.y = 0;
    }

    private void setYellow() {
        yellow.y = 1;
        yellow.b = 0;
        yellow.r = 0;
    }

    private void setGreen() {
        green.b = 1;
        green.y = 1;
        green.r = 0;
    }

    private void setOrange() {
        orange.r = 1;
        orange.y = 1;
        orange.b = 0;
    }

    private void setPurple() {
        purple.b = 1;
        purple.r = 1;
        purple.y = 0;
    }

    private void setBlack() {
        black.r = 1;
        black.y = 1;
        black.b = 1;
    }

    private void setCellDefault(){
        cellDefault.r = 0;
        cellDefault.b = 0;
        cellDefault.y = 0;
    }

    public Color sumColor(Color color1, Color color2){
        Color sum = new Color();

        sum.r = color1.r + color2.r;
        sum.y = color1.y + color2.y;
        sum.b = color1.b + color2.b;

        return sum;
    }

    public Boolean areEquals(Color color1, Color color2){
        if(color1.r == color2.r && color1.y == color2.y && color1.b == color2.b)
            return true;
        else return false;
    }

    public Color getRandomColor(){
        Random random = new Random();
        int randomNr = random.nextInt(3);

        if(colorRed > 3) {
            colorRed = 0;
            return red;
        }
        if(colorYellow > 3) {
            colorYellow = 0;
            return yellow;
        }
        if(colorBlue > 3) {
            colorBlue = 0;
            return  blue;
        }
        if(randomNr == 0) {
            colorRed = 0;
            colorBlue++;
            colorYellow++;
            return red;
        } else if(randomNr == 1) {
            colorYellow = 0;
            colorRed++;
            colorBlue++;
            return yellow;
        } else {
            colorBlue = 0;
            colorRed++;
            colorYellow++;
            return blue;
        }
    }

    public Boolean checkColor(Color color){
        if(color.r < 2 && color.y < 2 && color.b < 2)
            return true;
        else return false;
    }

}
