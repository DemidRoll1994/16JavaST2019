package by.samtsov.task03.service.combiner;

import by.samtsov.task03.beans.entity.Component;

public interface ComponentCombiner {
    /**
     * concat string values from component to one string with prefix and suffix.
     *
     * @param componentToCombine - component, that should be concatenated
     *                           into the string
     * @return concatenation result.
     */
    String combine(Component componentToCombine);
}
