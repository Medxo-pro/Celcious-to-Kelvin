/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Atmani
 */
public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static int DELAY = 75; // the heigher this number, the slower the game is.
    final int x[] = new int[GAME_UNITS]; //hold all the x cpordinates
    final int y[] = new int[GAME_UNITS]; //hold all y coordinates 
    int bodyParts = 6;
    int applesEaten ;
    int appleX;
    int appleY;
    int applex[] = new int[5];
    int appley[] = new int[5];
    int deathx[] = new int[5];
    int deathy[] = new int[5];
    int deathX;
    int deathY;
    String level = "L0";
    char direction = 'R'; // R (right), L (Left), U (Up), D (Down)
    boolean running = false;
    Timer timer;
    Random random;
    static int maxScore = 0;

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        newApple();
        newApples();
        newDeath();
        newDeaths();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
        System.out.println("To pause press the ESCAPE");
        System.out.println("To continue press SPACE bar");
        timer.stop();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        if (running) {
            
            if (applesEaten < 10){
                for (int i = 0; i<SCREEN_HEIGHT/UNIT_SIZE; i++){
                    g.setColor(Color.GRAY);
                    g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
                    g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
                }
            }
            if (applesEaten > 10) {
                g.setColor(Color.yellow);
                g.fillOval(deathX, deathY, UNIT_SIZE, UNIT_SIZE);
                level = "L1";     
            }
            if (applesEaten > 20) {
                this.setBackground(Color.magenta);
                level = "L2";
            }
            if (applesEaten > 30) {
                this.setBackground(Color.orange);
                level = "L3";
            }
            if (applesEaten > 40) {
                this.setBackground(Color.red);
                level = "L4";
            }
            if (applesEaten > 45) {
                this.setBackground(Color.CYAN);
                level = "L5";
                g.setColor(Color.yellow);
                for (int i = 0; i < deathx.length; i++) {
                    g.fillOval(deathx[i], deathy[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            if (applesEaten > 55) {
                this.setBackground(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                for (int i = 0; i<SCREEN_HEIGHT/UNIT_SIZE; i++){
                    g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                    g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
                    g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
                }
                level = "L4";
            }
            

            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
            for (int i = 0; i < applex.length; i++) {
                g.fillOval(applex[i], appley[i], UNIT_SIZE, UNIT_SIZE);
            }

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);

                }
            }

            g.setColor(Color.white);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten + " Level: " + level, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten + " Level: " + level)) / 2, g.getFont().getSize());

        } else {
            gameOver(g);
        }
    }

    public void newApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void newDeath() {
        deathX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        deathY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void newDeaths() {
        for (int i = 0; i < deathx.length; i++) {
            deathx[i] = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
            deathy[i] = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        }
    }

    public void newApples() {
        for (int i = 0; i < applex.length; i++) {
            applex[i] = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
            appley[i] = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        }
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];

        }
        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
        }

    }

    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }


        for (int i = 0; i < applex.length; i++) {
            if ((x[0] == applex[i]) && (y[0] == appley[i])) {
                applex[i] = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
                appley[i] = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
                bodyParts++;
                applesEaten++;
                newApple();
            }
        
        }
    }

    public void checkDeath() {
        if ((x[0] == deathX) && (y[0] == deathY)) {
            bodyParts = bodyParts - 8;
            applesEaten = applesEaten - 8;
            level = "L0";
            deathX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
            deathY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
            newDeath();
        }

        if (applesEaten>45){
        for (int i = 0; i < deathx.length; i++) {
            if ((x[0] == deathx[i]) && (y[0] == deathy[i])) {
                deathx[i] = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
                deathy[i] = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
                bodyParts = bodyParts - 8;
                applesEaten = applesEaten - 8;
                newDeath();
            }
         }
        }
    }

    public void checkCollisions() {

        //Check if head collides with rest of body
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && y[0] == y[i]) {
                running = false;
            }
        }
        //Check if head collides with left border
        if (x[0] < 0) {
            running = false;
        }

        //Check if head collides with left border
        if (x[0] > SCREEN_WIDTH) {
            running = false;
        }
        //Check if head collides with top border
        if (y[0] < 0) {
            running = false;
        }
        //Check if head collides with bottom border
        if (y[0] > SCREEN_HEIGHT) {
            running = false;
        }

        if (running == false) {
            timer.stop();
        }

    }

    public void gameOver(Graphics g) {

        if (applesEaten > maxScore) {
            maxScore = applesEaten;
            //Show New Top Score
            g.setColor(Color.white);
            g.setFont(new Font("Ink Free", Font.BOLD, 25));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Your Achieved A New Max Score:", (SCREEN_WIDTH - metrics.stringWidth("You Achieved A New Max Score:")) / 2, g.getFont().getSize() * 5);
        }

        g.setColor(Color.red);
        //TopScore
        if (level.equals("L4")) {
            g.setColor(Color.black);
        } else {
            g.setColor(Color.red);
        }
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Max Score: " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Max Score: " + applesEaten)) / 2, g.getFont().getSize() * 2);

        //Score
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics2.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());

        //Game Over text
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics3 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics3.stringWidth("GamevOver")) / 2, SCREEN_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            if (applesEaten > 10) {
                checkDeath();
            }
            checkCollisions();
        }
        repaint();

    }

    public class MyKeyAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    timer.start();
                    break;
                case KeyEvent.VK_ESCAPE:
                    timer.stop();
                    break;

            }
        }

    }

}
