import static com.raylib.Raylib.*;
import static com.raylib.Jaylib.WHITE;
public class Animations {
    public Image image;
    private Texture texture;
    private String fileName;
    private Player player;
    private Rectangle cropRec;
    private float columnWidth;
    private int columns;
    private int steps = 0;

    private float timer;
    public Animations(String fileName,Player player) {
        this.fileName = fileName;
        this.player = player;
        this.image = LoadImage(this.getClass().getResource(this.fileName).getPath().substring(1));
        ImageResize(this.image,50,50);
        this.texture = LoadTextureFromImage(this.image);
    }

    public Animations(String fileName) {
        this.fileName = fileName;
        this.image = LoadImage(this.getClass().getResource(this.fileName).getPath().substring(1));
        ImageResize(this.image,50,50);
        this.texture = LoadTextureFromImage(this.image);
    }

    public Animations (String fileName,int columns ,Player player){
        this.fileName = fileName;
        this.player = player;
        this.image = LoadImage(this.getClass().getResource(this.fileName).getPath().substring(1));
        this.columns = columns;
        this.steps = 0;
        this.columnWidth = this.image.width()/columns;
        this.cropRec = new Rectangle().height(this.image.height()).width(columnWidth);
        ImageCrop(this.image,this.cropRec);
        ImageResize(this.image,50,50);
        this.texture = LoadTextureFromImage(this.image);

    }

    public void ShowAnimation() {
        DrawTextureV(this.texture,this.player.GetPosition(),WHITE);
    }

    public static void ShowAnimation (Animations animation,Vector2 pos) {
        DrawTextureV(animation.texture,pos,WHITE);
    }

    public void Animate (float speed) {
        timer += speed*GetFrameTime();
        System.out.println(timer);
        if(timer >= 10){
            this.steps ++;
            timer =0;
            if(steps == this.columns) this.steps=0;

            this.cropRec.x(this.columnWidth*this.steps);
            ImageCrop(this.image,this.cropRec);
            ImageResize(this.image,50,50);
            this.texture = LoadTextureFromImage(this.image);

        }
        ShowAnimation();
    }


}
