package org.example.entities.suppliers;

import org.example.entities.EntitySupplier;
import org.example.entities.non_living.Rock;
import org.example.entities.non_living.Tree;

import java.util.Properties;

public class TreeSupplier extends EntitySupplier<Tree> {
    private static final String VIEW_PARAM = "tree_view";

    public TreeSupplier(Properties config, int numberToSupply) {
        super(config, numberToSupply);
    }

    @Override
    public Tree get() {
        String view = (String) config.get(VIEW_PARAM);
        return new Tree(view.charAt(0));
    }
}
