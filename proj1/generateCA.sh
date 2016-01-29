#!/bin/bash

KEY="key.pem";
CERT="cert.pem";

#Generate Certificate
function generateCA509 {
	openssl req -x509 -newkey rsa:1024 -keyout $KEY -out $CERT	

}

# Generate trustStore and import CA-certificate
function trustStore {
	keytool -import -v -trustcacerts -file $CERT -keystore clienttruststore -keypass password
}

function eit060_all {
	generateCA509;
	trustStore;
}
