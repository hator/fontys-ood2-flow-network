package util;

public class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getDistance(Point point)
    {
        return Math.sqrt(Math.pow(point.x - this.x, 2) +
                Math.pow(point.y - this.y, 2));
    }

    public boolean inBoundingSegment(Point segmentA, Point segmentB, int clickRadius){
        double distance;
        boolean inSectionBounds;

        if(segmentA.y == segmentB.y) {
            distance = Math.abs(y - segmentA.y);
            inSectionBounds = betweenNumbers(x, segmentA.x, segmentB.x);
        }
        else if(segmentA.x == segmentB.x) {
            distance = Math.abs(x - segmentA.x);
            inSectionBounds = betweenNumbers(y, segmentA.y, segmentB.y);
        }
        else {
            double a = (segmentA.y - segmentB.y)/(segmentA.x - segmentB.x);
            double b = segmentA.y - a*segmentA.x;

            double perA = -1/a;
            double b1 = segmentA.y - perA*segmentA.x;
            double b2 = segmentB.y - perA*segmentB.x;
            double sqrRoot = Math.sqrt(Math.pow(a, 2) + Math.pow(-1, 2));

            distance = Math.abs(a*x + -1*y + b)/sqrRoot;
            inSectionBounds = betweenNumbers(y, x*perA + b1, x*perA + b2);
        }

        if (distance <= clickRadius && inSectionBounds) {
            return true;
        }
        return false;
    }

    private boolean betweenNumbers(double value, double a, double b) {
        return value >= Math.min(a, b) && value <= Math.max(a, b);
    }
}
