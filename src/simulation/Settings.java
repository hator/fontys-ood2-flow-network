package simulation;

public class Settings {
    public float currentFlow;
    public float maxFlow;
    public Float splitRatio;

    public Settings(float currentFlow, float maxFlow, Float splitRatio) {
        this.currentFlow = currentFlow;
        this.maxFlow = maxFlow;
        this.splitRatio = splitRatio;
    }
}
