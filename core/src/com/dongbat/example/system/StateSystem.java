package com.dongbat.example.system;

import com.artemis.Aspect;
import com.artemis.ComponentManager;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.dongbat.example.component.State;

@Wire
public class StateSystem extends EntityProcessingSystem {
	private ComponentMapper<State> stateMapper;

	public StateSystem() {
		super(Aspect.getAspectForAll(State.class));
	}

	@Override
	protected void process(Entity e) {
		State state = stateMapper.get(e);
//		state.getTarget().add(-1, -1);
	}
}
