public class FibonacciMain {
    public static long fibonacci(long number) {
        if ((number == 0) || (number == 1)) {
            return number;
        } else {
            // recursion step
            return fibonacci(number - 1) + fibonacci(number - 2);
        }
    }

    public static void main(String args[]) {
        for (int count = 0; count <= 10; count++) {
            System.out.println("Fibonacci of " + count + " is " + fibonacci(count));
        }
    }
}   
