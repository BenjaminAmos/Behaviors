/*
 * Copyright 2015 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.minion.move;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.entitySystem.event.Event;
import org.terasology.logic.behavior.ActionName;
import org.terasology.logic.behavior.core.Actor;
import org.terasology.logic.behavior.core.BaseAction;
import org.terasology.logic.behavior.core.BehaviorEvent;
import org.terasology.logic.behavior.core.BehaviorState;
import org.terasology.logic.characters.AliveCharacterComponent;
import org.terasology.logic.characters.CharacterMoveInputEvent;
import org.terasology.logic.characters.CharacterMovementComponent;
import org.terasology.math.geom.Vector3f;

/**
 * Trigger a single jump into the air.<br/>
 * <br/>
 * <b>SUCCESS</b>: when the actor is grounded after the jump again.<br/>
 * <br/>
 * Auto generated javadoc - modify README.markdown instead!
 */
@ActionName("jump")
public class JumpNode extends BaseAction {

    @Override
    public void construct(Actor actor) {


        long delta = (long) (actor.getDelta() * 1000);
        Event event = new CharacterMoveInputEvent(0, 0, 0, new Vector3f(), false, false, true, delta);
        actor.getEntity().send(event);

    }

    @Override
    public BehaviorState modify(Actor actor, BehaviorState result) {


        // TODO early implementation, this'll be changed to something more sane
        BehaviorEvent event = actor.getEvents().get("interrupt");
        if (event != null) {
            return BehaviorState.FAILURE;
        }

        return actor.getComponent(CharacterMovementComponent.class).grounded ? BehaviorState.SUCCESS : BehaviorState.RUNNING;
    }

}
