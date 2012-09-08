package winner;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Image;

public class Menu extends BasicGameState{
	
	public String mouse = "No input yet!";
	public float mousePositionX;
	public float mousePositionY;
	
	public Menu(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{

	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		
		g.drawString("Wiggle Pong",50,50);
		g.drawString("Chris Laverdiere 2012",50,80);
		g.drawString("Hit Enter to play", 90, 215);
		g.drawString("X:" + mousePositionX,500,30);
		g.drawString("Y:" + mousePositionY,500,60);
		g.drawRect(50 ,200, 240, 50);
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		mousePositionX = Mouse.getX();
		mousePositionY = Mouse.getY();
		Display.sync(60);
		if(input.isKeyDown(Input.KEY_ENTER)){
			sbg.enterState(1);
		}
	}
	
	public int getID(){
		return 0;
	}

}
