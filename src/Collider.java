import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Collider extends Rectangle  {

    public Collider(float x, float y, float width, float hight, Color c)
    {
        super(x, y, width, hight);
        setFill(c);
        System.out.println("NewCollider");



    }

}
