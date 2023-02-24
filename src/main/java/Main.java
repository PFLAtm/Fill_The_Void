import static com.raylib.Jaylib.BLUE;
import static com.raylib.Jaylib.BLACK;
import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Raylib.*;
public class Main {
    public static void main(String[] args) {
        int screenWidth = 840;
        int screenHeight = 500;
        InitWindow(screenWidth,screenHeight,"Fill the Void");
        //Variables
        Vector2 playerPos = new Vector2().x(10).y(20);
        Vector2 playerSize = new Vector2().x(50).y(50);


        float speed = 0;
        float deltatime=0;
        float voidRadius = 10;

        Player player = new Player(playerPos);
        Animations playeranim = new Animations("player.png",player);

        while(!WindowShouldClose()){
            deltatime = GetFrameTime();
            speed = deltatime*120;


            //Movement
            if(IsKeyDown(KEY_W)){
                playerPos.y(playerPos.y()-speed);
            }
            if(IsKeyDown(KEY_D)){
                playerPos.x(playerPos.x()+speed);
            }
            if(IsKeyDown(KEY_S)){
                playerPos.y(playerPos.y()+speed);
            }
            if(IsKeyDown(KEY_A)){
                playerPos.x(playerPos.x()-speed);
            }

            player.SetPosition(playerPos);





            BeginDrawing();
            ClearBackground(RAYWHITE);

            playeranim.ShowAnimation();

            DrawCircleV(new Vector2().x(screenWidth/2).y(screenHeight/2),voidRadius,BLACK);



            EndDrawing();
        }

    }
}