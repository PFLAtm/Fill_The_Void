import static com.raylib.Jaylib.LIGHTGRAY;
import static com.raylib.Jaylib.BLACK;
import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Raylib.*;
public class Main {
    public static void main(String[] args) {
        int screenWidth = 500;
        int screenHeight = 500;
        InitWindow(screenWidth,screenHeight,"Fill the Void");
        //Variables
        Vector2 playerPos = new Vector2().x(screenWidth/2).y(50);
        Vector2 playerSize = new Vector2().x(50).y(50);


        int stoneNumber = 0;

        float speed = 0;
        float deltatime=0;
        float voidRadius = 10;
        float voidTimer = 0;
        float voidTime = 5;
        float stoneTimer = 0;
        float timeSurvived = 0;
        float highscore = 0;

        boolean walking = false;
        boolean lost = false;
        boolean startMenu = true;

        Player player = new Player(playerPos);
        Animations playeranim = new Animations("player.png",player);
        Animations playerWalk = new Animations("player_walk.png",2,player);
        player.ApplyHitBox(playeranim);

        Animations stone = new Animations("stone.png");


        while(!WindowShouldClose()){
            deltatime = GetFrameTime();
            speed = deltatime*200;
            voidTimer += deltatime*10;
            stoneTimer += deltatime*10;


            //menu
            if(lost && IsKeyPressed(KEY_ENTER))lost = false;

            //Movement
            if(IsKeyDown(KEY_W)){
                playerPos.y(playerPos.y()-speed);
            }
            if(IsKeyDown(KEY_D)){
                playerPos.x(playerPos.x()+speed);
                walking = true;
            }
            if(IsKeyDown(KEY_S)){
                playerPos.y(playerPos.y()+speed);
            }
            if(IsKeyDown(KEY_A)){
                playerPos.x(playerPos.x()-speed);
            }

            player.SetPosition(playerPos);
            player.UpdateHitBox();


            //Score
            timeSurvived += deltatime*2;


            //Collision

            if(CheckCollisionCircleRec(new Vector2().x(screenWidth/2).y(screenHeight/2),voidRadius-20,player.hitBox)){

                lost = true;
                voidRadius = 10;
                highscore = timeSurvived;
                timeSurvived = 0;
                stoneNumber = 0;
                voidTime = 5;
                playerPos.x(screenWidth/2).y(50);
                for(int i = 0;i<Stone.GetAllStones().size();i++){
                    Stone.GetAllStones().remove(Stone.GetAllStones().get(i));

                }

            }

            if(player.hitBox.x()< 0) playerPos.x(playerPos.x()+speed);
            if(player.hitBox.x()+player.hitBox.width()> screenWidth) playerPos.x(playerPos.x()-speed);
            if(player.hitBox.y()< 0) playerPos.y(playerPos.y()+speed);
            if(player.hitBox.y()+player.hitBox.height()> screenHeight) playerPos.y(playerPos.y()-speed);

            for(int i = 0;i<Stone.GetAllStones().size();i++){
                if(CheckCollisionCircleRec(new Vector2().x(screenHeight/2).y(screenWidth/2),voidRadius,Stone.GetAllStones().get(i).hitBox)){
                    Stone.GetAllStones().remove(Stone.GetAllStones().get(i));
                    stoneNumber--;
                }
            }

            for(int i = 0;i<Stone.GetAllStones().size();i++){
                if(Stone.GetAllStones().get(i).hitBox.x()+Stone.GetAllStones().get(i).hitBox.width()/2 >= screenWidth || Stone.GetAllStones().get(i).hitBox.x()+Stone.GetAllStones().get(i).hitBox.width()/2 <= 0 ||
                        Stone.GetAllStones().get(i).hitBox.y()+Stone.GetAllStones().get(i).hitBox.height()/2 <= 0 || Stone.GetAllStones().get(i).hitBox.y()+Stone.GetAllStones().get(i).hitBox.height()/2 >= screenHeight){
                    Stone.GetAllStones().remove(Stone.GetAllStones().get(i));
                    stoneNumber--;
                }
            }



            //voidhole

            if(voidTimer > voidTime){
                voidTimer = 0;
                voidRadius += 1.5;

            }

            if(player.holdStone && voidRadius > 5){
                if(CheckCollisionCircleRec(new Vector2().x(screenWidth / 2).y(screenHeight / 2), voidRadius+40,player.hitBox)){
                    voidRadius -= 5;
                    player.holdStone = false;
                    if(voidTime > 0.2) voidTime -= 0.5;

                }
            }


            //Stones
            if(stoneTimer > 10 && stoneNumber < 5){
                stoneTimer = 0;
                Stone.SpawnStone(new Vector2().x(GetRandomValue(10,screenWidth-10)).y(GetRandomValue(10,screenHeight-10)),stone);
                stoneNumber++;


            }

            if(!player.holdStone){
                for(int i = 0;i < Stone.GetAllStones().size();i++){
                    if(CheckCollisionRecs(player.hitBox,Stone.GetAllStones().get(i).hitBox)){
                        player.holdStone = true;
                        Stone.GetAllStones().remove(Stone.GetAllStones().get(i));
                        stoneNumber--;
                    }
                }
            }


            System.out.println(stoneNumber);




            BeginDrawing();
            ClearBackground(RAYWHITE);



            if(!lost) {


                DrawCircleV(new Vector2().x(screenWidth / 2).y(screenHeight / 2), voidRadius+40, LIGHTGRAY);
                DrawCircleV(new Vector2().x(screenWidth / 2).y(screenHeight / 2), voidRadius, BLACK);

                for(int i = 0;i< Stone.GetAllStones().size();i++){
                    Animations.ShowAnimation(stone,Stone.GetAllStones().get(i).position);
                }

                playeranim.ShowAnimation();

                DrawText(String.valueOf(timeSurvived).substring(0,String.valueOf(timeSurvived).indexOf(".")),screenWidth/2,20,30,BLACK);





            }
            else{
                DrawText("You got devoured \n by the void!",20,50,50,BLACK);
                DrawText("press Enter to play again",50,400,30,BLACK);
            }


            EndDrawing();
        }

    }
}