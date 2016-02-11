#!/bin/bash

CONFIG="config";
CERT="ca.crt";
KEY="key.crt";
SERVERTS="servertruststore";
SERVERKS="serverkeystore";
CLIENTTS="clienttruststore";
CLIENTKS="clientkeystore";
SERVERCN="CA";


# Create CA Certificate, $1 = password
generate_ca() {
	password = $1
	echo "Generating new CA certificate";
	openssl req -x509 -newkey rsa:1024 -keyout $KEY -out $CERT -config $CONFIG
}

# $1:Keystore name, $2: password, $3: alias $4 dname
create_keystore() {
		echo "keytool -genkeypair -alias $3 -keystore $1 -storepass $2 -dname $4"
		echo "$4"
		if [ -e $1 ]; then
		echo "Duplicate keystore found. Removing old truststore"
		rm $1
	fi

	keytool -genkeypair -alias $3 -keystore $1 -storepass $2 -dname $4

	echo "Importing CA to keystore"
	keytool -import -alias CA -file $CERT -keystore $1
}


# Create server keystore and truststore
# $1 = Certificate name, $2 = truststore name, $3 = password
create_truststore() {
	if [ ! -e $1 ]; then
		echo "No CA certificate found, run generate_ca first"
		exit
	fi
	if [ -e $2 ]; then
		echo "Duplicate truststore found. Removing old truststore"
		rm $2
	fi

	echo "Creating new truststore and importing CA"
	keytool -import -trustcacerts -v -file $1 -keystore $2 -keypass $3 -alias $1
}

generate_clients() {
	create_keystore DavidKeystore password David "cn=David o=Patient ou=Cardiology"
	create_keystore HannaKeystore password Hanna "cn=Hanna o=Doctor ou=Cardiology"
	create_keystore HannesKeystore password Hannes "cn=Hannes o=Nurse ou=Cardiology"
	create_keystore LarsKeystore password Lars "cn=Lars o=Government ou=Cardiology"
}