package CLI;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		Terminal t = new Terminal();
		Parser p = new Parser();
		while(true){
			t.display();
			String cmd = sc.nextLine();
			p.parse(cmd);
		}
    }

}
