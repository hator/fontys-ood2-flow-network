package simulation.elements;

abstract class InputOutput {
    float currentFlow;
    private boolean needsRecalculation = true;

    float getFlow() {
        if(needsRecalculation) {
            recalculateFlow();
            needsRecalculation = false;
        }
        return currentFlow;
    }

    protected abstract void recalculateFlow();
}
