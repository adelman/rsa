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

function mod_inverse();
