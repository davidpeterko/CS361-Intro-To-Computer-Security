UTEID: dpk326; acc2634; 
FIRSTNAME: David; Andres; 
LASTNAME: Ko; Correa;
CSACCOUNT: davidko; andresc;
EMAIL: davidpeterko@gmail.com; andres.correa@utexas.edu;

[Program 3]
[Description]
HuffHelp.java, QueuePriority.java, TreeNode.java were from Andres Correa's assignment from CS314. HuffHelp.java and QueuePriority.java written by Andres with minor changes to fit this assignment. TreeNode.java was taken from Mike Scott from his source and was modified to be able to return a String instead of an int. Our Encoder.java takes in the input from the testfile and a size k, were we create and output 2 encodings from the frequencies we were given by using the same idea that was used in Huffman Coding. Then we take the 2 encodings and we decode the values to get back the originally encoding. 

[Finish]
We have completed the assignment with correct output files for the input testfile. Both encoding files and decrypt files match up correctly with given test input.

[Test Cases]
[Input of test 1]
[command line] java Encoder frequenciesFile 4

3
2
1

[Output of test 1]
The first-order encoding is the following:
A 0
B 11
C 10


The second-order encoding is the following:
AA 01
AB 110
AC 1011
BA 111
BB 100
BC 0011
CA 000
CB 1010
CC 0010

[Input of test 2]
[command line] java Encoder frequenciesFile 6
4
8
6
2
5

[Output of test 2]
The first-order encoding is the following:
A 101
B 11
C 01
D 100
E 00


The second-order encoding is the following:
AA 01010
AB 0001
AC 10111
AD 1110111
AE 10100
BA 0100
BB 001
BC 1100
BD 01011
BE 1000
CA 11100
CB 1101
CC 0111
CD 101101
CE 11111
DA 011010
DB 01100
DC 111010
DD 1110110
DE 011011
EA 10101
EB 1001
EC 0000
ED 101100
EE 11110

[Input of test 3]
[command line] java Encoder frequenciesFile 8
14
10
6
4

[Output of test 3]
The first-order encoding is the following:
A 0
B 10
C 111
D 110


The second-order encoding is the following:
AA 111
AB 011
AC 1011
AD 11011
BA 100
BB 000
BC 0011
BD 10100
CA 1100
CB 0100
CC 01011
CD 110101
DA 0010
DB 10101
DC 01010
DD 110100


[Input of test 4]
[command line] java Encoder frequenciesFile 10
12
14
10
16
3

[Output of test 4]
The first-order encoding is the following:
A 00
B 10
C 011
D 11
E 010


The second-order encoding is the following:
AA 0010
AB 0101
AC 10111
AD 1000
AE 000101
BA 0110
BB 1010
BC 11111
BD 1100
BE 011100
CA 11110
CB 0000
CC 10110
CD 0011
CE 0001111
DA 1001
DB 1101
DC 0100
DD 1110
DE 011110
EA 000110
EB 011101
EC 000100
ED 011111
EE 0001110
