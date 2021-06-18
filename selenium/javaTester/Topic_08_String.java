package javaTester;

public class Topic_08_String {
	public static void main (String[] args) {
		String ActualText = "            First Option       ";
		System.out.println(ActualText);
		System.out.println(ActualText.trim());
		
		
		String user = "admin";
		String pass = "admin";
		String href = "http://the-internet.herokuapp.com/basic_auth";
		
		String[] hrefValue = href.split("//");
		System.out.println(hrefValue[0]);
		System.out.println(hrefValue[1]);
		
		href = hrefValue[0] + "//" +  user + ":" + pass + "@" + hrefValue[1] ;
		System.out.println(href);
		
		
	}

}
