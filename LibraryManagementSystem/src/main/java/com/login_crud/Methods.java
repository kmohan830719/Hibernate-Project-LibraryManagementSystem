//declaring the package
package com.login_crud;

//importing the packages
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.entity.Book;
import com.entity.Login;
import com.entity.Registration;
import com.hiberUtil.GetConnection;

//declaring the class
public class Methods {

	// getting the connection from getconnection class
	SessionFactory sf = GetConnection.con();

	// creating the object of scanner class
	Scanner sc = new Scanner(System.in);

	// variable
	public boolean loginStatus = false;

	// creating method for registration
	public void registration() {
		try {
			Session s = sf.openSession();
			Transaction tr = s.beginTransaction();

			Registration r = new Registration();
			System.out.println("Enter your Name");
			r.setName(sc.next());
			System.out.println("Enter your Age");
			r.setAge(sc.nextInt());
			System.out.println("Enter your Pnone Number");
			r.setPhone_no(sc.nextInt());
			System.out.println("Enter your Gender");
			r.setGender(sc.next());

			s.save(r);
			tr.commit();
			System.out.println("You are sucessfully registered");
			System.out.println("-------------------------------------");
			System.out.println("your Login credentials are");
			System.out.println("User name: " + r.getName());
			System.out.println("Password: " + r.getPhone_no());
			System.out.println("-------------------------------------");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// creating method for login
	public void login() {
		try {
			Session s = sf.openSession();
			Transaction tr = s.beginTransaction();

			System.out.println("Enter your user_name");
			String uName = sc.next();
			System.out.println("Enter your password");
			int pass = sc.nextInt();

			Registration r1 = s.get(Registration.class, pass);
			String user = r1.getName();
			int password = r1.getPhone_no();

			if (uName.equals(user) && pass == password) {
				System.out.println("-----------------------------");
				System.out.println("login sucsessful.....");
				Login l = new Login();
				l.setName(uName);
				l.setDate(new Date());
				s.save(l);
				tr.commit();
				System.out.println("login data saved");
				System.out.println("-----------------------------");
				loginStatus = true;
			} else {
				System.out.println("---------------------------------------");
				System.out.println("wrong credentials.......Login again!!");
				System.out.println("---------------------------------------");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	// creating method to show menu
	public void showMenu() {

		System.out.println("WELCOME....You can do following things");
		System.out.println("1.Add book to the library");
		System.out.println("2.Show the available books");
		System.out.println("3.Delete a book from library");
		System.out.println("4.Update a book in the library");
		System.out.println("5.Logout");

	}

	// creating method to add book
	public void addBook() {
		try {
			Session s = sf.openSession();
			Transaction tr = s.beginTransaction();

			System.out.println("Enter the book name");
			String name = sc.next();
			System.out.println("Enter the book_auther name");
			String aName = sc.next();
			System.out.println("Enter date of publication");
			String dop = sc.next();
			System.out.println("Number of pages");
			int pag = sc.nextInt();

			Book b = new Book();
			b.setBook_name(name);
			b.setBook_auther_name(aName);
			b.setDate_of_publication(dop);
			b.setNo_of_pages(pag);

			s.save(b);
			tr.commit();
			System.out.println("-------------------");
			System.out.println("Book added.....");
			System.out.println("-------------------");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// creating method to delete book
	public void deleteBook() {
		try {
			Session s = sf.openSession();
			Transaction tr = s.beginTransaction();

			System.out.println("Enter the book ID which you want to delete");
			int bID = sc.nextInt();

			Book b = s.load(Book.class, bID);
			s.delete(b);
			tr.commit();
			System.out.println("-------------------");
			System.out.println("Book deleted......");
			System.out.println("-------------------");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// creating method to update book
	public void updateBook() {
		try {
			Session s = sf.openSession();
			Transaction tr = s.beginTransaction();

			System.out.println("Enter the book ID which you want to update");
			int bID = sc.nextInt();
			System.out.println("----------------------------------------------------------------");
			System.out.println("which part of the book you want to upadte");
			System.out.println("1.book_name");
			System.out.println("2.book_auther_name");
			System.out.println("3.date of publication");
			System.out.println("4.number of pages");

			System.out.println("Enter");
			int ch = sc.nextInt();

			Book b = s.load(Book.class, bID);

			switch (ch) {

			case 1:
				System.out.println("Enter the new book name");
				String name = sc.next();
				b.setBook_name(name);
				s.save(b);
				tr.commit();
				break;
			case 2:
				System.out.println("Enter the new book_auther name");
				String aName = sc.next();
				b.setBook_auther_name(aName);
				s.save(b);
				tr.commit();
				break;
			case 3:
				System.out.println("Enter new date of publication");
				String dop = sc.next();
				b.setDate_of_publication(dop);
				s.save(b);
				tr.commit();
				break;
			case 4:
				System.out.println("New number of pages");
				int pag = sc.nextInt();
				b.setNo_of_pages(pag);
				s.save(b);
				tr.commit();
				break;
			default:
				System.out.println("Inavlid input....");

			}
			System.out.println("----------------------");
			System.out.println("The Data updated....");
			System.out.println("----------------------");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// creating method to show book
	public void getBook() {
		try {
			Session s = sf.openSession();

			Query q1 = s
					.createQuery("select book_id,book_name,book_auther_name,date_of_publication,no_of_pages from Book");
			List<Object[]> st = (List<Object[]>) q1.list();
			System.out.println("----------------------------------------------");
			for (Object[] ob : st) {

				System.out.println(ob[0] + "  " + ob[1] + "  " + ob[2] + "  " + ob[3] + "  " + ob[4]);

			}
			System.out.println("----------------------------------------------");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
