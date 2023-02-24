import static com.raylib.Raylib.*;

public class Player {
    private Vector2 position;
    public Player(Vector2 pos){
        this.position = pos;
    }

    public Vector2 GetPosition() {
        return this.position;
    }

    public void SetPosition(Vector2 newPos) {
        this.position = newPos;
    }
}
