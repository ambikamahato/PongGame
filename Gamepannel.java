import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Gamepannel extends JPanel implements Runnable {
    int width = 1000;
    int height  = (int)(0.555*width); // typeCasting height is mulitple of 0.555
    Dimension screen_size = new Dimension(width,height);


    //Set paddle Dimension
    int Paddle_Height = 100;
    int Paddle_width = 25;
    GamePaddle paddle1;
    GamePaddle paddle2;

    //Set Ball Dimension
    int ball_Dia = 20;
    Ball ball;
    Thread gameThread;

    Graphics graphics; //Graphics Class for using paint only
    Image image;
    Score score = new Score(width,height);


    Gamepannel()
    {
        newPaddles(); // Create a Function for Handdling Paddles
        newBall();  // Create a Function for Handdling Ball
        this.setFocusable(true);  // for Game more Focusable
        this.addKeyListener(new AL()); //KEy listner Under Action Listner / Run Paddle with help of keys / Action class are Runable class

        this.setPreferredSize(screen_size);


        gameThread = new Thread(this);
        gameThread.start();



    }




    private void newBall() {  // Create a Function for Handdling Ball
        Random random = new Random(); //Ball start from midle line
        ball = new Ball(width/2-ball_Dia/2,random.nextInt(height-ball_Dia),ball_Dia,ball_Dia);
    }

    private void newPaddles() { // Create a Function for Handdling Paddles
        paddle1 = new GamePaddle(0,height/2-Paddle_Height/2,Paddle_width,Paddle_Height, 1);
        paddle2 = new GamePaddle(width-Paddle_width,height/2-Paddle_Height/2,Paddle_width,Paddle_Height,2);

    }

    @Override
    public void paint(Graphics g) {
        image = createImage(getWidth(),getHeight()); // Create Image with help of width & height over the Graphics or Image
        graphics = image.getGraphics(); //graphics  Assign with Image
        draw(graphics); // Create draw fun and pass the graphics value
        g.drawImage(image,0,0,this);
    }

    private void draw(Graphics g) { // for Drawing
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }



    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 100000000/amountOfTicks;
        double delta = 0;

        while(true)
        {
            long now = System.nanoTime();
            delta+= (now-lastTime)/ns; //How many ticks are used change this particular time last to now/ How many secounds are gone
            lastTime = now; // lastTime to current time
            if(delta>=1)
            {
                move();
                repaint();
                checkCollsion();
                delta--;
            }

        }

    }

    private void checkCollsion() {
        if(ball.y<=0)
        {
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y>=height-ball_Dia)
        {
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.intersects(paddle1))
        {
            ball.xVelocity = -ball.xVelocity;
            ball.xVelocity++;
            if(ball.yVelocity>0)
            {
                ball.yVelocity++;
            }else{
                ball.yVelocity--;
            }
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if(ball.intersects(paddle2))
        {
            ball.xVelocity = -ball.xVelocity;
            ball.xVelocity++;
            if(ball.yVelocity>0)
            {
                ball.yVelocity++;
            }else{
                ball.yVelocity--;
            }
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if(paddle1.y<=0)
        {
            paddle1.y=0;
        }
        if(paddle1.y>=height-Paddle_Height)
        {
            paddle1.y = height-Paddle_Height;
        }

        if(paddle2.y<=0)
        {
            paddle2.y=0;
        }
        if(paddle2.y>=height-Paddle_Height)
        {
            paddle2.y = height-Paddle_Height;
        }

        if(ball.x>=width-ball_Dia)
        {
            newPaddles();
            newBall();
            score.player1++;
        }
        if(ball.x<=0)
        {
            newPaddles();
            newBall();
            score.player2++;
        }

    }

    private void move() {// for moving Objects
        paddle1.move();
        paddle2.move();
        ball.move();
    }
    public class AL extends KeyAdapter{ // For Handling the key
       public void keyPressed(KeyEvent e)
       {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e)
        {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);

        }
    }
}
