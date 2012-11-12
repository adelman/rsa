/*
 * A few helper functions for the web implementation.
 *
 * Matt Adelman
 */

/* On page load attach event handlers to different buttons */
$('#rsa-tools button#clear').click(function(){
      this.form.reset()
});

/*
 * This is the function the first button calls. It checks that all of the fields
 * are properly filled in, or uses defaults. It also checks if the primes are 
 * disticnt, etc.
 */
function gen(frm) {
    if (frm.exponent.value === "") {
        e = frm.exponent.placeholder;
    }
    else {
        e = frm.exponent.value;
    }
    if (frm.prime1.value === "") {
        p = frm.prime1.placeholder;
    }
    else {
        p = frm.prime1.value;
    }
    if (frm.prime2.value === "") {
        q = frm.prime2.placeholder;
    }
    else {
        q = frm.prime2.value;
    }

    key = generate_key(p, q, e);
    // Primes aren't distinct
    if (key === 0) {
        frm.prime1.value = "";
        frm.prime2.value = "";
    }
    // Mod inverse doesn't exist.
    else if (key === 1) {
        frm.exponent.value = "";
    }
    // Exponent is bigger than phi(n).
    else if (key === 2) {
        frm.exponent.value = "";
    }
    else {
        // Computation and display.
        n = bigInt2str(key[0][0], 10);
        d = bigInt2str(key[1][1], 10);
        frm.mod1.value = n;
        frm.mod2.value = n;
        frm.pub.value  = e;
        frm.priv.value = d;
    }
}

/*
 * If the user uses the second button, all they need to do is input a bit
 * length, and this function uses the prime generation of big-int to get prime
 * numbers for them.
 */
function doEverything(frm) {
    if (frm.bits.value === "") {
        b = parseInt(frm.bits.placeholder, 10);
    }
    else {
        b = parseInt(frm.bits.value, 10);
    }
    // Impossible to have a cipher with less than or equal to 2 bits.
    if (b <= 2) {
        alert("Bit length must be greater than 2");
        frm.bits.value = "";
    }
    // Create primes.
    else {
        p = randTruePrime(b);
        q = randTruePrime(b);
    }
    // Make sure they are distinct.
    while (equals(p,q)) {
        q = randTruePrime(b);
    }

    var es = "";
    // Small values.
    if (b <= 7) {
        es = "17"
    }
    // Middle value.
    else if (b === 8) {
        es = "257";
    }
    // The rest.
    else {
        es = "65537";
    }
    ps = bigInt2str(p, 10);
    qs = bigInt2str(q, 10);
    key = generate_key(ps, qs, es);
    
    if (key === 0) {
        frm.prime1.value = "";
        frm.prime2.value = "";
    }
    // Should never be the case, but just in case.
    else if (key === 1) {
        frm.exponent.value = "";
    }

    // Display of values.
    else {
        n = bigInt2str(key[0][0], 10);
        d = bigInt2str(key[1][1], 10);
        frm.exponent.value = es;
        frm.prime1.value = ps;
        frm.prime2.value = qs;
        frm.mod1.value = n;
        frm.mod2.value = n;
        frm.pub.value  = es;
        frm.priv.value = d;
    }
}


/*
 * Calls crypt function with public key and message.
 */
function encrypt (frm) {
    // Getting values.
    ns = frm.mod1.value;
    es = frm.pub.value;
    ms = frm.message.value;

    n = str2bigInt(ns, 10, 0);
    e = str2bigInt(es, 10, 0);
    m = str2bigInt(ms, 10, 0);

    // Cipher
    c = crypt([n,e], m);

    // Incorrect message size
    if (c === 0) {
        frm.message.value = "";
    }
    else {
        frm.cipher.value = bigInt2str(c, 10);
    }
}

/*
 * Very similar to encrypt.
 */
function decrypt (frm) {
    ns = frm.mod1.value;
    ds = frm.priv.value;
    cs = frm.cipher.value;

    n = str2bigInt(ns, 10, 0);
    d = str2bigInt(ds, 10, 0);
    c = str2bigInt(cs, 10, 0);

    m = crypt([n,d], c);

    if (m === 0) {
        frm.cipher.value = "";
    }
    else {
        frm.message.value = bigInt2str(m, 10);
    }
}
