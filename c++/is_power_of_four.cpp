/*
Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

Example:
Given num = 16, return true. Given num = 5, return false.
*/

class Solution {
public:
	/**
	 * If a number is a power of 4, it's must be a power of 2.
	 * So it has to satisfy num & (num-1) == 0.
	 * Besides that, the only 1 bit position must be in
	 * the odd position if we count the right most as the first position,
	 * which means, it can only be 2^2, 2^4, 2^6, ..., 2^30. While
	 * 0x55555555 is 0b01010101010101010101010101010101, we just need
	 * to check whether num & 0x55555555 is zero in addition.
	 */
    bool isPowerOfFour(int num) {
        return (!(num & (num-1)) && (num & 0x55555555));
    }
};