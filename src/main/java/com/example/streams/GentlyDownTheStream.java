package com.example.streams;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Enhanced coding kata on the Stream API with exception handling, generics, and advanced concepts.
 * All methods include proper validation and can be completed with a single return statement plus validation.
 */
public class GentlyDownTheStream {

    protected List<String> fruits;
    protected List<String> veggies;
    protected List<Integer> integerValues;

    public GentlyDownTheStream() {
        fruits = Arrays.asList("Apple", "Orange", "Banana", "Pear", "Peach", "Tomato");
        veggies = Arrays.asList("Corn", "Potato", "Carrot", "Pea", "Tomato");
        integerValues = new Random().ints(0, 1001)
                .boxed()
                .limit(1000)
                .collect(Collectors.toList());
    }

    /**
     * Example method showing proper exception handling and validation
     * Returns a sorted list of fruits with comprehensive error checking
     */
    public List<String> sortedFruits() throws InvalidDataException {
        try {
            validateCollection(fruits, "Fruits collection");

            return fruits.stream()
                    .filter(Objects::nonNull) // Handle potential null elements
                    .sorted()
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new InvalidDataException("Failed to sort fruits: " + e.getMessage());
        }
    }

    /**
     * Enhanced version with custom predicate and exception handling
     */
    public List<String> sortedFruitsException() throws InvalidDataException {
        return sortedFruitsWithFilter(fruit -> !fruit.startsWith("A"));
    }

    // TODO - return a list with the first 2 elements of a sorted list of fruits
    // Add proper validation and exception handling
    public List<String> sortedFruitsFirstTwo() throws InvalidDataException {
        try {
            validateCollection(fruits, "Fruits collection");

            List<String> result = fruits.stream()
                    .filter(Objects::nonNull)
                    .sorted()
                    .limit(2)
                    .collect(Collectors.toList());

            if (result.isEmpty()) {
                throw new InvalidDataException("No valid fruits found after filtering nulls");
            }

            return result;
        } catch (EmptyCollectionException e) {
            throw new InvalidDataException("Cannot retrieve first two fruits: " + e.getMessage(), e);
        } catch (InvalidDataException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidDataException("Failed to get first two sorted fruits: " + e.getMessage(), e);
        }
    }

    /**
     * Returns a comma-separated String of sorted fruits.
     * Null fruit entries are filtered out gracefully.
     */
    public String commaSeparatedListOfFruits() throws InvalidDataException {
        try {
            validateCollection(fruits, "Fruits collection");

            String result = fruits.stream()
                    .filter(Objects::nonNull)
                    .sorted()
                    .collect(Collectors.joining(", "));

            if (result.isEmpty()) {
                throw new InvalidDataException("No valid fruits available to join");
            }

            return result;
        } catch (EmptyCollectionException e) {
            throw new InvalidDataException("Cannot create comma-separated list: " + e.getMessage(), e);
        } catch (InvalidDataException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidDataException("Failed to create comma-separated fruits: " + e.getMessage(), e);
        }
    }

    /**
     * Returns a list of veggies sorted in reverse (descending) order.
     * Uses Comparator.reverseOrder() with null-safe filtering.
     */
    public List<String> reverseSortedVeggies() throws InvalidDataException {
        try {
            validateCollection(veggies, "Veggies collection");

            return veggies.stream()
                    .filter(Objects::nonNull)
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());
        } catch (EmptyCollectionException e) {
            throw new InvalidDataException("Cannot reverse-sort veggies: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new InvalidDataException("Failed to reverse-sort veggies: " + e.getMessage(), e);
        }
    }

    /**
     * Returns a list of veggies sorted in reverse order, all in upper case.
     * Chains map() for transformation after sorting.
     */
    public List<String> reverseSortedVeggiesInUpperCase() throws InvalidDataException {
        try {
            validateCollection(veggies, "Veggies collection");

            return veggies.stream()
                    .filter(Objects::nonNull)
                    .sorted(Comparator.reverseOrder())
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());
        } catch (EmptyCollectionException e) {
            throw new InvalidDataException("Cannot reverse-sort veggies in upper case: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new InvalidDataException("Failed to reverse-sort veggies in upper case: " + e.getMessage(), e);
        }
    }

    /**
     * Returns a list of the top 10 values (highest) in the list of random integers.
     * Handles cases where the list has fewer than 10 elements by returning all available.
     */
    public List<Integer> topTen() throws InvalidDataException {
        try {
            validateCollection(integerValues, "Integer values collection");

            return integerValues.stream()
                    .filter(Objects::nonNull)
                    .sorted(Comparator.reverseOrder())
                    .limit(10)
                    .collect(Collectors.toList());
        } catch (EmptyCollectionException e) {
            throw new InvalidDataException("Cannot retrieve top ten: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new InvalidDataException("Failed to get top ten values: " + e.getMessage(), e);
        }
    }

    /**
     * Returns a list of the top 10 unique values in the list of random integers.
     * Uses distinct() to eliminate duplicates before limiting.
     */
    public List<Integer> topTenUnique() throws InvalidDataException {
        try {
            validateCollection(integerValues, "Integer values collection");

            List<Integer> result = integerValues.stream()
                    .filter(Objects::nonNull)
                    .distinct()
                    .sorted(Comparator.reverseOrder())
                    .limit(10)
                    .collect(Collectors.toList());

            if (result.isEmpty()) {
                throw new InvalidDataException("No valid integer values found after filtering");
            }

            return result;
        } catch (EmptyCollectionException e) {
            throw new InvalidDataException("Cannot retrieve top ten unique: " + e.getMessage(), e);
        } catch (InvalidDataException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidDataException("Failed to get top ten unique values: " + e.getMessage(), e);
        }
    }

    /**
     * Returns a list of the top 10 unique odd values from the random integer list.
     * Combines odd-number filtering, distinct, descending sort, and limiting.
     */
    public List<Integer> topTenUniqueOdd() throws InvalidDataException {
        try {
            validateCollection(integerValues, "Integer values collection");

            List<Integer> result = integerValues.stream()
                    .filter(Objects::nonNull)
                    .filter(n -> n % 2 != 0)   // keep only odd numbers
                    .distinct()
                    .sorted(Comparator.reverseOrder())
                    .limit(10)
                    .collect(Collectors.toList());

            if (result.isEmpty()) {
                throw new InvalidDataException("No unique odd values found in the integer collection");
            }

            return result;
        } catch (EmptyCollectionException e) {
            throw new InvalidDataException("Cannot retrieve top ten unique odd: " + e.getMessage(), e);
        } catch (InvalidDataException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidDataException("Failed to get top ten unique odd values: " + e.getMessage(), e);
        }
    }

    /**
     * Returns the average of all random numbers as a Double.
     * Handles OptionalDouble safely and guards against an empty/null collection.
     */
    public Double average() throws InvalidDataException {
        try {
            validateCollection(integerValues, "Integer values collection");

            OptionalDouble avg = safeAverage(integerValues);

            if (avg.isEmpty()) {
                throw new InvalidDataException(
                        "Average could not be computed — no valid (non-null) integers in the collection");
            }

            return avg.getAsDouble();
        } catch (EmptyCollectionException e) {
            throw new InvalidDataException("Cannot compute average: " + e.getMessage(), e);
        } catch (InvalidDataException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidDataException("Failed to compute average: " + e.getMessage(), e);
        }
    }

    // ─── Private helpers ───────────────────────────────────────────────────────

    /**
     * Generic null/empty guard for any Collection.
     *
     * @param collection     the collection to validate
     * @param collectionName human-readable name used in error messages
     * @throws EmptyCollectionException if the collection is empty
     * @throws IllegalArgumentException if the collection itself is null
     */
    private <T> void validateCollection(Collection<T> collection, String collectionName)
            throws EmptyCollectionException {
        if (collection == null) {
            throw new IllegalArgumentException(collectionName + " cannot be null");
        }
        if (collection.isEmpty()) {
            throw new EmptyCollectionException(collectionName + " cannot be empty");
        }
    }

    /**
     * Generic helper that filters, sorts by a given Comparator, and returns a List.
     *
     * @param collection the source collection
     * @param filter     predicate applied after null-filtering
     * @param comparator sort order to apply
     * @return filtered and sorted list
     */
    private <T> List<T> sortedWithFilter(Collection<T> collection,
                                         Predicate<T> filter,
                                         Comparator<T> comparator) throws InvalidDataException {
        try {
            validateCollection(collection, "Input collection");

            return collection.stream()
                    .filter(Objects::nonNull)
                    .filter(filter)
                    .sorted(comparator)
                    .collect(Collectors.toList());
        } catch (EmptyCollectionException e) {
            throw new InvalidDataException("Failed to sort and filter collection: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new InvalidDataException("Failed to sort and filter collection: " + e.getMessage(), e);
        }
    }

    /** Convenience overload for String fruit lists. */
    private List<String> sortedFruitsWithFilter(Predicate<String> filter) throws InvalidDataException {
        return sortedWithFilter(fruits, filter, String::compareTo);
    }

    /**
     * Null-safe average over a collection of Integers.
     * Returns an empty OptionalDouble when every element was null.
     */
    private OptionalDouble safeAverage(Collection<Integer> numbers) {
        return numbers.stream()
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .average();
    }
}
