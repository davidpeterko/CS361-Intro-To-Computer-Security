UTEID: acc2634; dpk326;
FIRSTNAME: Andres; David;
LASTNAME: Correa; Ko;
CSACCOUNT: andresc; davidko; 
EMAIL: andres.correa@utexas.edu; davidpeterko@gmail.com;

Good description of your code:
A secure system using subjects and objects with independant security levels 
based on the BLP/*-Property/Strong Tranquility models. Each subject and object 
contains their own values that must keep.Our code contains many
classes, each with a purpose to run the program. The bulk of the code is in 
SecureSystem.java that then calls functions from ReferenceMonitor.java using 
objects from InstructionObject.java, BadInstruction.java, SystemObject.java, 
and SystemSubject.java. Are there security flaws in this design? Only addresses 
confidentiality. How would we fix this? Making the system with more integrity 
and non-repudiaton. We could also implement an authorization type access (unique user and PINS?).

You need to tell us how much you've finished:
(1) We finished all requirements and passed all tests and implemented the appropriate logic and BLP/*-property/Strong tranquility.

Test cases

destroy Hal
create Lyle HObj
write Hal HObj 4
read Hal LObj
1 stuff maybe let's hope not
completely wrong

Output

Reading from file: ourinput

Bad Instruction
The current state is:
   LObj has value: 0
   HObj has value: 0
   Lyle has recently read: 0
   Hal has recently read: 0

Bad Instruction
The current state is:
   LObj has value: 0
   HObj has value: 0
   Lyle has recently read: 0
   Hal has recently read: 0

Hal writes value 4 to HObj
The current state is:
   LObj has value: 0
   HObj has value: 4
   Lyle has recently read: 0
   Hal has recently read: 0

Hal reads LObj
The current state is:
   LObj has value: 0
   HObj has value: 4
   Lyle has recently read: 0
   Hal has recently read: 0

Bad Instruction
The current state is:
   LObj has value: 0
   HObj has value: 4
   Lyle has recently read: 0
   Hal has recently read: 0

Bad Instruction
The current state is:
   LObj has value: 0
   HObj has value: 4
   Lyle has recently read: 0
   Hal has recently read: 0

