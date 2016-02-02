#!/bin/bash

KEY="key.crt";
CERT="ca.crt";
CONFIG="config";
NAMES="cn=\"DavidNorrestam(fys11dno)/HannesFornell(cek11hfo)/LarsGustafsson(ada10lgu)/HannaAndreason(bte12han)\"";
KSNAME="clientkeystore";
TSNAME="clienttruststore";
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
	rm $TSNAME
	keytool -import -trustcacerts -v -file $CERT -keystore $TSNAME -keypass password -alias $CERT
}

function eit060_all {
	generateCA509;
	trustStore;
	keyStore;
	generateCSR;
	sign;
	import;
	printStuff;
}


function keyStore {
	rm $KSNAME
	keytool -genkeypair -keystore $KSNAME -storepass password -dname $NAMES -alias client
}

function generateCSR {
	keytool -certreq -keystore $KSNAME -keyalg rsa:1024 -alias client -file $CSRNAME 
}

function sign {
	openssl x509 -req -in $CSRNAME -CA $CERT -CAkey $KEY -CAcreateserial -out client.cer
}

function import {
	keytool -import -trustcacerts -keystore $KSNAME -file $CERT -alias $CAROOT
	keytool -import -file client.cer -keystore $KSNAME -alias client
}

function printStuff {
	keytool -list -v -keystore $KSNAME
}
