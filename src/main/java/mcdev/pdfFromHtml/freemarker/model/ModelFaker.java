package mcdev.pdfFromHtml.freemarker.model;

import java.util.ArrayList;
import java.util.List;

public class ModelFaker {


	public static UsersDTO getUserData() {
		
		List<UserDTO> userData = new ArrayList<UserDTO>();
		
		userData.add(new UserDTO("Jhon", "Table"));
		userData.add(new UserDTO("Phill", "Core"));
		userData.add(new UserDTO("Rose", "Dho"));
		
		UsersDTO usersData = new UsersDTO();
		usersData.setUsers(userData);
		
		return usersData;
	}
	
}
