import models.Food;
import org.junit.Before;
import org.junit.Test;
import repository.InMemoryRepo;

import static org.junit.Assert.*;

public class InMemoryRepoTests {
    private InMemoryRepo<Food> foodRepo;

    @Before
    public void setUp() {
        foodRepo = new InMemoryRepo<>();
    }

    @Test
    public void testCreate() {
        Food food = new Food(1, "Test Food", 10.0f, 100);

        foodRepo.create(food);

        assertTrue(foodRepo.readAll().contains(food));
    }

    @Test
    public void testRead() throws Exception {
        Food food = new Food(1, "Test Food", 10.0f, 100);
        foodRepo.create(food);

        Food retrievedFood = foodRepo.read(food.getId());

        assertEquals(food, retrievedFood);
    }

    @Test
    public void testUpdate() throws Exception {
        Food food = new Food(1, "Test Food", 10.0f, 100);
        foodRepo.create(food);

        food.setName("Updated Food");
        foodRepo.update(food);

        Food retrievedFood = foodRepo.read(food.getId());

        assertEquals("Updated Food", retrievedFood.getName());
    }

    @Test(expected = Exception.class)
    public void testReadNonExistent() throws Exception {
        foodRepo.read(999); // A non-existent ID, this should throw an exception
    }

    @Test(expected = Exception.class)
    public void testUpdateNonExistent() throws Exception {
        Food food = new Food(999, "Non-existent Food", 10.0f, 100);
        foodRepo.update(food); // This should throw an exception
    }

    @Test(expected = Exception.class)
    public void testDeleteNonExistent() throws Exception {
        foodRepo.delete(999); // A non-existent ID, this should throw an exception
    }
}
