#!/bin/bash

KEY="key.pem";
CERT="cert.pem";
CONFIG="config";
NAMES="cn=\"DavidNorrestam(fys11dno)/HannesFornell(cek11hfo)/LarsGustafsson(ada10lgu)/HannaAndreason(bte12han)\"";
KSNAME="clientkeystore";
TSNAME="clienttruststore";

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
