package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class Game extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 28;
    private Timer timer;
    private int delay = 8;
    private int playerPos = 310;
    private int ballPosX = 120;
    private int ballPosY = 350;
    private int ballXDir = -1;
    private int ballYDir = -2;

    private Map map;

    public Game(){
        map = new Map(4, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }


    public void paint(Graphics g){
        //background
        g.setColor(Color.white);
        g.fillRect(1,1, 692, 592);

        //border
        g.setColor(Color.white);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        //paddle
        g.setColor(Color.pink);
        g.fillRect(playerPos, 550, 100, 15);

        //ball
        g.setColor(Color.orange);
        g.fillOval(ballPosX, ballPosY, 20, 20);

        //map
        map.draw((Graphics2D)g);

        //score
        g.setColor(Color.black);
        g.setFont(new Font("Ariel", Font.BOLD, 25));
        g.drawString("" +score, 590, 30);

        if(totalBricks <= 0){
            play = false;
            ballXDir = 0;
            ballYDir = 0;
            g.setColor(Color.black);
            g.setFont(new Font("Ariel", Font.BOLD, 30));
            g.drawString("You won!", 260, 300);
            g.setFont(new Font("Ariel", Font.BOLD, 20));
            g.drawString("Press enter to play again", 190, 350);
        }

        if(ballPosY > 570) {
            play = false;
            ballXDir = 0;
            ballYDir = 0;
            g.setColor(Color.black);
            g.setFont(new Font("Ariel", Font.BOLD, 30));
            g.drawString("Game over", 190, 300);
            g.setFont(new Font("Ariel", Font.BOLD, 20));
            g.drawString("Press enter to play again", 190, 350);
        }

        g.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if(play){
            if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerPos, 550, 100, 8))){
                ballYDir = -ballYDir;
            }
            A: for(int i = 0; i<map.map.length; i++){
                for(int j = 0; j<map.map[0].length; j++){
                    if(map.map[i][j] > 0) {
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;
                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);
                        Rectangle brickRect = rect;

                        if(ballRect.intersects(brickRect)){
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score += 1;

                            if(ballPosX + 19 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width){
                                ballXDir = -ballXDir;
                            } else {
                                ballYDir = -ballYDir;
                            }
                            break A;
                        }
                    }
                }
            }
            ballPosX += 2 * ballXDir;
            ballPosY += 2 * ballYDir;
            if(ballPosX < 0){
                ballXDir = -ballXDir;
            }
            if(ballPosY < 0){
                ballYDir = -ballYDir;
            }
            if(ballPosX > 670){
                ballXDir = -ballXDir;
            }
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {    }

    @Override
    public void keyReleased(KeyEvent e) {    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerPos >= 600){
                playerPos = 600;
            } else {
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerPos < 10){
                playerPos = 10;
            } else {
                moveLeft();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if (!play){
                play = true;
                ballPosX = 120;
                ballPosY = 350;
                ballXDir = -1;
                ballYDir = -2;
                playerPos = 310;
                score = 0;
                totalBricks = 28;
                map = new Map(3, 7);

                repaint();
            }
        }
    }
    public void moveRight(){
        play = true;
        playerPos+=20;
    }

    public void moveLeft(){
        play = true;
        playerPos-=20;
    }
}