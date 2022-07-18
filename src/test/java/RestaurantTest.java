import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    Restaurant restaurant;
    List<String> itemNames = new ArrayList<>();
    //REFACTOR ALL THE REPEATED LINES OF CODE

    @BeforeEach
    public void setup() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------TEST CASES TO TEST THE RESTAURANT IS OPEN OR CLOSED
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
        //WRITE UNIT TEST CASE HERE
        assertEquals(restaurant.isRestaurantOpen(), true);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        //WRITE UNIT TEST CASE HERE
        Restaurant restaurant = Mockito.mock(Restaurant.class);
        LocalTime localtime = LocalTime.now().minusHours(8);
        Mockito.lenient().when(restaurant.getCurrentTime()).thenReturn(localtime);
        assertEquals(restaurant.isRestaurantOpen(), false);
    }


    //<<<<<<<<<<<<<<<<TEST CASE TO CALCULATE TOTAL ORDER VALUE>>>>>>>>>>>>>
    @Test
    public void should_display_total_order_value_given_the_list_of_item_names() {

        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToMenu("Chocolate brownie", 90);
        Item item = restaurant.selectItem("Sweet corn soup");
        Item item1 = restaurant.selectItem("Vegetable lasagne");
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                () -> restaurant.removeFromMenu("French fries"));
    }
//<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
