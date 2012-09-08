package winner;

import java.io.IOException;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;
import org.newdawn.slick.Image;

public class Play extends BasicGameState {
	
	public String direction = "right";
	public float score;
	public float hiscore = 0;
	public float speed = 1;
	float rectPosX = 20;
	float rectPosY = 50;
	float[] ball = {300,150,20,20};
	public Audio bloop;
	public Audio bloop2;
	public Audio bop;
	public Image BGround;
	
	public Play(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		
		BGround = new Image("res/BGroundPong.png");
		
		try {
			bloop = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/bloop.wav"));
			bloop2 = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/bloop2.wav"));
			bop = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/bop.wav"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawImage(BGround,0,0);
		g.drawString("Let's pong it up baby",240,50);
		g.drawString("Your score yo: " + score, 240,300);
		g.drawString("Yo Hoss, this is your hi-score: " + hiscore, 190, 320);
		g.fillRect(rectPosX,rectPosY,20,70);
		g.fillRect(rectPosX+580,rectPosY,20,70);
		g.fillOval(ball[0], ball[1], ball[2], ball[3]);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Display.sync(60);
		
		Input input = gc.getInput();
		rectPosY = -(Mouse.getY())+320;	         //sync rect boxes with mouse position
		score += 1;
		
		if ((ball[1]+10) - rectPosY < 70 && (ball[1]+10) - rectPosY > 0 ){    //hit-box detection
		
			if (ball[0] > 580){ 
				bloop.playAsSoundEffect(1.0f, 1.0f, false);
				speed += .1;
				direction = "left";
			}                                 //left and right boundaries
			
			if (ball[0] < 40){
				bloop2.playAsSoundEffect(1.0f, 1.0f, false);
				speed += .1;
				direction = "right";
			}
			
		}
		
		if(ball[1]<10){
			ball[1] = 15;
		}
		                             //top and bottom boundaries
		if(ball[1]>350){
			ball[1] = 340;
		}
		
		if(direction == "right"){
			if(ball[1] > 10){
				ball[0] += delta * speed * .4f;
				ball[1] += delta * .2f * randomWithRange(-3,3);
			}else{ball[1] = 11;}
			
		}                                     //ball movement
			
		if(direction == "left"){
			if(ball[1] < 340){
				ball[0] -= delta * speed * .4f;
				ball[1] += delta * .2f * randomWithRange(-3,3);
			}else{ball[1] = 339;}
		}
		
		if ((ball[0] < 20) || (ball[0] > 600)){
			reset();                              //miss detection
		}
		
		if(Mouse.isInsideWindow()){
			Mouse.setGrabbed(true);             //mouse visibility
	    }else{
	    	Mouse.setGrabbed(false);
	    }
		
		if(input.isKeyDown(Input.KEY_ESCAPE)){sbg.enterState(0);Mouse.setGrabbed(false);}
		
	}
	
	public void reset(){
		bop.playAsSoundEffect(1.0f, 1.0f, false);
		ball[0] = 300;
		ball[1] = 150;
		direction = "right";
		if (score > hiscore){hiscore = score;}
		score = 0;
		speed = 1;
	}
	
	public int randomWithRange(int min, int max)
	{
	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}

	public int getID(){
		return 1;
	}

}
