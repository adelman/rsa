// RSA experiments

/*
 * function: generate_key - generate and return a public key and a private key  
 *
 * @param p - a prime used to create key, distinct from q
 * @param q - a prime used to create key, distinct from p
 *
 */

function generate_key(p, q, e) {
    if (p === q){
        throw {
            name: 'ArgumentError',
            message: 'p, q must be unique'
        };
    }
    var p = str2bigInt(p, 10, 0);
    var q = str2bigInt(q, 10, 0);
    var e = str2bigInt(e, 10, 0);

    var n = mult(p, q);

    // Calculate Euler's totient function
    var phi_n = mult(addInt(p, -1), addInt(q, -1)); 
    var d = inverseMod(e, phi_n);

    // Changed return type for easier equality testing
    return [
          [ n, e ], // Public Key
          [ n, d ]  // Private Key
    ]
}

function encrypt(pub_key, message) {
    var cipher = powMod(message, pub_key[1], pub_key[0]);
    return cipher
}

function decrypt(priv_key, cipher) {
    var message = powMod(cipher, priv_key[1], priv_key[0]);
    return message
}

/* No longer necessary with use of big-int library. Perhaps the next step is
 * to create our own?
 */
function gcd(a,b) { 
    if (a === 0) {
        return b
    }
    if (b === 0) {
        return a
    }
    var q = Math.floor(a/b);
    var r = a - (b * q)
    if (r === 0) {
        return b
    }
    else {
        return gcd(b,r)
    }
}

function egcd(a,b) {
    if (a === 0) {
        return [b,0,1]
    }
    else {
        var data = egcd(b % a, a);
        return [data[0], data[2] - (Math.floor(b/a) * data[1]), data[1]]
    }
}

function mod_inverse(a, m) {
    var data = egcd(a, m)
        if (data[0] != 1) {
            throw {name: 'ArgumentError',
                   message: 'modular inverse does not exist'
            };
        }
        else {
            return ((data[1] % m) + m) % m
        }
}
