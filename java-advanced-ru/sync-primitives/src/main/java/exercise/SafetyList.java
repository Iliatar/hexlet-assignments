package exercise;

class SafetyList {
    // BEGIN
    private static int INITIAL_CAPACITY = 5;
    private int[] array = new int[INITIAL_CAPACITY];
    private int size = 0;

    public synchronized void add(int item) {
        if (size == array.length) {
            int[] newArray = new int[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[size++] = item;
    }

    public int get(int index) {
        return array[index];
    }

    public int getSize() {
        return size;
    }
    // END
}
