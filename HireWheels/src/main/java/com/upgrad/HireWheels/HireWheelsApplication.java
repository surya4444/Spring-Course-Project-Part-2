package com.upgrad.HireWheels;

import com.upgrad.HireWheels.dao.*;
import com.upgrad.HireWheels.entities.*;
import com.upgrad.HireWheels.services.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HireWheelsApplication {

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


	public static void main(String[] args) {
		SpringApplication.run(HireWheelsApplication.class, args);
	}

	@Bean
	CommandLineRunner init(){
		return args -> {

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


		};
	}

}
