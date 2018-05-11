package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

/**
 * Created by ChitraRamaswamy on 6/7/17.
 */

public class Fireball extends Actor{
    static boolean collision = false;
    static int score;
    boolean exploding = false;
    float elapsedTime;
    Texture texture = new Texture(Gdx.files.internal("sprite2/fireball.png"));
    Sprite sprite = new Sprite(new Texture(Gdx.files.internal("sprite2/fireball.png")));
    TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("spritesheet/fireball.atlas"));
    //TextureAtlas textureAtlas2 = new TextureAtlas(Gdx.files.internal("spritesheet/fireballExplosion.atlas"))           ;
    Animation<TextureRegion> animation = new Animation(1/6f, textureAtlas.getRegions());
    //Animation<TextureRegion> animation2 = new Animation(1/9f, textureAtlas2.getRegions());
    static float width, height, x, y;


    public Fireball(float f){
        elapsedTime = f;
        setX((float)((Math.random()*500)+50));
        setY(Gdx.graphics.getHeight());
        setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());
        width = texture.getWidth();
        height = texture.getHeight();
        animation.setPlayMode(Animation.PlayMode.LOOP);

        SequenceAction sequence = new SequenceAction();

        MoveToAction moveToAction = new MoveToAction();
        moveToAction.setPosition(getX(), 0);
        moveToAction.setDuration(2f);

        sequence.addAction(moveToAction);
        sequence.addAction(Actions.removeActor());

        this.addAction(sequence);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        elapsedTime += Gdx.graphics.getDeltaTime();
        sprite.setRegion(animation.getKeyFrame(elapsedTime));
        BitmapFont font = new BitmapFont();

        //batch.draw(animation.getKeyFrame(elapsedTime), sprite.getX(), sprite.getY());
        //super.draw(batch, parentAlpha);
        sprite.draw(batch);
        font.getData().setScale(3f, 3f);
        font.draw(batch, "Score: " + score, 30,45);

    }

    @Override
    public void act(float delta) {
        elapsedTime += Gdx.graphics.getDeltaTime();
        super.act(delta);
        sprite.setPosition(getX(), getY());
        x = getX();
        y = getY();
        if(collision){
            exploding = true;
            this.clearActions();
            this.sprite = new Sprite(new Texture(Gdx.files.internal("sprite3/fireball_hit_0001.png")));
            TextureAtlas newTextureAtlas = new TextureAtlas(Gdx.files.internal("spritesheet/fireballExplosion.atlas"));
            animation = new Animation(1/9f, newTextureAtlas.getRegions());
            animation.setPlayMode(Animation.PlayMode.NORMAL);
            collision = false;
        }
        if(animation.getPlayMode().equals(Animation.PlayMode.NORMAL)) {
            if (animation.isAnimationFinished(elapsedTime)) {
                this.addAction(Actions.removeActor());
            }
        }
    }

    public static Rectangle getRectangle(){
        return new Rectangle(x,y,width,height);
    }

    public static void explode(){
        collision = true;
    }

    public static void setScore(int x){
        score = x;
    }
}
