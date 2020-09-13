# Reduction operations

Reduction operations
A reduction operation (also called a fold) takes a sequence of input elements and combines them into a single summary result by repeated application of a combining operation, such as finding the sum or maximum of a set of numbers, or accumulating elements into a list. The streams classes have multiple forms of general reduction operations, called reduce() and collect(), as well as multiple specialized reduction forms such as sum(), max(), or count().
Of course, such operations can be readily implemented as simple sequential loops, as in:

    int sum = 0;
    for (int x : numbers) {
       sum += x;
    }

However, there are good reasons to prefer a reduce operation over a mutative accumulation such as the above. Not only is a reduction "more abstract" -- it operates on the stream as a whole rather than individual elements -- but a properly constructed reduce operation is inherently parallelizable, so long as the function(s) used to process the elements are associative and stateless. For example, given a stream of numbers for which we want to find the sum, we can write:

    int sum = numbers.stream().reduce(0, (x,y) -> x+y);

or:

    int sum = numbers.stream().reduce(0, Integer::sum);

These reduction operations can run safely in parallel with almost no modification:

    int sum = numbers.parallelStream().reduce(0, Integer::sum);

Reduction parallellizes well because the implementation can operate on subsets of the data in parallel, and then combine the intermediate results to get the final correct answer. (Even if the language had a "parallel for-each" construct, the mutative accumulation approach would still required the developer to provide thread-safe updates to the shared accumulating variable sum, and the required synchronization would then likely eliminate any performance gain from parallelism.) Using reduce() instead removes all of the burden of parallelizing the reduction operation, and the library can provide an efficient parallel implementation with no additional synchronization required.
The "widgets" examples shown earlier shows how reduction combines with other operations to replace for loops with bulk operations. If widgets is a collection of Widget objects, which have a getWeight method, we can find the heaviest widget with:

     OptionalInt heaviest = widgets.parallelStream()
                                   .mapToInt(Widget::getWeight)
                                   .max();

In its more general form, a reduce operation on elements of type <T> yielding a result of type <U> requires three parameters:

 <U> U reduce(U identity,
              BiFunction<U, ? super T, U> accumulator,
              BinaryOperator<U> combiner);

Here, the identity element is both an initial seed value for the reduction and a default result if there are no input elements. The accumulator function takes a partial result and the next element, and produces a new partial result. The combiner function combines two partial results to produce a new partial result. (The combiner is necessary in parallel reductions, where the input is partitioned, a partial accumulation computed for each partition, and then the partial results are combined to produce a final result.)
More formally, the identity value must be an identity for the combiner function. This means that for all u, combiner.apply(identity, u) is equal to u. Additionally, the combiner function must be associative and must be compatible with the accumulator function: for all u and t, combiner.apply(u, accumulator.apply(identity, t)) must be equals() to accumulator.apply(u, t).
The three-argument form is a generalization of the two-argument form, incorporating a mapping step into the accumulation step. We could re-cast the simple sum-of-weights example using the more general form as follows:

     int sumOfWeights = widgets.stream()
                               .reduce(0,
                                       (sum, b) -> sum + b.getWeight())
                                       Integer::sum);

though the explicit map-reduce form is more readable and therefore should usually be preferred. The generalized form is provided for cases where significant work can be optimized away by combining mapping and reducing into a single function.
Mutable reduction
A mutable reduction operation accumulates input elements into a mutable result container, such as a Collection or StringBuilder, as it processes the elements in the stream.
If we wanted to take a stream of strings and concatenate them into a single long string, we could achieve this with ordinary reduction:

     String concatenated = strings.reduce("", String::concat)

We would get the desired result, and it would even work in parallel. However, we might not be happy about the performance! Such an implementation would do a great deal of string copying, and the run time would be O(n^2) in the number of characters. A more performant approach would be to accumulate the results into a StringBuilder, which is a mutable container for accumulating strings. We can use the same technique to parallelize mutable reduction as we do with ordinary reduction.
The mutable reduction operation is called collect(), as it collects together the desired results into a result container such as a Collection. A collect operation requires three functions: a supplier function to construct new instances of the result container, an accumulator function to incorporate an input element into a result container, and a combining function to merge the contents of one result container into another. The form of this is very similar to the general form of ordinary reduction:

 <R> R collect(Supplier<R> supplier,
               BiConsumer<R, ? super T> accumulator,
               BiConsumer<R, R> combiner);

As with reduce(), a benefit of expressing collect in this abstract way is that it is directly amenable to parallelization: we can accumulate partial results in parallel and then combine them, so long as the accumulation and combining functions satisfy the appropriate requirements. For example, to collect the String representations of the elements in a stream into an ArrayList, we could write the obvious sequential for-each form:

     ArrayList<String> strings = new ArrayList<>();
     for (T element : stream) {
         strings.add(element.toString());
     }

Or we could use a parallelizable collect form:

     ArrayList<String> strings = stream.collect(() -> new ArrayList<>(),
                                                (c, e) -> c.add(e.toString()),
                                                (c1, c2) -> c1.addAll(c2));

or, pulling the mapping operation out of the accumulator function, we could express it more succinctly as:

     List<String> strings = stream.map(Object::toString)
                                  .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

Here, our supplier is just the ArrayList constructor, the accumulator adds the stringified element to an ArrayList, and the combiner simply uses addAll to copy the strings from one container into the other.
The three aspects of collect -- supplier, accumulator, and combiner -- are tightly coupled. We can use the abstraction of a Collector to capture all three aspects. The above example for collecting strings into a List can be rewritten using a standard Collector as:

     List<String> strings = stream.map(Object::toString)
                                  .collect(Collectors.toList());

Packaging mutable reductions into a Collector has another advantage: composability. The class Collectors contains a number of predefined factories for collectors, including combinators that transform one collector into another. For example, suppose we have a collector that computes the sum of the salaries of a stream of employees, as follows:

     Collector<Employee, ?, Integer> summingSalaries
         = Collectors.summingInt(Employee::getSalary);

(The ? for the second type parameter merely indicates that we don't care about the intermediate representation used by this collector.) If we wanted to create a collector to tabulate the sum of salaries by department, we could reuse summingSalaries using groupingBy:

     Map<Department, Integer> salariesByDept
         = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
                                                            summingSalaries));

As with the regular reduction operation, collect() operations can only be parallelized if appropriate conditions are met. For any partially accumulated result, combining it with an empty result container must produce an equivalent result. That is, for a partially accumulated result p that is the result of any series of accumulator and combiner invocations, p must be equivalent to combiner.apply(p, supplier.get()).
Further, however the computation is split, it must produce an equivalent result. For any input elements t1 and t2, the results r1 and r2 in the computation below must be equivalent:

     A a1 = supplier.get();
     accumulator.accept(a1, t1);
     accumulator.accept(a1, t2);
     R r1 = finisher.apply(a1);  // result without splitting
    
     A a2 = supplier.get();
     accumulator.accept(a2, t1);
     A a3 = supplier.get();
     accumulator.accept(a3, t2);
     R r2 = finisher.apply(combiner.apply(a2, a3));  // result with splitting

Here, equivalence generally means according to Object.equals(Object). but in some cases equivalence may be relaxed to account for differences in order.
Reduction, concurrency, and ordering
With some complex reduction operations, for example a collect() that produces a Map, such as:

     Map<Buyer, List<Transaction>> salesByBuyer
         = txns.parallelStream()
               .collect(Collectors.groupingBy(Transaction::getBuyer));

it may actually be counterproductive to perform the operation in parallel. This is because the combining step (merging one Map into another by key) can be expensive for some Map implementations.
Suppose, however, that the result container used in this reduction was a concurrently modifiable collection -- such as a java.util.concurrent.ConcurrentHashMap. In that case, the parallel invocations of the accumulator could actually deposit their results concurrently into the same shared result container, eliminating the need for the combiner to merge distinct result containers. This potentially provides a boost to the parallel execution performance. We call this a concurrent reduction.
A Collector that supports concurrent reduction is marked with the Collector.Characteristics.CONCURRENT characteristic. However, a concurrent collection also has a downside. If multiple threads are depositing results concurrently into a shared container, the order in which results are deposited is non-deterministic. Consequently, a concurrent reduction is only possible if ordering is not important for the stream being processed. The Stream.collect(Collector) implementation will only perform a concurrent reduction if
The stream is parallel;
The collector has the Collector.Characteristics.CONCURRENT characteristic, and;
Either the stream is unordered, or the collector has the Collector.Characteristics.UNORDERED characteristic.
You can ensure the stream is unordered by using the BaseStream.unordered() method. For example:

     Map<Buyer, List<Transaction>> salesByBuyer
         = txns.parallelStream()
               .unordered()
               .collect(groupingByConcurrent(Transaction::getBuyer));

(where Collectors.groupingByConcurrent is the concurrent equivalent of groupingBy).
Note that if it is important that the elements for a given key appear in the order they appear in the source, then we cannot use a concurrent reduction, as ordering is one of the casualties of concurrent insertion. We would then be constrained to implement either a sequential reduction or a merge-based parallel reduction.
Associativity
An operator or function op is associative if the following holds:

     (a op b) op c == a op (b op c)

The importance of this to parallel evaluation can be seen if we expand this to four terms:

     a op b op c op d == (a op b) op (c op d)

So we can evaluate (a op b) in parallel with (c op d), and then invoke op on the results.
Examples of associative operations include numeric addition, min, and max, and string concatenation.
Low-level stream construction
So far, all the stream examples have used methods like java.util.Collection.stream() or java.util.Arrays.stream(Object[]) to obtain a stream. How are those stream-bearing methods implemented?
The class StreamSupport has a number of low-level methods for creating a stream, all using some form of a java.util.Spliterator. A spliterator is the parallel analogue of an java.util.Iterator; it describes a (possibly infinite) collection of elements, with support for sequentially advancing, bulk traversal, and splitting off some portion of the input into another spliterator which can be processed in parallel. At the lowest level, all streams are driven by a spliterator.
There are a number of implementation choices in implementing a spliterator, nearly all of which are tradeoffs between simplicity of implementation and runtime performance of streams using that spliterator. The simplest, but least performant, way to create a spliterator is to create one from an iterator using java.util.Spliterators.spliteratorUnknownSize(java.util.Iterator, int). While such a spliterator will work, it will likely offer poor parallel performance, since we have lost sizing information (how big is the underlying data set), as well as being constrained to a simplistic splitting algorithm.
A higher-quality spliterator will provide balanced and known-size splits, accurate sizing information, and a number of other characteristics of the spliterator or data that can be used by implementations to optimize execution.
Spliterators for mutable data sources have an additional challenge; timing of binding to the data, since the data could change between the time the spliterator is created and the time the stream pipeline is executed. Ideally, a spliterator for a stream would report a characteristic of IMMUTABLE or CONCURRENT; if not it should be late-binding. If a source cannot directly supply a recommended spliterator, it may indirectly supply a spliterator using a Supplier, and construct a stream via the Supplier-accepting versions of stream(). The spliterator is obtained from the supplier only after the terminal operation of the stream pipeline commences.
These requirements significantly reduce the scope of potential interference between mutations of the stream source and execution of stream pipelines. Streams based on spliterators with the desired characteristics, or those using the Supplier-based factory forms, are immune to modifications of the data source prior to commencement of the terminal operation (provided the behavioral parameters to the stream operations meet the required criteria for non-interference and statelessness). See Non-Interference for more details.
Since:
1.8