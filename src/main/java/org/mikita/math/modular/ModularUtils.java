package org.mikita.math.modular;

public class ModularUtils {

    public long modularExponentiation(long base, long exponent, long mod) {
        long result = 1;

        base %= mod;

        if(base == 0) {
            return base;
        }

        while(exponent > 0) {
            if((exponent & 1) == 1) {
                result = (result * base) % mod;
            }

            base = (base * base) % mod;
            exponent >>>= 1;
        }

        return result;
    }
}
