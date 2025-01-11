import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;


public class CollectionListTest {
    @Test
    public void testTop3YearEstimateDifferences(){
        List<Collectible> collectibles = new ArrayList<>();
        collectibles.add(new Collectible("Item 1", new YearEstimate(1930,1950),1, "Owner", "Good", 100.00));
        collectibles.add(new Collectible("Item 2", new YearEstimate(1800,1850), 2, "Owner", "Good", 100.00));
        collectibles.add(new Collectible("Item 3", new YearEstimate(2000,2010), 3, "Owner", "Good", 100.00));
        collectibles.add(new Collectible("Item 4", new YearEstimate( 1900,1950),4, "Owner", "Good", 100.00));

        CollectionList collectionList = new CollectionList();
        collectionList.setCollectibles(new ArrayList<>(collectibles));
        List<Collectible> result = collectionList.getTop3YearEstimateDifference();

        // Assert that the results contains 3 items
        assertEquals(3, result.size());
        // Assert that the top item is "Item2" with the largest difference
        assertEquals("Item 3", result.get(0).getTitle());

        // Check the differences to ensure sorting is done correctly
        int diff1 = Math.abs(result.get(0).getYearEstimate().getHighEstimate() - result.get(0).getYearEstimate().getLowEstimate());
        int diff2 = Math.abs(result.get(1).getYearEstimate().getHighEstimate() - result.get(1).getYearEstimate().getLowEstimate());
        int diff3 = Math.abs(result.get(2).getYearEstimate().getHighEstimate() - result.get(2).getYearEstimate().getLowEstimate());

        // ensure that the items are sorted by the largest year difference
        assertTrue(diff1 <= diff2);
        assertTrue(diff2 <= diff3);


    }
}
