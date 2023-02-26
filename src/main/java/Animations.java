import java.io.File;
import java.net.URISyntaxException;

import static com.raylib.Raylib.*;
import static com.raylib.Jaylib.WHITE;
import static com.raylib.Jaylib.RED;
public class Animations {

    private Main main = new Main();
    public Texture texture;
    private String fileName;
    private Player player;
    public Rectangle cropRec;
    private float frameWidth;
    private int maxFrames;

    private int frame = 0;

    private float timer;
    public Animations(String fileName,Player player) throws URISyntaxException {
        this.fileName = fileName;
        this.player = player;
        this.texture = LoadTexture(main.getPath(fileName));
    }

    public Animations(String fileName) throws URISyntaxException {
        this.fileName = fileName;
        this.texture = LoadTexture(main.getPath(fileName));
    }

    public Animations (String fileName,int columns ,Player player) throws URISyntaxException {
        this.fileName = fileName;
        this.player = player;
        this.maxFrames = columns;
        this.frame = 0;
        this.texture = LoadTexture(main.getPath(fileName));
        this.frameWidth = this.texture.width()/columns;
        this.cropRec = new Rectangle().height(this.texture.height()).width(frameWidth);


    }

    public void ShowAnimation() {
        DrawTextureV(this.texture,this.player.GetPosition(),WHITE);
    }

    public static void ShowAnimation (Animations animation,Vector2 pos) {
        DrawTextureV(animation.texture,pos,WHITE);
    }

    public void Animate (float speed) {
        this.timer += GetFrameTime();

        if(timer >= speed){
            timer = 0;
            this.frame ++;
        }
        this.frame = frame % this.maxFrames;
        this.cropRec.x(this.frameWidth*this.frame);
        DrawTextureRec(this.texture,this.cropRec,this.player.GetPosition(),WHITE);

    }




}
