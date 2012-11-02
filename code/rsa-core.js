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

    n = p * q;
    phi_n = (p - 1) * (q - 1); // Calculate Euler's totient function

    d = mod_inverse(e, phi_n);

    return {
        public_key: [ n, e ],
        private_key: [ n, d]
    }
}

function encrypt(pub_key, message) {
    cipher = Math.pow(message, pub_key[1]) % pub_key[0];
    return cipher
}

function decrypt(priv_key, cipher) {
    message = Math.pow(cipher, priv_key[1]) % priv_key[0];
    return message
}

function gcd(a,b) { 
    if (a === 0) {
        return b
    }
    if (b === 0) {
        return a
    }
    q = Math.floor(a/b);
    r = a - (b * q)
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
        data = egcd(b % a, a);
        return [data[0], data[2] - (Math.floor(b/a) * data[1]), data[1]]
    }
}

function mod_inverse(a, m) {
    data = egcd(a, m)
        if (data[0] != 1) {
            throw {name: 'ArgumentError',
                   message: 'modular inverse does not exist'
            };
        }
        else {
            return ((data[1] % m) + m) % m
        }
}
