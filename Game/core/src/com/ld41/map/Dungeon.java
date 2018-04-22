package com.ld41.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.ld41.core.Button_;
import com.ld41.core.Screen_;
import com.ld41.main.Game;

public class Dungeon extends Screen_{

    SpriteBatch batch, GUIbatch;
    Button_ generateButton;
    OrthographicCamera camera;
    Vector3 mPos;
    MapGeneration generator;
    int[][] map;
    Texture wall;

    boolean wPressed, aPressed, dPressed, sPressed;

    public Dungeon(Game game) {

        super(game);

    }

    @Override
    public void show() {

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        GUIbatch = new SpriteBatch();
        generateButton = new Button_(64, 64, "generate");
        generateButton.onClick(() -> generate());
        wall = new Texture("map/wall.png");
        generator = new MapGeneration();
        map = new int[100][100];
        generate();
        camera.position.x = generator.character.getX() - 16;
        camera.position.y = generator.character.getY() - 16;

        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean keyDown(int keycode) {

                if (keycode == Input.Keys.W) {

                    wPressed = true;

                }

                if (keycode == Input.Keys.S) {

                    sPressed = true;

                }

                if (keycode == Input.Keys.A) {

                    aPressed = true;

                }

                if (keycode == Input.Keys.D) {

                    dPressed = true;

                }

                return false;

            }

            @Override
            public boolean keyUp(int keycode) {

                if (keycode == Input.Keys.W) {

                    wPressed = false;

                }

                if (keycode == Input.Keys.S) {

                    sPressed = false;

                }

                if (keycode == Input.Keys.A) {

                    aPressed = false;

                }

                if (keycode == Input.Keys.D) {

                    dPressed = false;

                }

                return false;

            }

            @Override
            public boolean scrolled(int amount) {

                if (amount == 1) {

                    camera.zoom += 1 * 0.2f;

                }

                if (amount == -1) {

                    camera.zoom -= 1 * 0.2f;

                }

                return false;

            }
        });

    }

    @Override
    public void render(float delta) {

        camera.position.x = (generator.character.getX()) - 16;
        camera.position.y = (generator.character.getY()) - 16;

        if (aPressed) {

            /*if (xOnTile(generator.character.getX())) {

                System.out.println("TEST");

                int tileOnLeft = map[(generator.character.getX() / 32) - 1][(generator.character.getY() / 32)];

                if (tileOnLeft == 0) {

                    generator.character.setX(generator.character.getX() - 1);

                }

            } */

            //if ()

        }

        batch.setProjectionMatrix(camera.combined);
        camera.update();

        batch.begin();

        for (int i = 0; i < 75; i++) {

            for (int j = 0; j < 75; j++) {

                switch (map[i][j]) {

                    case 1:

                        batch.draw(wall, i * wall.getWidth(), j * wall.getHeight());
                        break;

                }

            }

        }

        generator.character.render(batch);

        batch.end();

        mPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mPos);

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {

            generate();

        }

       /* GUIbatch.begin();
        generateButton.render(GUIbatch);
        generateButton.logic(mPos);
        GUIbatch.end();*/
        // Not right but idk


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
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void generate() {

        System.out.println("Generating new map");
        map = generator.GenerateMap(75, 75, 10);

    }

    public boolean xOnTile(int x) {

        if (x % 32 == 0) {

            return true;

        } else {

            return  false;

        }

    }

    public boolean yOnTile(int y) {

        if (y % 32 == 0) {

            return true;

        } else {

            return  false;

        }

    }

}
