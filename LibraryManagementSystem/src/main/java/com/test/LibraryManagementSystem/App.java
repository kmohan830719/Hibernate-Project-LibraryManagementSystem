//declaring the package
package com.test.LibraryManagementSystem;

//importing the package
import java.util.Scanner;

import com.login_crud.Methods;

//declaring the class
public class App {

	// main method
	public static void main(String[] args) {

		// creating object of the method class
		Methods a = new Methods();

		// creating an object of scanner class
		Scanner scan = new Scanner(System.in);

		// variables
		boolean check = true;
		boolean check2 = true;

		System.out.println("...........WELCOME TO THE LIBRARY............\n");

		// presenting menu and calling methods to perform CRUD operation
		do {

			System.out.println("1.Registration");
			System.out.println("2.Login");
			System.out.println("3.Exit");

			// taking thw user choice
			System.out.println("Enter your choice");
			int choice = scan.nextInt();

			switch (choice) {

			case 1:
				a.registration();
				break;
			case 2:
				a.login();
				if (a.loginStatus) {
					while (check2) {
						a.showMenu();
						System.out.println("Enter your choice");
						int ch = scan.nextInt();

						switch (ch) {
						case 1:
							a.addBook();
							break;
						case 2:
							a.getBook();
							break;
						case 3:
							a.deleteBook();
							break;
						case 4:
							a.updateBook();
							break;
						case 5:
							check2 = false;
							System.out.println("------Logedout------");
							break;
						default:
							System.out.println("Invalid input");
						}
					}
				} else {
					break;
				}
				break;
			case 3:
				check = false;
				System.out.println("-----You are Exit------");
				break;

			default:
				System.out.println("Invalid input");

			}
		} while (check);

		scan.close();
	}
}
