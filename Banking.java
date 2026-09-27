package CoreJava;
import java.util.Scanner;
import java.time.*;
public class Banking{
	static String name;
	static int age;
	static long mobile_number;
	static  int account;
	static int balance = 0;
	static boolean account_created = false;
	static int attempt = 3;
	static boolean locked = false;
	static int otp_attempt = 3;
	static int pin;
	static int transaction_count = 0;
	static String[] history = new String[100];
	static void menu() {
		System.out.println(" ");
		System.out.println("Welcome to the Banking simulation project");
		System.out.println("1.Create an Account");
		System.out.println("2.Check balance in Account");
		System.out.println("3.Deposite an amount in Account");
		System.out.println("4.Withdrawl amount in Account");
		System.out.println("5.LOGIN to Account");
		System.out.println("6.Mini Statement");
		System.out.println("7.Exit Banking services");
	}
	static void creation(Scanner sc) {
		sc.nextLine();
		System.out.print("Enter your name: ");
		name = sc.nextLine();
		System.out.print("Enter your age: ");
		age = sc.nextInt();
		System.out.print("Enter your mobile number: ");
		mobile_number=sc.nextLong();
		account =(int)(Math.random()*10000000)+682792789;
		balance = 10000;
		account_created=true;
		System.out.println("Processing data.....");
		System.out.println("Your Account Created Successfully!!!");
		System.out.println("================Your account details are==============");
		System.out.println("Account Holder: "+name);
		System.out.println("Account Holder age: "+age);
		System.out.println("Mobile number: "+mobile_number);
		System.out.println("Account number is: "+account);
		LocalTime time = LocalTime.now();
		LocalDate dt = LocalDate.now();
		System.out.println("Details as of Time: "+ time);
		System.out.println("Details as of Date: "+ dt);
		System.out.println("================End of Details========================");
		System.out.println("please set up pin for your Account: ");
		pin = sc.nextInt();
		System.out.println("PIN SETUP SUCCESSFULL");
	}
	static void Check_balance() {
		if(account_created==false) {
			System.out.println("Please Create an account first");
		}
		else if(locked==true) {
			System.out.println("Account Locked");
		}
		else {
			System.out.println("Account Holder: "+name);
			System.out.println("Your balance is: "+balance);
		}
	}
	static void Deposite(Scanner sc) {
		if(account_created==false) {
			System.out.println("Please Create your account first");
		}
		else if(locked==true) {
			System.out.println("Account locked");
		}
		else {
			int amt;
			System.out.print("Enter the amount to be deposited: ");
			amt= sc.nextInt();
			balance=balance+amt;
			history[transaction_count] = "Deposited: +"+amt;
			transaction_count++;
			System.out.print("Do you Want to check your balance now: ");
			sc.nextLine();
			String	opt = sc.nextLine();
			if(opt.equals("yes")) {
				Check_balance();
			}
		}
	}
	static void Withdrawl(Scanner sc) {
		if(account_created==false) {
			System.out.println("Please Create your account first");
		}
		else if(locked==true) {
			System.out.println("Account Locked");
		}
		else {
			int draw;
			System.out.print("Enter the amount to be drawed: ");
			draw = sc.nextInt();
			if(balance<draw) {
				System.out.println("There is no sufficent balance in your account");
			}
			else {
				balance=balance-draw;
				history[transaction_count]="Withdrawal: -"+draw;
				transaction_count++;
			}
			System.out.print("Do you want to check balance in your account: ");
			sc.nextLine();
			String option = sc.nextLine();
			if(option.equals("yes")) {
				Check_balance();
			}
		}
	}
	static void Pin(Scanner sc) {
		if(account_created==false) {
			System.out.println("Please Create or recheck your credentials");
		}
		else if(locked==true) {
			System.out.println("Account locked");
		}
		else {
			System.out.print("Enter your pin: ");
			int pin_check = sc.nextInt();
			if(pin==pin_check) {
				Check_balance();
				attempt=3;
			}
			else {
				System.out.println("Attempt Failed");
				attempt--;
				System.out.println("Attempts Remaining: "+ attempt);
				if(attempt==0) {
					System.out.println("...........ACCOUNT LOCKED FOR PROTECTION ........");
					System.out.println("contact branch");
					LocalDate dt = LocalDate.now();
					LocalTime time = LocalTime.now();
					System.out.println("Locked Date: "+dt);
					System.out.println("Locked time: "+time);
					System.out.println("............STATE BANKING SERVICES INDIA..........");
					locked = true;
				}
			}
		}
	}
	static void Mini_statement(){
		if(transaction_count==0){
			System.out.println("There are no trasaction to display");
		}
		else{
			System.out.println("============MINI STATEMENT=============");
			for(int i = 0 ; i<transaction_count ; i++){
				System.out.println(history[i]);
			}
			System.out.println("--------------Statement created at-------------");
			LocalDate dt = LocalDate.now();
			LocalTime time = LocalTime.now();
			System.out.println("Date: "+dt);
			System.out.println("Time: "+time);
			System.out.println("=======================================");
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			menu();
			System.out.print("Enter your choice here: ");
			int choice = sc.nextInt();
			if(choice==1) {
				creation(sc);
			}
			else if(choice==2) {
				Check_balance();
			}
			else if(choice==3) {
				Deposite(sc);
			}
			else if(choice==4) {
				System.out.println("Sending OTP to registered mobile number.... ending with xxxxx---");
				int otp = (int)(Math.random() * 900000)+100000;
				System.out.println("The otp is: "+otp);
				int val;
				System.out.print("Enter the OTP received to your mobile number: ");
				val=sc.nextInt();
				if(otp==val){
					otp_attempt = 3;
					Withdrawl(sc);
				}
				else{
					otp_attempt--;
					System.out.print("The remaining attempts are: "+otp_attempt);
					if(otp_attempt==0){
						locked=true;
						System.out.print("The account is frezzed");
					}
				}

			}
			else if(choice==5) {
				Pin(sc);
			}
			else if(choice==6){
				Mini_statement();
			}
			else if(choice==7) {
				System.out.println("thank you for using banking services .......");
				System.out.println("LOGGING OUT......");
				System.out.println("BYE");
				break;
			}
		}
	}
}

