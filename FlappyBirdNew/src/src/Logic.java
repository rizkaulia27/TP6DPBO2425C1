import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Logic implements ActionListener, KeyListener{
    int frameWidth = 360;
    int frameHeight = 640;

    int playerStartPosX = frameWidth / 2;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;

    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    View view;
    Image birdImage;
    Player player;
    boolean gameOver = false;
    int score = 0;
    JLabel scoreLabel;

    Image lowerPipeImage;
    Image upperPipeImage;
    ArrayList<Pipe> pipes;

    Timer gameLoop;
    Timer pipesCoolDown;
    int gravity = 1;
    int pipeVelocityX = -2;

    public Logic(){
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);

        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();;
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();;
        pipes = new ArrayList<Pipe>();

        pipesCoolDown = new Timer(1500, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                System.out.println("Pipa");
                placePipes();
            }
        });
        pipesCoolDown.start();

        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    public void move(){
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);

            // Deteksi jika player sudah melewati pipa bawah (1 poin per sepasang pipa)
            if (!pipe.isPassed() && pipe.getImage() == lowerPipeImage && player.getPosX() > pipe.getPosX() + pipe.getWidth()) {
                pipe.setPassed(true);
                score++;
                if (scoreLabel != null) {
                    scoreLabel.setText("Score: " + score);
                }
                System.out.println("Score: " + score);
            }
        }
    }

    public void triggerGameOver() {
        if (!gameOver) {
            gameOver = true;
            gameLoop.stop();
            pipesCoolDown.stop();
            System.out.println("Game Over!");

            if (view != null) {
                view.showGameOverUI(score);
            }
        }
    }

    public void checkCollision() {
        Rectangle playerRect = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());

        for (Pipe pipe : pipes) {
            Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());
            if (playerRect.intersects(pipeRect)) {
                triggerGameOver();
                return; //Keluar dari loop, game sudah over
            }
        }

        //Cek kalau jatuh ke bawah (menyentuh tanah)
        if (player.getPosY() >= frameHeight - player.getHeight()) {
            triggerGameOver();
        }
    }

    public void restartGame(){
        //Untuk reset score setiap kali restart game
        score = 0;
        if (scoreLabel != null) {
            scoreLabel.setText("Score: 0");
        }

        //Reset player
        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);

        //Reset pipes
        pipes.clear();

        //Reset game state
        gameOver = false;

        //Restart timers
        pipesCoolDown.start();
        gameLoop.start();

        System.out.println("Game Restarted!");
        if (view != null) {
            view.hideGameOverUI();
        }
    }

    public void setScoreLabel(JLabel scoreLabel) {
        this.scoreLabel = scoreLabel;
        this.scoreLabel.setText("Score: 0");
    }

    public void placePipes(){
        int randomPosY = (int) (pipeStartPosY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    public void setView(View view){
        this.view = view;
    }

    public Player getPlayer(){
        return player;
    }

    public ArrayList<Pipe> getPipes(){
        return pipes;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (!gameOver) {
            move();
            checkCollision();
            if(view != null){
                view.repaint();
            }
        }
        if(view != null){
            view.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e){
    }
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            player.setVelocityY(-10);
        }

        if(e.getKeyCode() == KeyEvent.VK_R && gameOver){
            restartGame();
        }
    }
    public void keyReleased(KeyEvent e){}
}
