package com.kochkina;

import java.util.Random;
import java.util.function.Consumer;

public class Sorting {

    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        double timeForBubble = timeForSorting(3, Sorting::sortBubble);
        System.out.println(timeForBubble);
        double timeForSelection = timeForSorting(3, Sorting::sortSelection);
        System.out.println(timeForSelection);
        double timeForInsertion = timeForSorting(3, Sorting::sortInsertion);
        System.out.println(timeForInsertion);
    }

    private static double timeForSorting(int iteration, Consumer <int[]> sorting) {
        double sum = 0;
        for (int i = 0; i < iteration; i++) {
            int[] arr = generateArray(100_000);
            long start = System.currentTimeMillis();
            sorting.accept(arr);
            long end = System.currentTimeMillis() - start;
            sum += end;
        }
        return sum/iteration;
    }

    private static int[] generateArray(int size){
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = RANDOM.nextInt(-50, 50);
        }
        return arr;
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
    // Пузырьковая сортировка
    public static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }
    // Сортировка выбором
    public static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }
    // Сортировка вставкой
    public static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
}
