import java.util.Scanner;

/**
 * Test
 *
 * @author xccui
 * @date 2019-10-15.
 */
public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line;
        while(scanner.hasNext()) {
            line = scanner.nextLine();
            if (line.equals("exit")) {
                break;
            }
            System.out.println(line);
        }
    }
}
