package lambda;

@FunctionalInterface
interface Operation {
    int apply(int a, int b);
}
