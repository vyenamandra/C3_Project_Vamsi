import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {
        /* part-2 solution
        List<Restaurant> matches = new ArrayList<Restaurant>();
        boolean isRestaurantFound = false;
        for (Restaurant str : restaurants) {
            if (str.getName().equalsIgnoreCase(restaurantName)) {
                matches.add(str);
                isRestaurantFound = true;
                break;
            }
        }
        if (isRestaurantFound) {
            return (Restaurant) matches.get(0);
        } else
            throw new restaurantNotFoundException(restaurantName);*/
        return null;
        }

    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}
