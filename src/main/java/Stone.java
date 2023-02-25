import java.util.ArrayList;
import java.util.List;

import static com.raylib.Raylib.*;

public class Stone {
    public Vector2 position;

    public Rectangle hitBox;

    private static List<Stone> stone = new ArrayList<>();
    public Stone (Vector2 pos,Animations anim) {
        this.position = pos;
        this.applyHitBoxStone(anim);

        stone.add(this);

    }

    public static List<Stone> GetAllStones () {
        return stone;
    }

    public static void SpawnStone(Vector2 pos, Animations anim) {
        Stone stone = new Stone(pos, anim);
    }

    public void applyHitBoxStone (Animations anim) {
        this.hitBox = new Rectangle().x(this.position.x()).y(this.position.y()).height(anim.image.height()).width(anim.image.width());

    }




}
