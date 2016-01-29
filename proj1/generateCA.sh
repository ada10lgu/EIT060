#!/bin/bash

#Generate Certificate
openssl req -x509 -newkey rsa:1024 -keyout key.pem -out cert.pem

