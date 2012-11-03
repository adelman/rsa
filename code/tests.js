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


// To be implemented
test("egcd", function() {
  ok( egcd(54, 24) === 6, "extended gcd works correctly");
});

test("mod_inverse", function() {
  ok( egcd(54, 24) === 6, "mod_inverse works");
});
test("encrypt", function() {
  ok( egcd(54, 24) === 6, "encrypt");
});
test("decrypt", function() {
  ok( egcd(54, 24) === 6, "decrypt");
});
test("generate_key", function() {
  ok( egcd(54, 24) === 6, "generate_key");
});
