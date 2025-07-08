package org.example.entity_factories;

import org.example.entities.static_entities.Tree;

import java.util.Properties;

public class TreeFactory extends EntityFactory<Tree> {
    private static final String VIEW_PARAM = "tree_view";
    private static final String ENTITY_NAME = "дерево";

    public TreeFactory(Properties config, int numberToSupply) {
        super(config, ENTITY_NAME, numberToSupply);
    }

    @Override
    public Tree get() {
        String view = (String) config.get(VIEW_PARAM);
        return new Tree(view);
    }
}
