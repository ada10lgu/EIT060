#!/bin/bash

KEY="key.crt";
CERT="ca.crt";
CONFIG="config";
NAMES="cn=\"DavidNorrestam(fys11dno)/HannesFornell(cek11hfo)/LarsGustafsson(ada10lgu)/HannaAndreason(bte12han)\"";
CKSNAME="clientkeystore";
CTSNAME="clienttruststore";
CSRNAME="client.csr";
ALIAS="EIT060";
CAROOT="theCARoot";


#Generate Certificate
function generateCA509 {
	openssl req -x509 -newkey rsa:1024 -keyout $KEY -out $CERT -config $CONFIG	
	openssl x509 -in $CERT -noout -text;
}

# Generate trustStore and import CA-certificate
function trustStore {
	rm $CTSNAME
	keytool -import -trustcacerts -v -file $CERT -keystore $CTSNAME -keypass password -alias $CERT
}

function eit060_all {
	echo "GENERATE CA";
	generateCA509;
	echo "CLIENT STUFF!";
	echo "TRUST";
	trustStore;
	echo "KEY";
	keyStore;
	echo "CSR";
	generateCSR;
	echo "SIGN";
	sign;
	echo "IMPORT";
	import;
	echo "PRINT";
	printStuff;
}


function keyStore {
	rm $CKSNAME
	keytool -genkeypair -keystore $CKSNAME -storepass password -dname $NAMES -alias client
}

function generateCSR {
	keytool -certreq -keystore $CKSNAME -keyalg rsa:1024 -alias client -file $CSRNAME 
}

function sign {
	openssl x509 -req -in $CSRNAME -CA $CERT -CAkey $KEY -CAcreateserial -out client.cer
}

function import {
	keytool -import -trustcacerts -keystore $CKSNAME -file $CERT -alias $CAROOT
	keytool -import -file client.cer -keystore $CKSNAME -alias client
}

function printStuff {
	keytool -list -v -keystore $CKSNAME
}
