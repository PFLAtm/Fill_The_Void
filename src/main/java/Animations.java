import static com.raylib.Raylib.*;
import static com.raylib.Jaylib.WHITE;
public class Animations {
    private Texture texture;
    private String fileName;
    private Player player;
    public Animations(String fileName,Player player) {
        this.fileName = fileName;
        this.player = player;
        this.texture = LoadTexture(this.getClass().getResource(this.fileName).getPath().substring(1));
    }

    public void ShowAnimation() {
        DrawTextureV(this.texture,this.player.GetPosition(),WHITE);
    }


}
