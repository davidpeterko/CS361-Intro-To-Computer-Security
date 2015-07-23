UTEID: dpk326; acc2634
FIRSTNAME: David; Andres
LASTNAME: Ko; Correa;
CSACCOUNT: davidko; andresc;
EMAIL: davidpeterko@gmail.com; andres.correa@utexas.edu;

[Program 4]
[Description]
We tried to implement AES 256 however the given pdf file given for the aes key expansion did not explain things very for us. It was very convoluted and we couldnt find any help online that could steer us in the right direction of getting the steps of the process the expansion keys. We got up to key 3 right, then the rest did not calculate correctly. So instead, we completed the AES 128 - fully functional. We also used Dr Young's code for mixColumn and implemented into our code.

[Finish]
Mostly completed. Expansion key does not work for 256, but works for 128. 

[Test Cases]
[Input of test 1]
[command line]
java AES e Key plaintext
java AES d key plaintext.enc

plaintext
FD AE 5F 3A
11 22 33 44
44 33 22 11
B1 B2 B3 B4

[Output of test 1]
plaintext.enc
26 BF 6A 3C 
8D 59 28 23 
A3 BE CD 43 
B3 83 9F A5 

plaintext.enc.dec
FD AE 5F 3A 
11 22 33 44 
44 33 22 11 
B1 B2 B3 B4 
   
[Input of test 2]
[command line]
java AES e Key plaintext
java AES d Key plaintext.enc

plaintext
AB BC CD DE
BA EC DD AB
11 44 22 33
A1 B2 C3 D4

[Output of test 2]

plaintext.enc
09 3E 99 B2 
1A 00 7A 1A 
4D D9 52 1F 
E3 5D AF F1 

plaintext.enc.dec
AB BC CD DE 
BA EC DD AB 
11 44 22 33 
A1 B2 C3 D4 

[Input of test 3]
[command line]
java AES e Key plaintext
java AES d Key plaintext.enc

plaintext
AE E1 3B C2
CC F2 1D B3
22 44 33 11
DE ED CD DC

[Output of test 3]

plaintext.enc
86 3D 77 B8 
A1 DB 8F F0 
0B 2F C3 FE 
45 6D EE A1 

plaintext.enc.dec
AE E1 3B C2 
CC F2 1D B3 
22 44 33 11 
DE ED CD DC 

[Input of test 4]
[command line]
java AES e Key plaintext
java AES d Key plaintext.enc

plaintext
3D 3B 3A 3C
2A 2C 2B 2D
E1 E2 E3 E4
D9 D8 D7 D6

[Output of test 4]

plaintext.enc
9E C1 93 C1 
9A E6 10 3C 
45 7C E4 35 
9A 17 63 2A 

plaintext.enc.dec
3D 3B 3A 3C 
2A 2C 2B 2D 
E1 E2 E3 E4 
D9 D8 D7 D6