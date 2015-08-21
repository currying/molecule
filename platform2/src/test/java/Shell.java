import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Shell {
	public static void main(String[] args) throws java.io.IOException {
		String commandLine;
		List<String> input;
		List<String> history = new ArrayList<String>();
		BufferedReader console = new BufferedReader(new InputStreamReader(
				System.in));

		while (true) {
			System.out.print("jsh>");
			commandLine = console.readLine();
			input = new ArrayList<String>();

			if (commandLine.equals("!!")) {
				if (history.size() > 0)
					commandLine = history.get(history.size() - 1);
				else
					continue;
			} else {
				history.add(commandLine);
			}

			if (commandLine.equalsIgnoreCase("history")) {
				for (String command : history) {
					System.out.println(command);
				}
				continue;
			}

			if (commandLine.equalsIgnoreCase("exit")) {
				System.out.println("See you next time and have a nice day!");
				System.exit(0);
			}

			for (String command : commandLine.split(" ")) {
				input.add(command);
			}
			ProcessBuilder pb = new ProcessBuilder(input);
			Process process = pb.start();

			InputStreamReader isr = new InputStreamReader(
					process.getInputStream());
			BufferedReader br = new BufferedReader(isr);

			String line;
			while ((line = br.readLine()) != null)
				System.out.println(line);
			br.close();
		}
	}

}
