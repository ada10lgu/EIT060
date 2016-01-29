#!/bin/bash

#Generate Certificate
function generateCA509 {
	echo "hej"
	openssl req -x509 -newkey rsa:1024 -keyout key.pem -out cert.pem	

}

# Generate trustStore and import CA-certificate
function trustStore {
	keytool -import -v -trustcacerts -file cert.pem -keystore clienttruststore -keypass password
}
