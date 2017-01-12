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

    public boolean isValid() {
        boolean splitValid = true;

        if(splitRatio != null)
        {
            splitValid = splitRatio >= 0.0 && splitRatio <= 1.0;
        }

        return maxFlow >= 0.0 && currentFlow >= 0.0 && splitValid;
    }

    public void applyValues(Settings settings){
        this.currentFlow = settings.currentFlow;
        this.maxFlow = settings.maxFlow;
        this.splitRatio = settings.splitRatio;
    }

    public static Settings getDefault() {
        return new Settings(0, 10, null);
    }
}
