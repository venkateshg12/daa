import java.util.Arrays;

class MinHeap {
    int[] heap;
    int size;

    MinHeap(int capacity) {
        heap = new int[capacity];
        size = 0;
    }

    // Insert into min heap
    void insert(int val) {
        heap[size] = val;
        int current = size;
        size++;

        // heapify up
        while (current > 0 && heap[current] < heap[(current - 1) / 2]) {
            swap(current, (current - 1) / 2);
            current = (current - 1) / 2;
        }
    }

    // Delete any element by index
    void delete(int index) {
        if (index < 0 || index >= size) return;

        heap[index] = heap[size - 1];
        size--;
        heapifyDown(index);
    }

    void heapifyDown(int i) {
        int smallest = i;

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && heap[left] < heap[smallest])
            smallest = left;

        if (right < size && heap[right] < heap[smallest])
            smallest = right;

        if (smallest != i) {
            swap(i, smallest);
            heapifyDown(smallest);
        }
    }

    void swap(int a, int b) {
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    void display() {
        System.out.println("Min Heap: " + Arrays.toString(Arrays.copyOf(heap, size)));
    }
}

class MaxHeap {
    int[] heap;
    int size;

    MaxHeap(int capacity) {
        heap = new int[capacity];
        size = 0;
    }

    // Insert into max heap
    void insert(int val) {
        heap[size] = val;
        int current = size;
        size++;

        // heapify up
        while (current > 0 && heap[current] > heap[(current - 1) / 2]) {
            swap(current, (current - 1) / 2);
            current = (current - 1) / 2;
        }
    }

    // Delete any element by index
    void delete(int index) {
        if (index < 0 || index >= size) return;

        heap[index] = heap[size - 1];
        size--;
        heapifyDown(index);
    }

    void heapifyDown(int i) {
        int largest = i;

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && heap[left] > heap[largest])
            largest = left;

        if (right < size && heap[right] > heap[largest])
            largest = right;

        if (largest != i) {
            swap(i, largest);
            heapifyDown(largest);
        }
    }

    void swap(int a, int b) {
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    void display() {
        System.out.println("Max Heap: " + Arrays.toString(Arrays.copyOf(heap, size)));
    }
}

public class HeapDemo {
    public static void main(String[] args) {

        MinHeap min = new MinHeap(20);
        MaxHeap max = new MaxHeap(20);

        // Insert values
        int[] arr = {12,30,16,3,7};

        for (int val : arr) {
            min.insert(val);
            max.insert(val);
        }

        // Display both heaps
        min.display();
        max.display();

        System.out.println("\nDeleting element at index 2...");
        
        min.delete(0);  
        max.delete(0);

        // Display after deletion
        min.display();
        max.display();
    }
}
