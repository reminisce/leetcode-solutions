import java.util.Arrays;

/**
 * Created on 7/17/16.
 * Given a matrix h and
 * the departure city, where h[i][j]
 * represents the number of holidays
 * of month in city j. You can take
 * flight to travel between cities.
 * Find the path such that you can
 * get the max number of holidays. (G)
 */

public class MaximumCityHolidays {

    public static void main(String[] args) {
        int m = 4, n = 3;
        int[][] holidays = new int[m][n];
        holidays[0][0] = 2;
        holidays[0][1] = 3;
        holidays[0][2] = 1;

        holidays[1][0] = 1;
        holidays[1][1] = 5;
        holidays[1][2] = 4;

        holidays[2][0] = 3;
        holidays[2][1] = 1;
        holidays[2][2] = 2;

        holidays[3][0] = 4;
        holidays[3][1] = 2;
        holidays[3][2] = 1;

        MaximumCityHolidays app = new MaximumCityHolidays();
        System.out.println(app.maxHolidays(holidays));
    }

    /**
     * If understand correctly, this is a slight variant of
     * Paint House. The only difference here is we seek
     * for max instead of min objective function. The month
     * and city are similar to house and color, repsectively,
     * in that problem.
     * maxHolidays[i][j] = holidays[i][j] + max(maxHolidays[i-1][k])
     * for k = 0, 1,..., j-1, j+1,..., n-1
     * @param holidays
     * @return
     */
    public int maxHolidays(int[][] holidays) {
        int m = holidays.length;
        int n = holidays[0].length;
        int[][] maxHolidays = new int[m][n];
        // itinerary[i][j] indicates the city id
        // from where you fly in month i-1
        // to city j in month i
        int[][] itinerary = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0) {
                    maxHolidays[i][j] = holidays[i][j];
                } else {
                    int preMaxHolidays = Integer.MIN_VALUE;
                    int cityId = -1;
                    for (int k = 0; k < n; ++k) {
                        if (k == j) continue;
                        if (preMaxHolidays < maxHolidays[i-1][k]) {
                            preMaxHolidays = maxHolidays[i-1][k];
                            cityId = k;
                        }
                    }
                    maxHolidays[i][j] = holidays[i][j] + preMaxHolidays;
                    itinerary[i][j] = cityId;
                }
            }
        }
        int res = Integer.MIN_VALUE;
        int cityId = -1;
        for (int j = 0; j < n; ++j) {
            if (maxHolidays[m-1][j] > res) {
                res = maxHolidays[m-1][j];
                cityId = j;
            }
        }

        int[] stops = new int[m];
        stops[m-1] = cityId;
        for (int i = m - 2; i >= 0; --i) {
            stops[i] = itinerary[i+1][stops[i+1]];
        }

        System.out.println(Arrays.toString(stops));

        return res;
    }
}
