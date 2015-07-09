UTEID: dpk326;
FIRSTNAME: David;
LASTNAME: Ko;
CSACCOUNT: davidko;
EMAIL: davidpeterko@gmail.com

[Program 5]
[Description]
In this assignment, we implement a portion of Crack's functionality and attempt to guess one or more passwords. We input the password file and we try to crack as many passwords as possible; comparing them to encrypted versions. It uses a salt that is stored with a user's login information. Salt is used so that anyone guessing passwords has to guess per user. We have to encrypt the passwords ourselves. We used a dictoinary that we compare to. My algorithm consisted of saving each user with their salt and password. Then i iterateed through the dictionary and using jcrypt to encrypt all of those words with each individual users salt to generate a map of each user with an associated arraylist (this list contains the jcrypt passwords). I then compared the ones that were jcrypted with the user.password, if it was a match, i would output that original dictionary word used by the jcrypt. 

[Finish]
I implemented the full project in it entirety, but the output is a little behind. I can output 1 correct cracked password from passwd1 and 2 cracked passwords from passwd2. But the program compiles and runs completely.

[Test Cases]
[Input of test 1]
https://www.cs.utexas.edu/~byoung/cs361/passwd1

[Output of test 1]
I can crack 1 cases.
List of cracked:
amazing

I can not crack 19 cases.
List of uncracked:
atbWfKL4etk4U
&i4KZ5wmac566
<qt0.GlIrXuKs
feohQuHOnMKGE
{ztmy9azKzZgU
%xPBzF/TclHvg
khnVjlJN3Lyh2
e4DBHapAtnjGk
7we09tBSVT76o
3dIJJXzELzcRE
jsQGVbJ.IiEvE
w@EbBlXGLTue6
8joIBJaXJvTd2
nxsr/UAKmKnvo
gwjT8yTnSCVQo
KelgNcBOZdHmA
5WW698tSZJE9I
!cI6tOT/9D2r6
T8jwuve9rQBo.

[Input of test 2]
https://www.cs.utexas.edu/~byoung/cs361/passwd2

[Output of test 2]
I can crack 2 cases.
List of cracked:
tremors
Saxon

I can not crack 18 cases.
List of uncracked:
(bt0xiAwCf7nA
<qf.L9z1/tZkA
fe8PnYhq6WoOQ
{zQOjvJcHMs7w
%xqFrM5RVA6t6
kh/1uC5W6nDKc
e4EyEMhNzYLJU
7wKTWsgNJj6ac
3d1OgBYfvEtfg
js5ctN1leUABo
w@FxBZv.d0y/U
8jGWbU0xbKz06
nxr9OOqvZjbGs
gw9oXmw1L08RM
KenK1CTDGr/7k
5Wb2Uqxhoyqfg
!cSaQELm/EBV.
T8U5jQaDVv/o2