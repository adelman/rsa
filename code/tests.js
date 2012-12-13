/*
 * QUnit tests for RSA algorithm experiments
 * http://qunitjs.com
 */

test("gcd", function() {
  ok( gcd(54, 24) === 6, "gcd(54, 24) returns 6");
});

test("egcd", function() {
  deepEqual(egcd(54,24), [6,1,-2], "extended gcd works correctly");
});

test("egcd", function() {
  deepEqual(egcd(54,21), [3,2,-5], "extended gcd works correctly");
});

test("mod_inverse", function() {
 ok( mod_inverse(8, 19) === 12, "mod_inverse works");
});

// Now uses big ints
// No idea why a global variable is being introduced
test("crypt", function() {
  deepEqual(crypt([[143,0], [7, 0]], [42, 0]), [81,0], "encrypt");
});

test("crypt", function() {
  deepEqual(crypt([[143, 0], [103,0]], [81, 0]), [42,0], "decrypt");
});

test("generate_key", function() {
  deepEqual(generate_key("11", "13" ,"7"), [[[143 ,0],[7, 0]],
          [[143, 0],[103, 0]]], "generate_key");
});
