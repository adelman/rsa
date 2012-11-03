# A ruby shell script to scrape for https certificates and decode them.
# @author - Evan Carmi

# a csv file with a number and host on each line. Ex: 1,google.com.
# Often useful: http://s3.amazonaws.com/alexa-static/top-1m.csv.zip

HOST_FILE = 'top-1m.csv'
PORT = 443

# Directory for downloaded certificates
CERT_DIR = 'certs'

# Directory for decoded certificates
DECODED_DIR = 'decoded'

`mkdir -p #{CERT_DIR}/`
`mkdir -p #{DECODED_DIR}/`

def cmd_runner(cmd)
  out = `#{cmd}`
  unless $?.success?
    puts "\n\nERROR\nFailed while running #{cmd} with error: #{out}"
  end
  $?.success?
end

File.open(HOST_FILE).each_line do |line|
  num, domain = line.strip.split(',')

  # first check if server responds to https request
  cmd = "curl -I https://#{domain} --connect-timeout 1 -m 2"
  next unless cmd_runner(cmd)

  # download certificate
  cmd1 = "echo -n | openssl s_client -connect #{domain}:#{PORT} | sed -ne '/-BEGIN CERTIFICATE-/,/-END CERTIFICATE-/p' > #{CERT_DIR}/#{domain}.cert"
  next unless cmd_runner(cmd1)

  # unpack certificate
  cmd2 = "openssl x509 -in #{CERT_DIR}/#{domain}.cert -text -noout > #{DECODED_DIR}/#{domain}.cert.decoded"
  next unless cmd_runner(cmd2)

  cmd3 = "echo '#{domain}' >> downloaded_hosts.txt"
  next unless cmd_runner(cmd3)



end

