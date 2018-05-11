package com.mygdx.game;

//package com.gamefromscratch;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.utils.reflect.Field;

import java.util.ArrayList;
//import com.sun.tools.javac.util.Log;


public class MyGdxGame implements ApplicationListener, InputProcessor{

	Plane myActor;
	Fireball fireball;
	float elapsedTime;
	int score = 0;
	//BitmapFont font = new BitmapFont();


	private Stage stage;

	@Override
	public void create() {
		stage = new Stage();
		Gdx.input.setInputProcessor(this);

		myActor = new Plane(Gdx.graphics.getDeltaTime());
		fireball = new Fireball(Gdx.graphics.getDeltaTime());

		stage.addActor(myActor);
		stage.addActor(fireball);

		//stage.addActor(myActor);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

		if(stage.getActors().size < 2){
			Fireball newFireball = new Fireball(Gdx.graphics.getDeltaTime());
			stage.addActor(newFireball);
			stage.setKeyboardFocus(newFireball);
			score++;
			newFireball.setScore(score);

			//font.draw(new SpriteBatch(), "Score" + score, 30,30);
		}
		ArrayList<Integer> fireballs = new ArrayList<Integer>();
		ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
		for(int x = 0; x < stage.getActors().size; x++){
			if(stage.getActors().get(x) instanceof Fireball){
				Fireball fireball = (Fireball) stage.getActors().get(x);
				fireballs.add(x);
				rectangles.add(fireball.getRectangle());
			}
			else if(stage.getActors().get(x) instanceof  Plane){
				Plane plane = (Plane) stage.getActors().get(x);
				rectangles.add(plane.getRectangle());
			}
		}
		Rectangle rectangle1 = rectangles.get(0);
		Rectangle rectangle2 = rectangles.get(1);

		if(rectangle1.overlaps(rectangle2)){
			((Fireball) stage.getActors().get(fireballs.get(0))).explode();
			score--;
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		/**float rate = 500f;
		float distance = Math.abs(((float)screenX) - myActor.getX());
		float duration = distance / rate;
		MoveToAction moveToAction = new MoveToAction();
		moveToAction.setPosition((float)screenX, myActor.getY());
		moveToAction.setDuration(duration);

		myActor.addAction(moveToAction);*/
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		float rate = 500f;
		float distance = Math.abs(((float)screenX) - myActor.getX());
		float duration = distance / rate;
		MoveToAction moveToAction = new MoveToAction();
		moveToAction.setPosition((float)screenX, myActor.getY());
		moveToAction.setDuration(duration);

		myActor.clearActions();
		myActor.addAction(moveToAction);
		return false;
		//return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}