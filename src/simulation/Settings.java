package simulation;

public class Settings {
    public float currentFlow = 0;
    public float maxFlow = 10;
    public Float splitRatio = null;

    public Settings() {
    }

    public Settings(float currentFlow, float maxFlow, Float splitRatio) {
        this.currentFlow = currentFlow;
        this.maxFlow = maxFlow;
        this.splitRatio = splitRatio;
    }
}
