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

// still normal ints
test("egcd", function() {
  ok( arrays_equal(egcd(54,24), [6,1,-2]), "extended gcd works correctly");
});

test("egcd", function() {
  ok( arrays_equal(egcd(54,21), [3,2,-5]), "extended gcd works correctly");
});

test("mod_inverse", function() {
  ok( mod_inverse(8, 19) === 12, "mod_inverse works");
});

// Now uses big ints
test("encrypt", function() {
  ok( arrays_equal(encrypt([[143,0], [7, 0]], [42, 0]), [81,0]), "encrypt");
});

test("decrypt", function() {
  ok( arrays_equal(decrypt([[143, 0], [103,0]], [81, 0]), [42,0]), "decrypt");
});

test("generate_key", function() {
  ok( arrays_equal(generate_key("11", "13" ,"7"), [[[143 ,0],[7, 0]],
          [[143, 0],[103, 0]]]), "generate_key");
});
