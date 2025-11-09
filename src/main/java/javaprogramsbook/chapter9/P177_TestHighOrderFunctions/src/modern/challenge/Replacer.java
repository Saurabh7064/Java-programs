package javaprogramsbook.chapter9.P177_TestHighOrderFunctions.src.modern.challenge;

@FunctionalInterface
public interface Replacer<T> {

    T replace(T s);
}
