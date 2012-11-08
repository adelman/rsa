/*
 * A few helper functions for the web implementation.
 *
 * Matt Adelman
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
    if (key === 0) {
        frm.prime1.value = "";
        frm.prime2.value = "";
    }
    else if (key === 1) {
        frm.exponent.value = "";
    }
    else {
        n = bigInt2str(key[0][0], 10);
        d = bigInt2str(key[1][1], 10);
        frm.mod1.value = n;
        frm.mod2.value = n;
        frm.pub.value  = e;
        frm.priv.value = d;
    }
}

function encrypt (frm) {
    ns = frm.mod1.value;
    es = frm.pub.value;
    ms = frm.message.value;

    n = str2bigInt(ns, 10, 0);
    e = str2bigInt(es, 10, 0);
    m = str2bigInt(ms, 10, 0);

    c = crypt([n,e], m);

    if (c === 0) {
        frm.message.value = "";
    }
    else {
        frm.cipher.value = bigInt2str(c, 10);
    }
}

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
