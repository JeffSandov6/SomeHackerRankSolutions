Challenge level: Medium

You will be given a list of integers, arr, and a single integer k. You must create an array of length k from elements of a such that its unfairness is minimized. Call that array subarr. Unfairness of an array is calculated as

max(subarr) - min(subarr)

Where:

max denotes the largest integer in subarr
min denotes the smallest integer in subarr
As an example, consider the array [1, 4, 7, 2] with k = 2. Pick any two elements, test [4, 7] unfairness = max(4, 7) - min(4, 7) = 7 - 4 = 3 Testing for all pairs, the solution [1, 2] provides the minimum unfairness.

Note: Integers in may not be unique.

Challenge link: https://www.hackerrank.com/challenges/angry-children/problem

