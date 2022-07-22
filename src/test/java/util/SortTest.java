package util;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import timekeeping.my.model.entity.Activity;
import timekeeping.my.model.entity.Category;
import timekeeping.my.util.Sort;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SortTest {

    private static final List<Activity> expectedList = new ArrayList<>();
    private static final List<Activity> actualList = new ArrayList<>();
    private static final Activity a = new Activity();
    private static final Activity b = new Activity();
    private static final Activity c = new Activity();

    @Before
    public void setUp() {
        String nameA = "A - Activity 100";
        String nameB = "B - Activity 1500";
        String nameC = "C - Activity 5000";

        Category categoryA = new Category(2);
        Category categoryB = new Category(3);
        Category categoryC = new Category(1);

        BigInteger countA = new BigInteger("5000");
        BigInteger countB = new BigInteger("1500");
        BigInteger countC = new BigInteger("100");

        a.setName(nameA);
        b.setName(nameB);
        c.setName(nameC);

        a.setCategory(categoryA);
        b.setCategory(categoryB);
        c.setCategory(categoryC);

        a.setCount(countA);
        b.setCount(countB);
        c.setCount(countC);

        expectedList.add(a);
        expectedList.add(b);
        expectedList.add(c);
    }

    @Test
    public void testCategory() {
        actualList.add(c);
        actualList.add(a);
        actualList.add(b);

        String param = "category";
        Sort.sort(expectedList, param);

        Assertions.assertThat(expectedList).isEqualTo(actualList);
    }

    @Test
    public void testName() {
        actualList.add(a);
        actualList.add(b);
        actualList.add(c);

        String param = "name";
        Sort.sort(expectedList, param);

        Assertions.assertThat(expectedList).isEqualTo(actualList);
    }

    @Test
    public void testCount() {
        actualList.add(c);
        actualList.add(b);
        actualList.add(a);

        String param = "count";
        Sort.sort(expectedList, param);

        Assertions.assertThat(expectedList).isEqualTo(actualList);
    }

    @Test
    public void testUnsupportedParam() {
        actualList.add(a);
        actualList.add(b);
        actualList.add(c);

        String param = "unsupported";
        Sort.sort(expectedList, param);

        Assertions.assertThat(expectedList).isEqualTo(actualList);
    }
}
