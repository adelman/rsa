/*
 * QUnit tests for RSA algorithm experiments
 * http://qunitjs.com
 */

test( "Example test", function() {
  ok( 1 == "1", "Passed!" );
});

test("gcd", function() {
  ok( gcd(54, 24) === 6, "gcd(54, 24) returns 6");
});

// For array equality
function arrays_equal(a,b) { return !(a<b || b<a); }

// To be implemented
test("egcd", function() {
  ok( arrays_equal(egcd(54,24), [6,1,-2]), "extended gcd works correctly");
});

test("egcd", function() {
  ok( arrays_equal(egcd(54,21), [3,2,-5]), "extended gcd works correctly");
});

test("mod_inverse", function() {
  ok( mod_inverse(8, 19) === 12, "mod_inverse works");
});

test("encrypt", function() {
  ok( encrypt([143, 7], 42) === 81, "encrypt");
});

test("decrypt", function() {
  ok( decrypt([143, 7], 81) === 16, "decrypt");
});

test("generate_key", function() {
  ok( arrays_equal(generate_key(11,13,7), [[143,7],[143,103]]), "generate_key");
});
