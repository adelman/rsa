"""
RSA Implementation in python.

Note that this only works to transmit integers so far.

Matt Adelman
Evan Carmi
Adam Forbes
"""

import random

# key generation
def key_gen(p, q):
    n = p * q
    phi_n = (p - 1) * (q - 1)
    # Some relatively prime number, for large prime 0x10001
    e = find_rel_prime(phi_n)
    d = modinv(e, phi_n)
    
    public_key = (n, e)
    private_key = (n, d)

    return(public_key, private_key)

# encrypt a message using the public key. message must be an integer less than
# the modulus n of the public key (should not be a problem with large primes
def encrypt(pub_key, message):
    (n, e) = pub_key
    cipher = pow(message, e) % n
    return int(cipher)

# Decrypt a message that was encrypted using a public key sent by the person
# with this private key
def decrypt(priv_key, cipher):
    (n, d) = priv_key
    message = pow(cipher, d) % n
    return int(message)

# Helper functions, these probably should be hidden
# Finds a number relatively prime to n. NOT EFFICIENT!
def find_rel_prime(n):
    e = random.randint(2, n - 1)
    if gcd(n, e) == 1:
        return e
    else:
        return find_rel_prime(n)

# NUMBER THEORY STUFF

# greatest common divisor
def gcd(a,b):
    if a == 0:
        return b
    if b == 0:
        return a
    q = a/b
    r = a - b*q
    if r == 0:
        return b
    else:
        return gcd(b,r)

# extended gcd. Allows us to find modular multiplicative inverse
def egcd(a, b):
    if a == 0:
        return (b, 0, 1)
    else:
        g, y, x = egcd(b % a, a)
        return (g, x - (b // a) * y, y)

# Finds modular multiplicative inverse if one exists. Note that since in 
# key_gen e, and phi_n are relatively prime, this will always exist.
def modinv(a, m):
    g, x, y = egcd(a, m)
    if g != 1:
        raise Exception('modular inverse does not exist')
    else:
        return x % m

