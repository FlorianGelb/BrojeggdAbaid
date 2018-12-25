import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Block extends Rectangle implements HarterKoerper
{
    private double x = 0;
    private double y = 0;

    private double hight = 0;
    private double width = 0;

    private Color c = Color.RED;

    public Block(float x, float y, float width, float hight, Color c)
    {
        super(x,y, width, hight);
        setFill(c);

        this.x = x;
        this.y = y;

        this.hight = hight;
        this.width = width;

        this.c = c;
    }

    @Override
    public boolean collideWith(HarterKoerper h)
    {
        if (h.getClass() == Ball.class)
        {
            Ball b = (Ball) h;

            if (((b.x >= this.x) && (b.x <= (this.x + this.width))) && ((this.y - b.y) <= b.radius))
            {
                b.vy = b.vy * -1;
                b.vx = b.vx * -1;
                System.out.println("Collide");
            }
            if (((b.x  > this.x) && (b.x + b.radius < (this.x + this.width))) && ((b.y - (this.y + this.hight)) >= b.radius))
            {
                System.out.println("2");
                b.vy = -b.vy;
                b.vx = -b.vx;
            }
            if ((((b.y + b.radius > this.y) && (b.y + b.radius  < (this.y + this.hight))) && b.x + b.radius < this.x + this.width) &&  ((this.x - b.x) <= b.radius))
            {
                System.out.println("3");
                b.vy = -b.vy;
                b.vx = -b.vx;
            }
            }
        return false;
    }
}
