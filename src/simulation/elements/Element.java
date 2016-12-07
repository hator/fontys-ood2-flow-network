package simulation.elements;

import simulation.Settings;

abstract class Element {
    float currentFlow;
    float maxFlow;
    private boolean needsRecalculation = true;

    float getFlow() {
        if(needsRecalculation) {
            currentFlow = recalculateFlow();
            needsRecalculation = false;
        }
        return currentFlow;
    }

    abstract float recalculateFlow();

    Settings getSettings() {
        return new Settings(currentFlow, maxFlow, null);
    }

    void applySettings(Settings settings) {
        this.currentFlow = settings.currentFlow;
        this.maxFlow = settings.maxFlow;
        this.needsRecalculation = true;
    }

}
