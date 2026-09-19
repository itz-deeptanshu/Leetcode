class Solution {
public:
    bool checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Find the closest x and y coordinates on the rectangle to the circle center
        int closestX = max(x1, min(xCenter, x2));
        int closestY = max(y1, min(yCenter, y2));
        
        // Calculate the distance from the closest point to the circle center
        long long distX = xCenter - closestX;
        long long distY = yCenter - closestY;
        
        // Check if the distance is less than or equal to the radius
        return (distX * distX + distY * distY) <= (long long)radius * radius;
    }
};