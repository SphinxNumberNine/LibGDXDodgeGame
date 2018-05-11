package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

/**
 * Created by ChitraRamaswamy on 6/7/17.
 */

public class Plane extends Actor {
    float elapsedTime;
    Texture texture = new Texture(Gdx.files.internal("sprite/jet.png"));
    Sprite sprite = new Sprite(new Texture(Gdx.files.internal("sprite/jet.png")));
    TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("spritesheet/spritesheet.atlas"));
    Animation<TextureRegion> animation = new Animation(1/15f, textureAtlas.getRegions());
    public boolean started = false;
    static float width, height, x, y;

    public Plane(float f){
        elapsedTime = f;
        setBounds(getX(),getY(),texture.getWidth(),texture.getHeight());
        width = sprite.getWidth() / 3.5f;
        height = sprite.getHeight() / 2;
        SequenceAction sequenceAction = new SequenceAction();

        this.setX(300f);
        this.setY(0f);
    }

    @Override
    public void draw(Batch batch, float alpha){
        elapsedTime+=Gdx.graphics.getDeltaTime();
        /**batch.draw(animation.getKeyFrame(elapsedTime, true),this.getX(),getY(),this.getOriginX(),this.getOriginY(),this.getWidth(),
         this.getHeight(),this.getScaleX(), this.getScaleY(),this.getRotation(),0,0,
         texture.getWidth(),texture.getHeight(),false,false);*/
        sprite.setRegion(animation.getKeyFrame(elapsedTime));
        batch.draw(animation.getKeyFrame(elapsedTime,true), sprite.getX(), sprite.getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        sprite.setPosition(getX(),getY());
        x = getX();
        y = getY();
    }

    public static Rectangle getRectangle(){
        return new Rectangle(x + 30,y,width,height);
    }
}
