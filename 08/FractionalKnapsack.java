import java.util.*;

class Item {
    int weight, value;
    double ratio;

    Item(int w, int v) {
        weight = w;
        value = v;
        ratio = (double) v / w;
    }
}

public class FractionalKnapsack {

    static double knapsack(Item[] items, int capacity) {

        // Sort items by value/weight ratio (descending)
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        double totalValue = 0;

        for (Item item : items) {
            if (capacity == 0) break;

            if (item.weight <= capacity) {
                totalValue += item.value;     
                capacity -= item.weight;
            } else {
                totalValue += item.ratio * capacity;
                capacity = 0;
            }
        }
        return totalValue;
    }

    public static void main(String[] args) {

        // 5 items (weight, value)
        Item[] items = {
            new Item(10, 60),
            new Item(20, 90),
            new Item(30, 120),
            new Item(25, 75),
            new Item(15, 45)
        };

        int capacity = 50;

        double maxValue = knapsack(items, capacity);
        System.out.println("Maximum value = " + maxValue);
    }
}
