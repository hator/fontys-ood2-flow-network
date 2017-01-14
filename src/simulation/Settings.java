package simulation;

public class Settings implements java.io.Serializable{
    public float getCurrentFlow() {
        return currentFlow;
    }

    public void setCurrentFlow(float currentFlow) {
        this.currentFlow = (float)Math.floor(currentFlow*1000) / 1000f;
    }

    public float getMaxFlow() {
        return maxFlow;
    }

    public void setMaxFlow(float maxFlow) {
        this.maxFlow = maxFlow;
    }

    private float currentFlow;
    private float maxFlow;
    public Float splitRatio;
    public boolean generatesFlow = false;

    public Settings(float currentFlow, float maxFlow, Float splitRatio) {
        this.currentFlow = currentFlow;
        this.maxFlow = maxFlow;
        this.splitRatio = splitRatio;
    }

    public boolean isValid() {
        boolean splitValid = true;

        if (splitRatio != null) {
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

    public static Settings forTool(Tool tool) {
        Settings settings = Settings.getDefault();

        if (tool == Tool.Select || tool == Tool.Remove) {
            settings = null;
        } else if (tool == Tool.AddAdjustableSplitter) {
            settings.splitRatio = 0.5f;
        } else if (tool == Tool.AddPump) {
            settings.generatesFlow = true;
        }
        return settings;
    }




}
