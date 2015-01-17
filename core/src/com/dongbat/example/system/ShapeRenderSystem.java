package com.dongbat.example.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.World;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.dongbat.example.component.Physics;
import com.dongbat.example.component.State;
import com.dongbat.example.util.EcsUtil;

@Wire
public class ShapeRenderSystem extends EntityProcessingSystem {

	private ComponentMapper<Physics> physicsMapper;
	private ComponentMapper<State> statesMapper;
	private ShapeRenderer renderer;
	private OrthographicCamera camera;

	public ShapeRenderSystem() {
		// super(Aspect.getAspectForAll(Physics.class, State.class));
		// super(Aspect.getAspectForOne(Physics.class));
		super(Aspect.getAspectForOne(State.class));
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

	int countTime = 0;

	@Override
	protected void process(Entity e) {
		countTime++;
		Physics physics = physicsMapper.get(e);
		State state = statesMapper.get(e);
		renderer.begin(ShapeType.Filled);
		renderer.setColor(Color.WHITE);
		if (physics != null)
			renderer.circle(physics.getPosition().x, physics.getPosition().y,
					10);
		renderer.setColor(Color.GREEN);
		if (state != null)
			renderer.circle(state.getTarget().x, state.getTarget().y, 10);
		renderer.end();
	}

	private void removePhysic(Entity e, ShapeRenderer render) {

		World world2 = EcsUtil.getWorld();

	}

}
