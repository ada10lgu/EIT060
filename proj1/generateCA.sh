#!/bin/bash

KEY="key.crt";
CERT="ca.crt";
CONFIG="config";
NAMES="cn=\"DavidNorrestam(fys11dno)/HannesFornell(cek11hfo)/LarsGustafsson(ada10lgu)/HannaAndreason(bte12han)\"";
CKSNAME="clientkeystore";
CTSNAME="clienttruststore";
CSRNAME="client.csr";

SKSNAME="serverkeystore";
STSNAME="servertruststore";
SSRNAME="server.csr"
SNAMES="cn=\"MyServer\"";

ALIAS="EIT060";
CAROOT="theCARoot";


#Generate Certificate
function generateCA509 {
	openssl req -x509 -newkey rsa:1024 -keyout $KEY -out $CERT -config $CONFIG	
	openssl x509 -in $CERT -noout -text;
}

# Generate trustStore and import CA-certificate
function trustStore {
	rm $CTSNAME;
	keytool -import -trustcacerts -v -file $CERT -keystore $CTSNAME -keypass password -alias $CERT
}
function serverTrustStore {
	rm $STSNAME;
	keytool -import -trustcacerts -v -file $CERT -keystore $STSNAME -keypass password -alias $CERT
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
	echo "CLIENT DONE!";

	echo "SERVER SHIT!";
	echo "TRUST";
	serverTrustStore;
	echo "KEY";
	serverKeyStore;
	echo "CSR"
	serverGenerateCSR;
	echo "SIGN"
	serverSign;
	echo "IMPORT";
	serverImport;
	echo "Lets hope this works!";

	echo "PRINT";
	printStuff;
}


function keyStore {
	rm $CKSNAME
	keytool -genkeypair -keystore $CKSNAME -storepass password -dname $NAMES -alias client
}

function serverKeyStore {
	rm $SKSNAME
	keytool -genkeypair -keystore $SKSNAME -storepass password -dname $SNAMES -alias server
}

##############################

function generateCSR {
	keytool -certreq -keystore $CKSNAME -keyalg rsa:1024 -alias client -file $CSRNAME 
}


function serverGenerateCSR {
	keytool -certreq -keystore $SKSNAME -keyalg rsa:1024 -alias server -file $SSRNAME 
}

##############################

function sign {
	openssl x509 -req -in $CSRNAME -CA $CERT -CAkey $KEY -CAcreateserial -out client.cer
}


function serverSign {
	openssl x509 -req -in $SSRNAME -CA $CERT -CAkey $KEY -CAcreateserial -out server.cer
}

##############################

function import {
	keytool -import -trustcacerts -keystore $CKSNAME -file $CERT -alias $CAROOT
	keytool -import -file client.cer -keystore $CKSNAME -alias client
}


function serverImport {
	keytool -import -trustcacerts -keystore $SKSNAME -file $CERT -alias $CAROOT
	keytool -import -file server.cer -keystore $SKSNAME -alias server
}

##############################


function printStuff {
	keytool -list -v -keystore $CKSNAME
	keytool -list -v -keystore $SKSNAME
}
