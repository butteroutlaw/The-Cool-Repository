import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import java.util.*;


public class Main {
  static String[] qs = signup.getQs();
	public static void main(String[] args) throws InterruptedException {
		// Creates the frame
    JFrame frame=new JFrame("Password Protector");

    //  Username and Password
    JLabel l1,l2;  
    l1=new JLabel("Username: ");  
    l1.setBounds(50,50, 100,30);  
    l2=new JLabel("Password: ");  
    l2.setBounds(50,100, 100,30);  
    frame.add(l1); 
    frame.add(l2);  
    frame.setSize(300,300); 

    // Username and Password input
    JTextField userInput= new JTextField(50);
    userInput.setBounds(150,50,100,30);
    frame.add(userInput);
    String username1= userInput.getText();

    JTextField userPass= new JTextField(50);
    userPass.setBounds(150,100,100,30);
    frame.add(userPass);
    String password1= userPass.getText();

    // Sign-up button
    
    JButton sigButton=new JButton("Sign-up");  
    sigButton.setBounds(50,150,100,30);  
    frame.add(sigButton); 

    // Log-in button

    JButton logButton= new JButton("Log-in");
    logButton.setBounds(150,150,100,30);
    frame.add(logButton);

    frame.setLayout(null);  
    frame.setVisible(true);
    
    Scanner scanner = new Scanner(System.in);

    //title
		System.out.println("Password Protectors");
		System.out.println("Log in");

		//get username and password
		System.out.println("Enter Username: ");
		String username = scanner.nextLine();
		System.out.println("Enter Password: ");
		String password = scanner.nextLine();
		
		//variables from file
		String ogPassword = "";
		String ogRegion = "";
		String q1 = "";
		String a1 = "";
		String q2 = "";
		String a2 = "";
		
		//read from csv file
		boolean found = false;
		String line = "";  
		String splitBy = ",";
		try {  
			BufferedReader br = new BufferedReader(new FileReader("userinfo.csv"));  
			while ((line = br.readLine()) != null)
			{  
				String[] user = line.split(splitBy);
				if (user[0].equals(username)) {
					ogPassword = user[1];
					ogRegion = user[6];
					q1 = user[2];
					a1 = user[3];
					q2 = user[4];
					a2 = user[5];
					//System.out.println(user[0]);
					found = true;
				}
			}  
		}   
		catch (IOException e) {  
			e.printStackTrace();  
		}

		//ip address from netherlands
		String r = "";
		try (java.util.Scanner s = new java.util.Scanner(new java.net.URL(
				"https://api.ipinfodb.com/v3/ip-city?key=cc07ab6d4c0dbdd82bda6aee847aad3247c92633101fb7d309776d9cbc71e7df&ip=193.118.53.194")
				.openStream(),
				"UTF-8").useDelimiter("\\A")) {
			r = s.next();
			System.out.println(r);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}

		//user doesn't have account
		if (!found) {
			System.out.println("Sign up for an account");
		}
		//password doesn't match username
		else if (!password.equals(ogPassword)) {
			System.out.println("Incorrect username or password");
			int count = 1;
			while (!password.equals(ogPassword) && count <= 5) {
				System.out.println("Reset password? (Y for yes, N for no)");
				String reset = scanner.nextLine();
				//reset password
				if (reset.equals("Y")) {
					//security question 1
					if (correctQ(q1, a1)) {
						System.out.println("Enter Password: ");
						password = scanner.nextLine();
						break;
					}
					else {
						//security question 2
						if (correctQ(q2, a2)) {
							System.out.println("Enter Password: ");
							password = scanner.nextLine();
							break;
						}
						//lock user out
						else {
							System.out.println("You are locked out for 20 seconds");
							Thread.sleep(20000);
						}
					}
				}
				//don't reset password
				else if (reset.equals("N")) {
					System.out.println("Enter Username: ");
					username = scanner.nextLine();
					System.out.println("Enter Password: ");
					password = scanner.nextLine();
				}
				else {
					System.out.println("Error");
				}
				count++;

				//user fails to log in 5 times
				if (!password.equals(ogPassword) && count == 5) {
					System.out.println("You are locked out for 20 seconds");
					count = 0;
					Thread.sleep(20000);
				}
			}
		}
		//password matches username but ip address is unfamiliar
		else if (password.equals(ogPassword) && !r.equals(ogRegion)) {
			if (correctQ(q1, a1)) {
				System.out.println("Successfully logged in");
			}
			else {
				if (correctQ(q2, a2)) {
					System.out.println("Successfully logged in");
				}
				else {
					Thread.sleep(20000);
				}
			}
		}
		//all is well
		else {
			System.out.println("Successfully logged in");
		}

		scanner.close();
	}

	//security question
	public static boolean correctQ(String q, String a) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please answer Security Question:");
		int i = Integer.parseInt(q);
		System.out.println(qs[i - 1].substring(3));
		String answer = scanner.next();
		if (answer.equals(a)) {
			return true;
		}
		return false;
	}
}
