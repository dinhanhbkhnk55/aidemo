package com.dongbat.example.component;

import com.artemis.Component;
import com.artemis.ComponentMapper;
import com.artemis.ComponentType;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.utils.Bag;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.dongbat.example.icomponent.IScript;
import com.dongbat.example.util.EcsUtil;

@Wire
public class Script extends Component implements IScript {

	public Script() {
		super();
	}

	@Override
	public void spawn(Entity e) {
		e.getComponent(State.class).setTarget(
				new Vector2(MathUtils.random(-200, 200), MathUtils.random(-200,
						200)));
	}

	@Override
	public void update(Entity e) {
		Physics physic = e.getComponent(Physics.class);
		State state = e.getComponent(State.class);
		while (physic.getPosition().epsilonEquals(state.getTarget(), 1)) {
			spawn(e);
		}
	}
}
