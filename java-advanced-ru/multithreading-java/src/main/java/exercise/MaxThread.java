package exercise;

// BEGIN
public class MaxThread extends Thread {
    private int[] array;
    private int result;

    public MaxThread(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        result = array[0];
        for (int num : array) {
            result = Math.max(result, num);
        }

        System.out.println("Max number in array is " + result);
    }

    public int getResult() {
        return result;
    }
}
// END
