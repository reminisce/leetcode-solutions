/**
 * Created on 8/14/16.
 * (L)
 * Suppose you have a long flowerbed
 * in which some of the plots are planted and
 * some are not. However, flowers cannot
 * be planted in adjacent plots -
 * they would compete for water and
 * both would die. Given a flowerbed
 * (represented as an array containing
 * booleans), return if a given number of
 * new flowers can be planted in it
 * without violating the no-adjacent-flowers rule.
 */

public class PlantFlowers {

    public boolean canPlant(int[] flowerbed, int n) {
        if (flowerbed == null || flowerbed.length == 0) return false;
        if (n <= 0) return true;

        int cnt = 0;

        if (flowerbed.length == 1) {
            return flowerbed[0] == 0 && n == 1;
        }

        if (flowerbed.length == 2) {
            if (flowerbed[0] == 1 || flowerbed[1] == 1) return false;
            return n == 1;
        }

        for (int i = 0; i < flowerbed.length; ++i) {
            if (flowerbed[i] == 0) {
                if ((i == 0 && flowerbed[i+1] == 0)
                        || (i == flowerbed.length - 1 && flowerbed[i-1] == 0)
                        || (flowerbed[i-1] == 0 && flowerbed[i+1] == 0)) {
                    flowerbed[i] = 1;
                    if (++cnt == n) return true;
                }
            }
        }
        return false;
    }
}
