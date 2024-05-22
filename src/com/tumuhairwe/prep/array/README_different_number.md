Getting a Different Number

A super naive solution is to randomly pick numbers from 0 to MAX_INT and check if they are in arr. However, this is not a good approach. First, the worst case scenario doesn’t even have an upper bound. We could be very unlucky and keep withdrawing numbers that are in the array. Also, If the size of arr is very large, we’ll have to look up the random numbers in that array many times.

Another simple solution is to keep a record of the minimum/maximum while iterating over all numbers in arr and then produce numbers smaller/larger than all numbers in arr. However, this won’t work if the minimum number is 0 and the maximum number is MAX_INT. Another iteration tactic is returning the sum of all numbers, but it won’t work, for instance, for the array [0, 3]. Also, returning the multiplication of all numbers in arr wouldn’t be correct if 0 is one of the numbers in arr.

There are many possible solutions to this problem. We’ll review some of them here.

The brute force solution

A simple solution would be to create a copy arr, sort that copy in an ascending order, iterate over its values, and then return the first index for which the condition i != arrSorted[i] is met, where arrSorted is the sorted copy of arr. This approach works since all the values in arr are nonnegative integers.

Pseudocode:

function getDifferentNumber(arr):
n = arr.length

    # since we’re not allowed to modify arr, we create a copy of it
    arrSorted = new Array(arr)

    # sort the duplicate array in an ascending order
    arrSorted.sort()

    for i from 0 to n - 1:
        if (arrSorted[i] != i):
            return i  # i isn’t in arr, hence we can return it

    # we got here since every number from 0 to n-1 is in arr.
    # By definition then, n isn’t in arr. Otherwise, the size of arr
    # would have been n+1 and not n.
    return n
Time Complexity: duplicating the array is O(N), sorting it is O(N⋅log(N)), and then traversing the array is another O(N). The total time complexity is, therefore, O(N⋅log(N)).

Space Complexity: we used a single auxiliary array, arrSorted, whose size is the same as arr. The space complexity is O(N).

The efficient solution

The reason we needed to sort arrSorted in the brute force solution above was because doing so allowed us to cap the number of lookups to O(N). However, if all that we’re doing is simply checking whether certain values exist, then there is a better data structure for this purposes, which obviates the need for sorting. That data structure is the Set. A Set is similar to a Hash Table (a.k.a Map or Hash Map). Both support lookups and insertions in O(1) time. The difference is that while a hash table returns a value that is a mapped to a key, a set returns a boolean: true if a looked up element exists in the set and false otherwise.

The new algorithm is practically identical to the brute force one, but instead of using a duplicate array and sorting it, we’ll use a set.

Pseudocode:

function getDifferentNumber(arr):
n = arr.length

    set = new Set() # the Set interface is language dependent
    for i from 0 to n-1:
        set.insert([arr[i]]) # build the set

    for i from 0 to n - 1:
        if (set.find(i) == false):
            return i

    # we got here since every number from 0 to n-1 is in arr.
    # By definition then, n isn’t in arr. Otherwise, the size of arr
    # would have been n+1 and not n.
    return n
Time Complexity: with sorting gone, the time complexity consists of building the set, which is O(N) and the lookup loop, which is also O(N). The reason the time complexity of the lookup loop is linear is because find()‘s time complexity is constant, i.e. O(1). The total time complexity is therefore O(N).

Space Complexity: we used a single auxiliary set whose size is the same as arr. Hence, the space complexity is O(N).

The “in-place” solution

If we are allowed to modify the input array arr, we can bring down the space complexity from O(N) to O(1), while keeping the time complexity at O(N).

As before, this algorithm is going to be very similar to the brute force one. Since we are allowed to modify arr, the fact that its values are all unique nonnegative integers allows us to use a special kind of sorting algorithm whose time complexity is linear and not the standard O(N⋅log(N)) we typically associate with efficient sorting.

The high-level idea is to push every number to its corresponding index in the array. The original number in the target index is “kicked out”, so we continue to find its target index using the same approach, until the target index is out of range.

Pseudocode:

function getDifferentNumber(arr):
n = arr.length
temp = 0

    # put each number in its corresponding index, kicking out
    # the original number, until the target index is out of range.
    for i from 0 to n-1:
        temp = arr[i]
        while (temp < n AND arr[temp] != temp):
            swap(temp, arr[temp])

    for i from 0 to n - 1:
        if (arr[i] != i):
            return i  # i isn’t in arr, hence we can return it

    # we got here since every number from 0 to n-1 is in arr.
    # By definition then, n isn’t in arr. Otherwise, the size of arr
    # would have been n+1 and not n.
    return n
Time Complexity: at first glance, one might think that due to the two nested loops (a while loop inside a for loop) that we use to sort the array, the time complexity is O(N^2). However, this is incorrect. The actual time complexity of the two nested loops is linear. The reason is that every number is at most moved once. For those already in their target indices, the while loop will end immediately since the condition arr[temp] != temp isn’t met. In the second part of the code we have another loop whose time complexity is linear. The total time complexity is therefore O(N).

Space Complexity: we use only constant space. Hence the space complexity is O(1).