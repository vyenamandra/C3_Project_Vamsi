import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

class RestaurantTest {
    Restaurant restaurant;
    public void addMenuforTestcaseExecution(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        addMenuforTestcaseExecution();
        Restaurant spyRestaurant =  Mockito.spy(restaurant);
        LocalTime timeForOpenHours = LocalTime.parse("12:30:00");

        Mockito.spy(when(spyRestaurant.getCurrentTime()).thenReturn(timeForOpenHours));
        spyRestaurant.openingTime = LocalTime.parse("11:00:00");
        spyRestaurant.closingTime = LocalTime.parse("19:00:00");
        assertTrue(spyRestaurant.isRestaurantOpen());

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        addMenuforTestcaseExecution();
        Restaurant spyRestaurant =  Mockito.spy(restaurant);
        LocalTime timeForOpenHours = LocalTime.parse("10:30:00");
        Mockito.spy(when(spyRestaurant.getCurrentTime()).thenReturn(timeForOpenHours));
        spyRestaurant.openingTime = LocalTime.parse("11:00:00");
        spyRestaurant.closingTime = LocalTime.parse("19:00:00");
        assertFalse(spyRestaurant.isRestaurantOpen());

    }


    //Solution for part-3
    @Test
    public void order_total_should_return_total_order_value_when_items_are_ordered_from_menu(){
        addMenuforTestcaseExecution();
        List<String> menuItemNames = Arrays.asList("Sweet corn soup","Vegetable lasagne");

        assertEquals(388,restaurant.orderTotal(menuItemNames));
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        addMenuforTestcaseExecution();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        addMenuforTestcaseExecution();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        addMenuforTestcaseExecution();
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}