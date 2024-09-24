package exercise;

// BEGIN
public class MinThread extends Thread {
    private int[] array;
    private int result;

    public MinThread(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        result = array[0];
        for (int num : array) {
            result = Math.min(result, num);
        }

        System.out.println("Min number in array is " + result);
    }

    public int getResult() {
        return result;
    }
}
// END
