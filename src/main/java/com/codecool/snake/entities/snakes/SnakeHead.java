package com.codecool.snake.entities.snakes;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.enemies.Ogre;
import com.codecool.snake.entities.enemies.Rat;
import com.codecool.snake.entities.powerups.Bomb;
import com.codecool.snake.entities.powerups.Nitro;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.powerups.Stopwatch;
import javafx.geometry.Point2D;

import java.util.Random;


public class SnakeHead extends GameEntity implements Interactable {
    private static final float turnRate = 2;
    private Snake snake;

    public SnakeHead(Snake snake, Point2D position) {
        this.snake = snake;
        setImage(Globals.getInstance().getImage("SnakeHead"));
        setPosition(position);
    }

    public void updateRotation(SnakeControl turnDirection, float speed) {
        double headRotation = getRotate();

        if (turnDirection.equals(SnakeControl.TURN_LEFT)) {
            headRotation = headRotation - turnRate;
        }
        if (turnDirection.equals(SnakeControl.TURN_RIGHT)) {
            headRotation = headRotation + turnRate;
        }

        // set rotation and position
        setRotate(headRotation);
        Point2D heading = Utils.directionToVector(headRotation, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof Enemy) {
            snake.changeHealth(-((Enemy) entity).getDamage());
        }
        if (entity instanceof SimplePowerUp) {
            snake.addPart(4);
        }
        if (entity instanceof Nitro) {
            snake.changeSpeed(0.25f);
        }
        if (entity instanceof Bomb) {
            Enemy.destroyAllEnemies();
        }
        if (entity instanceof Stopwatch) {
            Globals.getInstance().pauseEnemies(120);
        }
        if(entity instanceof Ogre) {
            snake.changeHealth(-((Enemy) entity).getDamage());
        }
        if (entity instanceof Rat) {
            snake.changeHealth(((Enemy) entity).getDamage());
        }
    }

    @Override
    public String getMessage() {
        return "IMMA SNAEK HED! SPITTIN' MAH WENOM! SPITJU-SPITJU!";
    }
}
