package test;

import java.util.Calendar;

public class Test {

public static void main(String[] args) {
	/*User user = new User();
	user.setEmail("masasdss@mail.com");
	user.setName("dsds11");
	user.setRole_id(12);
	user.setSurname("sddssd111");
	user.isValid();
	System.out.println(user.getErrors());*/
	 Calendar cal = Calendar.getInstance();
	 System.out.println(cal.getTime());
     cal.add(Calendar.MONTH, 7);
     System.out.println(cal.getTime());
	
}
}
