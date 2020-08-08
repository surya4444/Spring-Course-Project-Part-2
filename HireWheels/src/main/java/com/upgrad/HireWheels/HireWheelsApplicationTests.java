package com.upgrad.HireWheels;

import com.upgrad.HireWheels.dao.*;
import com.upgrad.HireWheels.entities.*;
import com.upgrad.HireWheels.exception.*;
import com.upgrad.HireWheels.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
class HireWheelsApplication {

	@Autowired
	InitService initService ;

	@Autowired
	VehicleCategoryDAO vehicleCategoryDAO ;

	@Autowired
	VehicleSubcategoryDAO vehicleSubcategoryDAO ;

	@Autowired
	CityDAO cityDAO ;

	@Autowired
	LocationDAO locationDAO ;

	@Autowired
	UsersDAO userDAO ;

	@Autowired
	RoleDAO roleDAO ;

	@Autowired
	UserService userService;

	@Autowired
	AdminService adminService;

	@Autowired
	VehicleDAO vehicleDAO;

	@Autowired
	BookingService bookingService;

	@Autowired
	VehicleService vehicleService;


	public static void main(String[] args) {
		SpringApplication.run(HireWheelsApplication.class, args);
	}

	@Bean
	CommandLineRunner init(){
		return args -> {


			System.out.println("============= CHECKPOINT 2 =============");
			//Insert data to the user table
			Users surya = userDAO.save(new Users("surya", "Teja", "gst12345", "gst143@gmail.com", "1234567891", 12345));

//        List<String> suryaPhoneNo =  new ArrayList<String>();
//        suryaPhoneNo.add("1234567891");
//        surya.setMobileNumber(suryaPhoneNo);
//        userDAO.save(surya);

			System.out.println("Data are insert into table");

			System.out.println("Data in users table");
			List<Users> insertedUsersList = userDAO.findAll();
			insertedUsersList.forEach(users -> System.out.println(users));


			System.out.println("============= CHECKPOINT 3 =============");

			Users Admin = userDAO.save(new Users( "Admin" , "Admin" , "admin@123" , "upgrad@gmail.com" , "9999999999" , 10000));

//        List<String> AdminPhoneNo =  new ArrayList<String>();
//        AdminPhoneNo.add("9999999999");
//        Admin.setMobileNumber(AdminPhoneNo);
//        userDAO.save(Admin);
			Role admin = roleDAO.save(new Role("Admin"));

			//As user and role manytomany we have to do insert row on both side of a tables
			Admin = userDAO.findById(Admin.getId()).get();
			admin = roleDAO.findById(admin.getId()).get();

//        Admin.setRoles(admin);
//        userDAO.save(Admin);

			admin.getUsers().add(Admin);
			roleDAO.save(admin);
			Admin.getRoles().add(admin);
			userDAO.save(Admin);

			admin = roleDAO.findById(admin.getId()).get();
			List<Users> adminUsers = admin.getUsers();
			List<Users> adminUser = adminUsers.stream().distinct().collect(Collectors.toList());
			System.out.println("Only admin details in table: ");
			adminUser.forEach(each -> System.out.println(each));

			System.out.println("============= CHECKPOINT 4 =============");

			//You can't see above data in table.
			//I insert data to only two tables and i remove it
			userDAO.deleteAll();
			roleDAO.deleteAll();

			Role userRole = initService.acceptRoleTable(new Role("User"));
			Role adminRole = initService.acceptRoleTable(new Role("Admin"));
			//===========
			Users Admin1 = initService.acceptUsersTable(new Users( "Admin" , "Admin" , "admin@123" , "upgrad@gmail.com" , "9999999999" , 10000));

			Admin1 = userDAO.findById(Admin1.getId()).get();
			adminRole = roleDAO.findById(adminRole.getId()).get();

			adminRole.getUsers().add(Admin1);
			roleDAO.save(adminRole);
			Admin1.getRoles().add(adminRole);
			userDAO.save(Admin1);

			//============
			VehicleCategory car = initService.acceptVehicleCategoryTable(new VehicleCategory("car"));
			VehicleCategory bike = initService.acceptVehicleCategoryTable(new VehicleCategory("bike"));
			//=============
			VehicleSubcategory suv = initService.acceptVehicleSubcategoryTable(new VehicleSubcategory("suv" , 300));
			VehicleSubcategory sedan = initService.acceptVehicleSubcategoryTable(new VehicleSubcategory("sedan" , 350));
			VehicleSubcategory hatchback = initService.acceptVehicleSubcategoryTable(new VehicleSubcategory("hatchback" , 250));
			VehicleSubcategory cruiser = initService.acceptVehicleSubcategoryTable(new VehicleSubcategory("cruiser" , 200));
			VehicleSubcategory dirtBike = initService.acceptVehicleSubcategoryTable(new VehicleSubcategory("dirtBike" , 200));
			VehicleSubcategory sportsBike = initService.acceptVehicleSubcategoryTable(new VehicleSubcategory("sportsBike" , 150));



			car = vehicleCategoryDAO.findById(car.getId()).get();

			suv = vehicleSubcategoryDAO.findById(suv.getId()).get();
			suv.setVehicleCategory(car);
			vehicleSubcategoryDAO.save(suv);
			sedan = vehicleSubcategoryDAO.findById(sedan.getId()).get();
			sedan.setVehicleCategory(car);
			vehicleSubcategoryDAO.save(sedan);
			hatchback = vehicleSubcategoryDAO.findById(hatchback.getId()).get();
			hatchback.setVehicleCategory(car);
			vehicleSubcategoryDAO.save(hatchback);

			bike = vehicleCategoryDAO.findById(bike.getId()).get();

			cruiser = vehicleSubcategoryDAO.findById(cruiser.getId()).get();
			cruiser.setVehicleCategory(bike);
			vehicleSubcategoryDAO.save(cruiser);
			dirtBike = vehicleSubcategoryDAO.findById(dirtBike.getId()).get();
			dirtBike.setVehicleCategory(bike);
			vehicleSubcategoryDAO.save(dirtBike);
			sportsBike = vehicleSubcategoryDAO.findById(sportsBike.getId()).get();
			sportsBike.setVehicleCategory(bike);
			vehicleSubcategoryDAO.save(sportsBike);
			//================
			FuelType petrol = initService.acceptFuelTypeTable(new FuelType("petrol"));
			FuelType diesel = initService.acceptFuelTypeTable(new FuelType("diesel"));
			//===============
			City mumbai = initService.acceptCityTable(new City("mumbai"));
			//==============
			Location worli = initService.acceptLocationTable(new Location("Worli" , "Dr E Moses Rd, Worli Naka, Upper Worli" ,"400018"));
			Location chembur = initService.acceptLocationTable(new Location("Chembur" , "Optic Complex","400019"));
			Location powai = initService.acceptLocationTable(new Location("Powai" , "Hiranandani Towers","400020"));

			mumbai = cityDAO.findById(mumbai.getId()).get();

			worli = locationDAO.findById(worli.getId()).get();
			worli.setCity(mumbai);
			locationDAO.save(worli);

			chembur = locationDAO.findById(chembur.getId()).get();
			chembur.setCity(mumbai);
			locationDAO.save(chembur);

			powai = locationDAO.findById(powai.getId()).get();
			powai.setCity(mumbai);
			locationDAO.save(powai);

			System.out.println("===================== check point 4.6 ========================");
			//It check email and phone number if it not matched in table it store in table else it return Exception
			try {
				Users london = userService.createUser(new Users( "london" , "usa" , "don@123" , "upgrad@gmail.com" , "9999899999" , 100));
			} catch (UserAlreadyExistsException e) {
				e.printStackTrace();
			}

			//It check email and phone number if it not matched in table it store in table else it return Exception
			try {
				Users london = userService.createUser(new Users( "london" , "usa" , "don@123" , "upgrad123@gmail.com" , "9999999999" , 100));
			} catch (UserAlreadyExistsException e) {
				e.printStackTrace();
			}

			//It check email and phone number if it not matched in table it store in table else it return Exception
			try {
				Users raja = userService.createUser(new Users( "raja" , "" , "rajaTheGreat" , "don123@gmail.com" , "1234567890" , 10000.23));
			} catch (UserAlreadyExistsException e) {
				e.printStackTrace();
			}

			//For login we use only email and password
			//It check email and password if any one are not matched in table it return Exception else it return the data of matched customer
			try {
				Users raja = userService.getUser(new Users("don@gmail.com","rajaTheGreat"));
				System.out.println(raja);
			} catch (UserNotFoundException e) {
				e.printStackTrace();
			}

			//It check email and password if any one are not matched in table it return Exception else it return the data of matched customer
			try {
				Users raja = userService.getUser(new Users("don123@gmail.com","rajaGreat"));
				System.out.println(raja);
			} catch (UserNotFoundException  e) {
				e.printStackTrace();
			}

			//It check email and password if any one are not matched in table it return Exception else it return the data of matched customer
			try {
				Users raja = userService.getUser(new Users("don123@gmail.com","rajaTheGreat"));
				System.out.println("logIn user details :");
				System.out.println(raja);
			} catch (UserNotFoundException  e) {
				e.printStackTrace();
			}
			//============
			/**
			 * This try catch block is used to check that vehicle is register user or not if user throw exception
			 * else store in table with availability_status is 1
			 * This store in database
			 */
			System.out.println("===================== check point 5.2 and check point 5.3========================");

			Vehicle vehicle2 = null;
			try {
				vehicle2 = adminService.registerVehicle(new Vehicle("SUVjet", "04MP0998", "RED", "https://www.google.com/url?sa=&143fsh=dfs" , Admin1 , suv, petrol, powai));
			} catch (UserNotRegisterVehicleException e) {
				e.printStackTrace();
			}

			Users vamsiUsers = initService.acceptUsersTable(new Users( "vamsi" , "rahul" , "vamsi@123" , "vamsi@gmail.com" , "9835671205" , 1000));

			vamsiUsers = userDAO.findById(vamsiUsers.getId()).get();
			userRole = roleDAO.findById(userRole.getId()).get();

			userRole.getUsers().add(vamsiUsers);
			roleDAO.save(userRole);
			vamsiUsers.getRoles().add(userRole);
			userDAO.save(vamsiUsers);

			/**
			 * This try catch block is used to check that vehicle is register user or not if user throw exception
			 * else store in table with availability_status is 1
			 * This throw exception
			 */
			Vehicle vehicle3;
			try {
				vehicle3 = adminService.registerVehicle(new Vehicle("hatchbackRocket", "99TL7503", "orange", "https://www.google.com/url?sa=12#19ij=u%" , vamsiUsers , hatchback, petrol, worli));
			} catch (UserNotRegisterVehicleException e) {
				e.printStackTrace();
			}

			System.out.println("===================== check point 6.1 ========================");
			/**
			 * Here admin can register any number of vehicle so admin can give which vechile he want to change status
			 * Here I check that i know Inside the table vehicle2 availability status is 1 and i also send 1
			 * So it throw exception
			 */
			try {
				Vehicle vehicle = adminService.changeAvailability( vehicle2 , 1);
			} catch (changeAvailabilityException e) {
				e.printStackTrace();
			}
			/**
			 * Here admin can register any number of vehicle so admin can give which vechile he want to change status
			 * Here I check that i know Inside the table vehicle2 availability status is 1 and I send 0
			 * So it change in table
			 */
			try {
				Vehicle vehicle = adminService.changeAvailability( vehicle2 , 0);
			} catch (changeAvailabilityException e) {
				e.printStackTrace();
			}
			/**
			 * Here admin can register any number of vehicle so admin can give which vechile he want to change status
			 * Here I check that i know Inside the table vehicle2 availability status is change to 0 and I send 0
			 * So it throw exception
			 */
			try {
				Vehicle vehicle = adminService.changeAvailability( vehicle2 , 0);
			} catch (changeAvailabilityException e) {
				e.printStackTrace();
			}
			/**
			 * Here admin can register any number of vehicle so admin can give which vechile he want to change status
			 * Here I check that i know Inside the table vehicle2 availability status is change to 0 and I send 1
			 * So it change in table
			 * I think there are 4 posibility vehicle2:0 changeAvailability :0,01,11,10 all are check
			 */
			try {
				Vehicle vehicle = adminService.changeAvailability( vehicle2 , 1);
			} catch (changeAvailabilityException e) {
				e.printStackTrace();
			}


			System.out.println("==============Below code for TODO 6.2 and 6.3 ==============");

			//Her create 2 users for checking exception are thrown correctly or not when account balance is low and high than booking
			Users suryaUser = initService.acceptUsersTable(new Users( "surya" , "teja" , "surya@123" , "surya@gmail.com" , "1467011111" , 200));

			suryaUser = userDAO.findById(suryaUser.getId()).get();
			userRole = roleDAO.findById(userRole.getId()).get();

			userRole.getUsers().add(suryaUser);
			roleDAO.save(userRole);
			suryaUser.getRoles().add(userRole);
			userDAO.save(suryaUser);

			Users shilaUser = initService.acceptUsersTable(new Users( "shila" , "rani" , "shila@123" , "shila@gmail.com" , "98562726775" , 6975.67));

			shilaUser = userDAO.findById(shilaUser.getId()).get();

			userRole.getUsers().add(shilaUser);
			roleDAO.save(userRole);
			shilaUser.getRoles().add(userRole);
			userDAO.save(shilaUser);

			//For Admin
			Users joneAdmin = initService.acceptUsersTable(new Users( "jone" , "cena" , "jone@123" , "jon@gmail.com" , "2314532661" , 5000));

			joneAdmin = userDAO.findById(joneAdmin.getId()).get();
			adminRole.getUsers().add(joneAdmin);//Admin role id already store in adminRole
			roleDAO.save(adminRole);
			joneAdmin.getRoles().add(adminRole);
			userDAO.save(joneAdmin);

			//===============

			Vehicle vehicle1 = vehicleDAO.save(new Vehicle( "dirtBike125CC" , "12AP1234" , "White" , "https://www.google.com/url?sa=i&url=https%" ,  1 , joneAdmin , dirtBike , petrol ,worli ));

			//===========

			//The user is booking a vehicle if the accountBalance is less than booking amount
			// it through an exception else it update to user wallet based on booking amount and save in booking table
			try {
				Booking booking1 = bookingService.addBooking(new Booking(new Date("31/07/2020") ,new Date("2/08/2020") , new Date("31/07/2020") , 500 , suryaUser ,vehicle1 , worli ));
				//userDAO.updateAmount(booking1.getUsers().getWalletMoney() - booking1.getAmount() , booking1.getUsers().getId() );
			} catch (InsufficientBalanceException e) {
				e.printStackTrace();
			}

			//The user is booking a vehicle if the accountBalance is less than booking amount
			// it through an exception else it update to user wallet based on booking amount and save in booking table
			try {
				Booking booking2 = bookingService.addBooking(new Booking(new Date("8/08/2020") ,new Date("10/08/2020") , new Date("8/08/2020") , 1000 , shilaUser ,vehicle1 , worli ));
				//userDAO.updateAmount(booking2.getUsers().getWalletMoney() - booking2.getAmount() , booking2.getUsers().getId() );
			} catch (InsufficientBalanceException e) {
				e.printStackTrace();
			}
			System.out.println("==============Below code for TODO 7.1 and 7.4 ==============");
			/**
			 * Return all the vehicles present in table
			 */

			List<Vehicle> vehicles = vehicleService.getAllVehicles();
			System.out.println("All the vehicle store in table");
			vehicles.forEach(each -> System.out.println(each));

			System.out.println("==============Below code for TODO 7.2 ==============");
			List<Vehicle> vehicleList = vehicleService.getVehicleByUserID(6);
			System.out.println("All the vehicles available for given userId ");
			vehicleList.forEach(vehicle -> System.out.println(vehicle));

			System.out.println("==============Below code for TODO 7.3 ==============");
			List<Vehicle> vehicleList1 = vehicleService.getAvailableVehicles(new Booking(new Date("8/08/2020") ,new Date("10/08/2020") , new Date("8/08/2020") ,worli ) );
			System.out.println("All the Available Vehicles in your area");
			vehicleList1.forEach(each -> System.out.println(each));

			/**
			 * Here I send a location that not register any vehicle so it doesn't return any vehicle
			 */
			List<Vehicle> vehicleList2 = vehicleService.getAvailableVehicles(new Booking(new Date("8/08/2020") ,new Date("10/08/2020") , new Date("8/08/2020") , chembur ) );

			System.out.println("All the Available Vehicles in your area");
			vehicleList2.forEach(each -> System.out.println(each));







		};
	}

}
