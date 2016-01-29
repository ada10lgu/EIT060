#!/bin/bash

KEY="key.pem";
CERT="cert.pem";
CONFIG="config";
NAMES="cn=David(fys11dno)/Hannes(cek11hfo)/Lars(ada10lgu)/Hanna()";
CLIENTKEYSTORENAME="clientkeystore"

#Generate Certificate
function generateCA509 {
	openssl req -x509 -newkey rsa:1024 -keyout $KEY -out $CERT -config $CONFIG	
	openssl x509 -in $CERT -noout -text;
}

# Generate trustStore and import CA-certificate
function trustStore {
	keytool -import -v -trustcacerts -file $CERT -keystore clienttruststore -keypass password
}

function eit060_all {
	generateCA509;
	trustStore;
	keyStore;
}

function keyStore {
	rm $CLIENTKEYSTORENAME
	keytool -genkeypair -keystore $CLIENTKEYSTORENAME -storepass password -dname $NAMES
}
