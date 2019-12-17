package behaviors;

public class InfiniteEightBehavior extends Behavior {

    public InfiniteEightBehavior(){
    }

    @Override
    public void OnActivate() {
        setNextBehavior(new LineFollowBehavior())
                .setNextBehavior(new TurnLeftBehavior())
                .setNextBehavior(new LineFollowBehavior())
                .setNextBehavior(new TurnLeftBehavior())
                .setNextBehavior(new LineFollowBehavior())
                .setNextBehavior(new TurnLeftBehavior())
                .setNextBehavior(new LineFollowBehavior())
                .setNextBehavior(new TurnRightBehavior())
                .setNextBehavior(new LineFollowBehavior())
                .setNextBehavior(new TurnRightBehavior())
                .setNextBehavior(new LineFollowBehavior())
                .setNextBehavior(new TurnRightBehavior())
                .setNextBehavior(new LineFollowBehavior())
                .setNextBehavior(new TurnRightBehavior())
                .setNextBehavior(new LineFollowBehavior())
                .setNextBehavior(new TurnLeftBehavior())
                .setNextBehavior(new InfiniteEightBehavior());
        switchToNextBehavior();
    }

    @Override
    public void Update(double deltaTime) {

    }

    @Override
    public void OnDeactivate() {

    }
}
