import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class signup {
	static HashMap<String, String> map = new HashMap<String, String>();
	static HashMap<String, String>[] first = new HashMap[7];
	static HashMap<String, String>[] second = new HashMap[7];
	static String region = "";
	static String[] qs = { "1. What city were you born in?", "2. What is your oldest sibling's middle name?",
			"3. What was the first concert you attended?", "4. What was the make and model of your first car?",
			"5. In what city or town did your parents meet?", "6. What was the name of your first pet?",
			"7. What is your mother's maiden name?" };


	//get functions
	public static HashMap<String, String> getMap() {
		return map;
	}
	public static HashMap<String, String>[] getFirst() {
		return first;
	}
	public static HashMap<String, String>[] getSecond() {
		return second;
	}
	public static String getRegion() {
		return region;
	}
	public static String[] getQs() {
		return qs;
	}
	
	//get ip address of user
	public static String getIPAddress() {
		String ip = "";
		try {
			URL url_name = new URL("https://checkip.amazonaws.com/");
			
			Scanner scanner = new Scanner(url_name.openStream());
			ip = scanner.nextLine().trim();
		}
		catch (Exception e) {
			ip = "";
			System.out.println(e.getMessage());
		}
		return ip;
	}

	//main
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);

		//title
		System.out.println("Password Protector");
		System.out.println("Sign up");

		//get username
		System.out.println("Enter Username: ");
		String username = scanner.nextLine();
		while (map.containsKey(username)) {
			System.out.println("Username is taken");
			System.out.println("Enter Username: ");
			username = scanner.nextLine();
		}
		System.out.println("Enter Password: ");
		String password = scanner.nextLine();

		//common passwords - https://github.com/danielmiessler/SecLists/blob/master/Passwords/2020-200_most_used_passwords.txt
		boolean weak = false;
		File file = new File("commonpasswords.txt");
		Scanner fileScanner = new Scanner(file);
		while (!weak && fileScanner.hasNextLine()) {
			String common = fileScanner.nextLine();
			if (password.equals(common)) {
				System.out.println("Weak");
				weak = true;
			}
		}
		
		//password strength
		if (!weak) {
			Set<Character> set = new HashSet<Character>(Arrays.asList('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+'));
			boolean hasLower = false, hasUpper = false, hasDigit = false, specialChar = false;
			for (char i : password.toCharArray()) {
				if (Character.isLowerCase(i))
					hasLower = true;
				if (Character.isUpperCase(i))
					hasUpper = true;
				if (Character.isDigit(i))
					hasDigit = true;
				if (set.contains(i))
					specialChar = true;
			}
			if (hasDigit && hasLower && hasUpper && specialChar && (password.length() >= 8)) {
				System.out.println("Strong");
			}
			else if ((hasLower || hasUpper || specialChar) && (password.length() >= 6)) {
				System.out.println("Moderate");
			}
			else {
				System.out.println("Weak");
			}
		}

		//put username password in map
		map.put(username, password);
		//System.out.println(map);

		//ip address - https://www.ipinfodb.com/api
		//System.out.println(getIPAddress());
		String ipAddress = getIPAddress();
		try (java.util.Scanner s = new java.util.Scanner(new java.net.URL(
				"https://api.ipinfodb.com/v3/ip-city?key=cc07ab6d4c0dbdd82bda6aee847aad3247c92633101fb7d309776d9cbc71e7df&ip="+ipAddress)
				.openStream(),
				"UTF-8").useDelimiter("\\A")) {
			region = s.next();
			System.out.println(region);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}

		//initialize questions arrays
		for (int i = 0; i < 7; i++) {
			first[i] = new HashMap<String, String>();
		}
		for (int i = 0; i < 7; i++) {
			second[i] = new HashMap<String, String>();
		}
		
		//security questions - https://www.okta.com/blog/2021/03/security-questions/
		//choice 1
		int choice1 = 1;
		String answer1 = "";
		while (choice1 >= 1 && choice1 <= 7) {
			System.out.println("Security Questions (Enter the number of your first choice)");
			for (int i = 0; i < qs.length; i++) {
				System.out.println(qs[i]);
			}
			choice1 = scanner.nextInt();
			if (choice1 < 1 || choice1 > 7) {
				System.out.println("Error");
				choice1 = 1;
			} 
			else {
				System.out.println(qs[choice1 - 1].substring(3));
				answer1 = scanner.next();
				first[choice1 - 1].put(username, answer1);
				break;
			}
			//System.out.println(questions[0]);
		}

		//choice 2
		int choice2 = 1;
		String answer2 = "";
		while (choice2 >= 1 && choice1 <= 7) {
			System.out.println("Security Questions (Enter the number of your second choice)");
			for (int i = 0; i < qs.length; i++) {
				System.out.println(qs[i]);
			}
			choice2 = scanner.nextInt();
			if (choice1 == choice2) {
				System.out.println("You have already chosen this question");
				choice2 = 1;
			} 
			else if (choice2 < 1 || choice2 > 7) {
				System.out.println("Error");
				choice2 = 1;
			} 
			else {
				System.out.println(qs[choice2 - 1].substring(3));
				answer2 = scanner.next();
				second[choice2 - 1].put(username, answer2);
				break;
			}
			//System.out.println(questions[1]);
		}

		//save user info
		FileWriter pw = new FileWriter("userinfo.csv", true);
		pw.append(username);
		pw.append(",");
		pw.append(password);
		pw.append(",");
		pw.append("" + choice1);
		pw.append(",");
		pw.append(answer1);
		pw.append(",");
		pw.append("" + choice2);
		pw.append(",");
		pw.append(answer2);
		pw.append(",");
		pw.append(region);
		pw.append("\n");
		pw.flush();
		pw.close();
		
		System.out.println("You have signed up!");
		
		scanner.close();
	}
}
