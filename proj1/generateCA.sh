#!/bin/bash

KEY="key.pem";
CERT="cert.pem";
CONFIG="config";
NAMES="cn=\"David Norrestam(fys11dno)/Hannes Fornell(cek11hfo)/Lars Gustafsson(ada10lgu)/Hanna Andreason()\"";
KSNAME="clientkeystore"
TSNAME="clienttruststore"

#Generate Certificate
function generateCA509 {
	openssl req -x509 -newkey rsa:1024 -keyout $KEY -out $CERT -config $CONFIG	
	openssl x509 -in $CERT -noout -text;
}

# Generate trustStore and import CA-certificate
function trustStore {
	rm $TSNAME
	keytool -import -v -trustcacerts -file $CERT -keystore $TSNAME -keypass password
}

function eit060_all {
	generateCA509;
	trustStore;
	keyStore;
}

function keyStore {
	rm $KSNAME
	keytool -genkeypair -keystore $KSNAME -storepass password -dname $NAMES
}
