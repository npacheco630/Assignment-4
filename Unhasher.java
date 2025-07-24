public class Unhasher {
    public static int[] UNHASH(int[] digest) {
        int[] message = new int[digest.length];
        int prev = 0; // message[-1] = 0

        for (int i = 0; i < digest.length; i++) {
            boolean found = false;
            for (int m = 0; m < 256; m++) {
                int result = ((129 * m) ^ prev) % 256;
                if (result == digest[i]) {
                    message[i] = m;
                    prev = m;
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new IllegalArgumentException("No valid byte found for digest[" + i + "] = " + digest[i]);
            }
        }
        return message;
    }

    // Test the function
    public static void main(String[] args) {
        int[] digest1 = {0, 129, 3, 129, 7, 129, 3, 129, 15, 129, 3, 129, 7, 129, 3, 129};
        int[] digest2 = {0, 129, 5, 141, 25, 137, 61, 149, 113, 145, 53, 157, 233, 185, 109, 165};
        int[] digest3 = {199, 168, 128, 11, 68, 106, 165, 13, 195};

        printMessage(UNHASH(digest1)); // expected: 0-15
        printMessage(UNHASH(digest2)); // expected: 0,1,4,...,225
        printMessage(UNHASH(digest3)); // expected: 71,111,...,33
    }

    public static void printMessage(int[] message) {
        // Print as numbers
        System.out.print("Output: ");
        for (int b : message) {
            System.out.print(b + " ");
        }
        System.out.println("\n");
    }
}
