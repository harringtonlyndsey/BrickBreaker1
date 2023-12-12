package com.company;

import java.awt.*;

public class PowerUp {
    private int x, y, dy, type, width, height;
    private boolean isOnScreen;
    private Color color;

    public final static int WIDEPADDLE = 4;
    public final static int FASTBALL = 5;
    public final static Color WIDECOLOR = new Color(50, 50, 125);
    public final static Color FASTCOLOR = new Color(250, 60, 60);

    public PowerUp(int xStart, int yStart, int theType, int theWidth, int theHeight){
    x = xStart;
    y = yStart;
    type = theType;
    width = theWidth;
    height = theHeight;
    if (type < 4){
        type = 4;
    }
    if(type > 5){
        type = 5;
    }
    if(type == WIDEPADDLE){
        color = WIDECOLOR;
    }
    if(type == FASTBALL){
        color = FASTCOLOR;
    }
    dy = (int)(Math.random() * 6 + 1);
      }
    public void draw(Graphics2D g){
    g.setColor(color);
    g.fillRect(x, y, width, height);
    }
    public void update(){

    }
    public int getX(){
        return x;
    }
    public void setX(int x){
        this.x = x;
    }
    public int getY(){
        return y;
    }
    public void setY(int newY){
        y = newY;
    }
    public int getDY(){
        return dy;
    }
    public void setDY(int newDY){
        dy = newDY;
    }
    public int getType(){
        return type;
    }
    public boolean getIsOnScreen(){
        return isOnScreen;
    }
    public void setIsOnScreen(boolean onScreen){
        isOnScreen = onScreen;
    }
    public Rectangle getRect(){
        return new Rectangle(x, y, width, height);
    }
}
