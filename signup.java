import java.util.HashMap;
import java.util.Scanner;

public class signup {
	static HashMap<String, String> map;
	static HashMap<String, String> q1;
	static HashMap<String, String> q2;
	static HashMap<String, String> q3;
	static HashMap<String, String> q4;
	static HashMap<String, String> q5;
	static HashMap<String, String> q6;
	static HashMap<String, String> q7;
	public signup() {
		map = new HashMap<>();
		q1 = new HashMap<>();
		q2 = new HashMap<>();
		q3 = new HashMap<>();
		q4 = new HashMap<>();
		q5 = new HashMap<>();
		q6 = new HashMap<>();
		q7 = new HashMap<>();
	}
	
	public static HashMap<String, String> getMap() {
		return map;
	}
	public static HashMap<String, String> getq1() {
		return q1;
	}
	public static HashMap<String, String> getq2() {
		return q2;
	}
	public static HashMap<String, String> getq3() {
		return q3;
	}
	public static HashMap<String, String> getq4() {
		return q4;
	}
	public static HashMap<String, String> getq5() {
		return q5;
	}
	public static HashMap<String, String> getq6() {
		return q6;
	}
	public static HashMap<String, String> getq7() {
		return q7;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		//title
		System.out.println("Title");
		System.out.println("Sign up");
		
		//put username and password in map
		System.out.println("Enter Username: ");
		String username = scanner.nextLine();
		System.out.println("Enter Password: ");
		String password = scanner.nextLine();
		
		//common passwords and password strength
		
		
		//put username password in map
		map.put(username, password);
		System.out.println(map);
		
		//ip address - https://www.ipinfodb.com/api
		/*
		193.118.53.194 (NL)
		45.146.164.110 (RU)
		104.248.163.61 (GB)
		45.146.164.110 (RU)
		51.158.156.78, (FR)
		*/
		String ip = "";
		try (java.util.Scanner s = new java.util.Scanner(new java.net.URL("https://api.ipinfodb.com/v3/ip-city?key=cc07ab6d4c0dbdd82bda6aee847aad3247c92633101fb7d309776d9cbc71e7df&ip=193.118.53.194").openStream(), "UTF-8").useDelimiter("\\A")) {
			ip = s.next();
			System.out.println(ip);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		
		//security questions - https://www.okta.com/blog/2021/03/security-questions/
		//choice 1
		System.out.println("Security Questions (Enter the number of your first choice)");
		System.out.println("1. What city were you born in?");
		System.out.println("2. What is your oldest sibling's middle name?");
		System.out.println("3. What was the first concert you attended?");
		System.out.println("4. What was the make and model of your first car?");
		System.out.println("5. In what city or town did your parents meet?");
		System.out.println("6. What was the name of your first pet?");
		System.out.println("7. What is your mother's maiden name?");
		int choice1 = scanner.nextInt();
		if (choice1 == 1) {
			System.out.println("What city were you born in?");
			String answer1 = scanner.next();
			q1.put(username, answer1);
		}
		else if (choice1 == 2) {
			System.out.println("What is your oldest sibling's middle name?");
			String answer1 = scanner.next();
			q2.put(username, answer1);
		}
		else if (choice1 == 3) {
			System.out.println("What was the first concert you attended?");
			String answer1 = scanner.next();
			q3.put(username, answer1);
		}
		else if (choice1 == 4) {
			System.out.println("What is your oldest sibling's middle name?");
			String answer1 = scanner.next();
			q4.put(username, answer1);
		}
		else if (choice1 == 5) {
			System.out.println("What is your oldest sibling's middle name?");
			String answer1 = scanner.next();
			q5.put(username, answer1);
		}
		else if (choice1 == 6) {
			System.out.println("What was the name of your first pet?");
			String answer1 = scanner.next();
			q6.put(username, answer1);
		}
		else if (choice1 == 7) {
			System.out.println("What is your mother's maiden name?");
			String answer1 = scanner.next();
			q7.put(username, answer1);
		}
		else {
			System.out.println("Error");
		}
		System.out.println(q1);
		
		//choice 2
		System.out.println("Security Questions (Enter the number of your second choice)");
		System.out.println("1. What city were you born in?");
		System.out.println("2. What is your oldest sibling's middle name?");
		System.out.println("3. What was the first concert you attended?");
		System.out.println("4. What was the make and model of your first car?");
		System.out.println("5. In what city or town did your parents meet?");
		System.out.println("6. What was the name of your first pet?");
		System.out.println("7. What is your mother's maiden name?");
		int choice2 = scanner.nextInt();
		if (choice1 == choice2) {
			System.out.println("You have already chosen this question");
		}
		if (choice2 == 1) {
			System.out.println("What city were you born in?");
			String answer2 = scanner.next();
			q1.put(username, answer2);
		}
		else if (choice2 == 2) {
			System.out.println("What is your oldest sibling's middle name?");
			String answer2 = scanner.next();
			q2.put(username, answer2);
		}
		else if (choice2 == 3) {
			System.out.println("What was the first concert you attended?");
			String answer2 = scanner.next();
			q3.put(username, answer2);
		}
		else if (choice2 == 4) {
			System.out.println("What is your oldest sibling's middle name?");
			String answer2 = scanner.next();
			q4.put(username, answer2);
		}
		else if (choice2 == 5) {
			System.out.println("What is your oldest sibling's middle name?");
			String answer2 = scanner.next();
			q5.put(username, answer2);
		}
		else if (choice2 == 6) {
			System.out.println("What was the name of your first pet?");
			String answer2 = scanner.next();
			q6.put(username, answer2);
		}
		else if (choice2 == 7) {
			System.out.println("What is your mother's maiden name?");
			String answer2 = scanner.next();
			q7.put(username, answer2);
		}
		else {
			System.out.println("Error");
		}
		System.out.println(q2);
		
		//close scanner
		scanner.close();
	}
}
