package org.wilson.lambdasinaction.chap6;

/**
 * Created by wilson on 7/11/16.
 */
public class PartitionPrimeNumbers {
//    public static void main(String... args) {
//        System.out.println("Numbers partitioned in prime and non-prime: " + partitionPrimes(100));
//    }
//
//    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
//        return IntStream.rangeClosed(2, n).boxed()
//                .collect(Collectors.partitioningBy(candidate -> isPrime(candidate)));
//    }
//
//    public static boolean isPrime(int candidate) {
//        int candidateRoot = (int)Math.sqrt((double)candidate);
//        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
//    }
//
//    public static class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {
//
//        @Override
//        public Supplier<Map<Boolean, List<Integer>>> supplier() {
//            return () -> new HashMap<Boolean, List<Integer>>() {{
//                put(true, new ArrayList<>());
//                put(false, new ArrayList<>());
//            }};
//        }
//
//        @Override
//        public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
//            return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
//                acc.get(isPrime(acc.get(true), candidate)).add(candidate);
//            };
//        }
//
//        @Override
//        public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
//            return null;
//        }
//
//        @Override
//        public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
//            return null;
//        }
//
//        @Override
//        public Set<Characteristics> characteristics() {
//            return null;
//        }
//    }
}
