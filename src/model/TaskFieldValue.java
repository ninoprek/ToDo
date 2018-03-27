package model;

/**
 * A generic object that can take any of the types of <code>{@link Task}</code>
 * @param <T>
 */

public class TaskFieldValue<T> {

    private T t;

    public TaskFieldValue(T t) {
        this.t = t;
    }

    public T getT() {
        return this.t;
    }
}
