package com.dongbat.example.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.World;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.dongbat.example.component.Physics;
import com.dongbat.example.component.State;
import com.dongbat.example.util.EcsUtil;

@Wire
public class PhysicsSystem extends EntityProcessingSystem {

	private ComponentMapper<Physics> physicsMapper;

	public PhysicsSystem() {
		super(Aspect.getAspectForAll(Physics.class));
	}

	@Override
	protected void process(Entity e) {
		Physics physics = physicsMapper.get(e);
		Vector2 position = physics.getPosition();
		Vector2 velocity = physics.getVelocity();
		position.add(velocity.cpy().scl(world.delta));
	}

}
