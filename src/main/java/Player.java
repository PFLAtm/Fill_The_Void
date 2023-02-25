import static com.raylib.Raylib.*;

public class Player {
    private Vector2 position;
    public Rectangle hitBox;

    public boolean holdStone = false;
    public Player(Vector2 pos){
        this.position = pos;
    }

    public Vector2 GetPosition() {
        return this.position;
    }

    public void SetPosition(Vector2 newPos) {
        this.position = newPos;
    }

    public void ApplyHitBox(Animations anim) {
        this.hitBox = new Rectangle().x(this.position.x()).y(this.position.y()).height(anim.image.height()).width(anim.image.width());
    }

    public void UpdateHitBox() {
        this.hitBox.x(this.position.x()).y(this.position.y());

    }
}
