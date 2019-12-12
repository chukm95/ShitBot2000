package behaviors;

public class IdleBehavior extends Behavior {

    public IdleBehavior(){
        super(null);
    }

    @Override
    public void OnActivate() {
nextBehavior =
        new LineFollowBehavior(
                new TurnBehavior(true,
                        new LineFollowBehavior(
                                new TurnBehavior(true,
                                        new LineFollowBehavior(
                                                new TurnBehavior(true,
                                                        new LineFollowBehavior(
                                                                new TurnBehavior(false,
                                                                        new LineFollowBehavior(
                                                                                new TurnBehavior(false,
                                                                                        new LineFollowBehavior(
                                                                                                new TurnBehavior(false,
                                                                                                        new LineFollowBehavior(
                                                                                                                new TurnBehavior(false,
                                                                                                                        new LineFollowBehavior(
                                                                                                                                new TurnBehavior(true,
                                                                                                                                        new IdleBehavior())
                                                                                                                        ))
                                                                                        ))
                                                                        ))
                                                        )
                                                ))))))));
getShitBot().SwitchStates(nextBehavior);
    }

    @Override
    public void Update(double deltaTime) {

    }

    @Override
    public void OnDeactivate() {

    }
}
