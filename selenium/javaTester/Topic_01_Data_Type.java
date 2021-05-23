package javaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

//kiểu dữ liệu
public class Topic_01_Data_Type {
	//ngầm định/nguyên thủy
	
	
	//chứa những ký tự
	//chứa duy nhất 1 ký tự
	//char
	char a = 'c';
	
	
	//chứa số nguyên (không dấu)miền dữ liệu rộng hay hẹp
	//byte
	byte byteNumber = 50;
	
	//short
	short shortNumber = 50;
	
	//int
	int intNumber = 50;
	
	//long
	long longNumber = 50;
	
	//chứa số thực (có dấu)
	//float
	float floatNumber = 59.3F;
	//double
	double doubleNumber = 78.2D;
	
	//kiểu luận lý (đúng or sai)
	//boolean
	boolean status = true;
	
	
	//tham chiếu
	//chứa những chuỗi kí tự (số/ chữ/kí tự đặc biệt)
	//String
	String name = "Truong Minh Thong";
	String address = "23/8 Tran Hung Dao";
	String password = "p@ssw$%^$ord";
	
	//kiểu class
	//Class
	
	//kiểu đối tượng
	//Object
	
	//kiểu mảng
	//Array
	String[] students = {"Hoa", "Buoi","CAc"};
	String[] info = {name, address, password};
	int[] studentsalary = {1200, 1400, 500};
	
	//kiểu interface
	WebDriver driver;
	//Interface
	RemoteWebDriver remoteDriver;
	//kiểu collection
	//Collection
	//(List) ArrayList/ Linkedlist /Set/ Map

}
