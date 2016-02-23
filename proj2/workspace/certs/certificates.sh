#!/bin/bash

CONFIG="config";
CERT="ca.crt";
KEY="key.crt";
SERVERTS="servertruststore";
SERVERKS="serverkeystore";
CA="CA";


# Create CA Certificate, $1 = password
generate_ca() {
	password = $1
	echo "Generating new CA certificate";
	openssl req -x509 -newkey rsa:1024 -keyout $KEY -out $CERT -config $CONFIG
}

# $1: alias, $2: password, $3 dname
generate_client() {
	# Local variables
	keystore_name="$1"_key
	truststore_name="$1"_trust
	csr_name="$1".csr
	signed_csr_name="$1".cer
	client_alias=$1
	client_password=$2
	d_name=$3

	# Keystore
	create_keystore $keystore_name $client_password $client_alias $d_name
	generate_csr $keystore_name $client_alias $csr_name
	sign_csr $csr_name $KEY $signed_csr_name
	import_cer $signed_csr_name $keystore_name $client_alias

	# Truststore
	create_truststore $CERT $truststore_name $client_password
}

# $1: alias, $2: password, $3 dname
generate_server() {
	server_alias=$1
	keystore_name="$1"_key 
	truststore_name="$1"_trust
	csr_name="$1".csr
	signed_csr_name="$1".cer
	server_password=$2
	d_name=$3

	echo "Server keystore"
	create_keystore $keystore_name $server_password $server_alias $d_name
	generate_csr $keystore_name $server_alias $csr_name
	sign_csr $csr_name $KEY $signed_csr_name
	import_cer $signed_csr_name $keystore_name $server_alias

	echo "Server truststore"
	create_truststore $CERT $truststore_name $server_password 
}


# $1:Keystore name, $2: password, $3: alias $4 dname
create_keystore() {
		echo "keytool -genkeypair -alias $3 -keystore $1 -storepass $2 -dname $4"
		echo "$4"
		if [ -e $1 ]; then
		echo "Duplicate keystore found. Removing old keystore"
		rm $1
	fi

	keytool -genkeypair -alias $3 -keystore $1 -storepass $2 -dname $4

	echo "Importing CA to keystore"
	keytool -import -trustcacerts -v -file $CERT -keystore $1 -keypass $2 -alias $CA
}

# $1:Name of keystore $2: alias $3: name of csr file to create
generate_csr() {
	keytool -certreq -keystore $1 -keyalg rsa:1024 -alias $2 -file $3 
}

# $1:CSR Name $2: CA Name Key $3: Signed file name
sign_csr() {
	openssl x509 -req -in $1 -CA $CERT -CAkey $2 -CAcreateserial -out $3
}

# $1: Cer to import $2: Keystore name $3: alias
import_cer() {
	#keytool -import -trustcacerts -keystore $CKSNAME -file $CERT -alias $CAROOT
	keytool -import -file $1 -keystore $2 -alias $3
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
	keytool -import -trustcacerts -v -file $1 -keystore $2 -keypass $3 -alias $CA
}

# Generates 4 new clients with trust and keystores
#  - Stores the CA in both stores
#  - Stores a signed public/private key-pair in the keystore
generate_clients() {
	generate_client david password "cn=David o=Patient ou=Cardiology"
	generate_client hanna password "cn=Hanna o=Doctor ou=Cardiology"
	generate_client lars password "cn=Lars o=Nurse ou=Cardiology"
	generate_client hannes passwordge "cn=Hannes o=Government ou=Cardiology"
}