# MNP5-Data-Compression

In **MNP5** (Microcom Network Protocol 5) compression, character frequency statistics are used to compress arbitrary binary messages. Codewords from 4 to 10 bits long are used to represent 8-bit characters. The available codewords are assigned dynamically to use the shortest codewords for the most frequently occuring message characters.
There is a fixed selection of codewords ranging from 4 to 10 bits in length. For most codewords, the first 3 bits (highlighted) indicate how many bits remain in the codeword. Exceptions are the first two 4-bit words (beginning in 000), and the last two words where an additional bit is added to allow a 'reset' codeword (number 256). While this approach limits the number of codewords available with a given length, it ensures that codewords can be extracted from a binary stream by first examining three bits to determine how many more bits to take.

For example, consider this MNP5 compressed binary message:

0100010110111000110001100011...

These 28 bits represent five MNP5 codewords, as:

**010**00 **101**10111 **000**1 **100**0110 **001**1...

This corresponds to exactly five 8-bit characters (40 bits) in the original uncompressed message.
