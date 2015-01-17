package com.dongbat.example.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.dongbat.example.component.Physics;
import com.dongbat.example.component.State;

@Wire
public class ShapeRenderSystem extends EntityProcessingSystem {

	private ComponentMapper<Physics>	physicsMapper;
	private ComponentMapper<State>		statesMapper;
	private ShapeRenderer				renderer;
	private OrthographicCamera			camera;

	public ShapeRenderSystem() {
		super(Aspect.getAspectForAll(Physics.class));
	}

	@Override
	protected void initialize() {
		renderer = new ShapeRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
	}

	@Override
	protected void begin() {
		camera.update();
		renderer.setProjectionMatrix(camera.combined);
	}

	@Override
	protected void dispose() {
		renderer.dispose();
	}

	int	countTime	= 0;

	@Override
	protected void process(Entity e) {
		countTime++;
		Physics physics = physicsMapper.get(e);
		State state = statesMapper.get(e);
		renderer.begin(ShapeType.Filled);
		renderer.setColor(Color.WHITE);
		renderer.circle(physics.getPosition().x, physics.getPosition().y, 10);
		if (countTime % 50 < 25) {
			renderer.setColor(Color.GREEN);
			if(state != null) renderer.circle(state.getTarget().x, state.getTarget().y, 10);
		}
		renderer.end();
	}
}
