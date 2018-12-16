Challenge level: Medium

Huffman coding assigns variable length codewords to fixed length input characters based on their frequencies. More frequent characters are assigned shorter codewords and less frequent characters are assigned longer codewords. All edges along the path to a character contain a code digit. If they are on the left side of the tree, they will be a 0 (zero). If on the right, they'll be a 1 (one). Only the leaves will contain a letter and its frequency count. All other nodes will contain a null instead of a character, and the count of the frequency of all of it and its descendant characters.

You are given pointer to the root of the Huffman tree and a binary coded string to decode. You need to print the decoded string. An example input is:

s="1001011", as well as the root node to a tree. Traverse the tree to find the leaves and their associated characters, then decode the string given.


Challenge link: https://www.hackerrank.com/challenges/tree-huffman-decoding/problem
