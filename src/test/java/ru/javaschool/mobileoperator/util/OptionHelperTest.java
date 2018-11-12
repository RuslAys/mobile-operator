package ru.javaschool.mobileoperator.util;

import org.junit.Assert;
import org.junit.Test;
import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.utils.OptionHelper;

import java.util.ArrayList;
import java.util.List;

public class OptionHelperTest {

    private OptionHelper optionHelper = new OptionHelper();

    @Test
    public void existConflictsInOneListTest() {
        List<Option> options = new ArrayList<>();
        Option option1 = new Option();
        option1.setName("option1");

        Option option2 = new Option();
        option2.setName("option2");

        option2.getParentExclusive().add(option1);
        option2.getExclusiveOptions().add(option1);
        option1.getParentExclusive().add(option2);
        option1.getExclusiveOptions().add(option2);

        options.add(option1);
        options.add(option2);

        Assert.assertTrue(optionHelper.existConflicts(options));
    }

    @Test
    public void doNotExistConflictsInOneList(){
        List<Option> options = new ArrayList<>();
        Option option1 = new Option();
        option1.setName("option1");

        Option option2 = new Option();
        option2.setName("option2");
        options.add(option1);
        options.add(option2);

        Assert.assertFalse(optionHelper.existConflicts(options));
    }

    @Test
    public void doNotExistConflictsInOneEmptyList(){
        Assert.assertFalse(optionHelper.existConflicts(new ArrayList<>()));
    }

    @Test
    public void existConflictsInTwoList(){
        List<Option> left = new ArrayList<>();
        List<Option> right = new ArrayList<>();
        Option option1 = new Option();
        option1.setName("option1");

        Option option2 = new Option();
        option2.setName("option2");

        option2.getParentExclusive().add(option1);
        option2.getExclusiveOptions().add(option1);
        option1.getParentExclusive().add(option2);
        option1.getExclusiveOptions().add(option2);

        left.add(option1);
        right.add(option2);

        Assert.assertTrue(optionHelper.existConflicts(left, right));
    }


    @Test
    public void doNotExistConflictsInTwoList(){
        List<Option> left = new ArrayList<>();
        List<Option> right = new ArrayList<>();
        Option option1 = new Option();
        option1.setName("option1");

        Option option2 = new Option();
        option2.setName("option2");
        left.add(option1);
        right.add(option2);

        Assert.assertFalse(optionHelper.existConflicts(left, right));
    }
}
