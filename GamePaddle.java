import java.awt.*;
import java.awt.event.KeyEvent;

public class GamePaddle extends Rectangle {

    int id;//1st Take id for which paddle is move
    int yvelocity; // paddle moving some speed
    int speed = 10;
    GamePaddle(int x,int y,int Paddle_width,int Paddle_height,int id)// GamePaddle function
    {
        super(x,y,Paddle_width,Paddle_height);
        this.id = id;

    }
    public void draw(Graphics g)
    {
        if(id == 1)
        {
            g.setColor(Color.blue);
        }else{
            g.setColor(Color.red);
        }
        g.fillRect( x, y, width, height);
    }
    public void keyPressed(KeyEvent e)
    {
        switch (id)
        {
            case 1:
                if(e.getKeyCode()== KeyEvent.VK_W)
                {
                    setYDirection(-speed);
                }
                if(e.getKeyCode() == KeyEvent.VK_S)
                {
                    setYDirection(speed);
                }
                break;
            case 2:
                if(e.getKeyCode() == KeyEvent.VK_UP)
                {
                    setYDirection(-speed);
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    setYDirection(speed);
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e)
    {
        switch (id)
        {
            case 1:
                if(e.getKeyCode()== KeyEvent.VK_W)
                {
                    setYDirection(0);
                }
                if(e.getKeyCode() == KeyEvent.VK_S)
                {
                    setYDirection(0);
                }
                break;
            case 2:
                if(e.getKeyCode() == KeyEvent.VK_UP)
                {
                    setYDirection(0);
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    setYDirection(0);
                }
                break;
        }
    }

    private void setYDirection(int i) {
        yvelocity = i;
    }
    public void move()
    {
        y = y+ yvelocity;
    }
}
