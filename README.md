# Multi-platform Socket connections

http://akinaru.github.io/socket-multiplatform

<i>Last update 23/05/2015</i>

This project will features socket connection implementation on multiple platforms and frameworks :

<table>
    <tr>
        <td colspan="6"></td>
        <td colspan="2"><b>Java</b></td>
        <td colspan="2"><b>Browser</b></td>
        <td colspan="2"><b>C++ QT4</b></td>
    </tr>
    <tr>
        <td colspan="2" rowspan="4">HTTP Server socket</td>
        <td colspan="2" rowspan="2">blocking</td>
        <td colspan="2">no ssl</td>
        <td colspan="2"><img src="https://raw.github.com/akinaru/socket-multiplatform/master/OK.png"/></td>
        <td colspan="2" rowspan="8"></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2">ssl</td>
        <td colspan="2"><img src="https://raw.github.com/akinaru/socket-multiplatform/master/OK.png"/></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2" rowspan="2">non-blocking</td>
        <td colspan="2">no ssl</td>
        <td colspan="2"></td>
        <td colspan="2"><img src="https://raw.github.com/akinaru/socket-multiplatform/master/OK.png"/></td>
    </tr>
    <tr>
        <td colspan="2">ssl</td>
        <td colspan="2"></td>
        <td colspan="2"><img src="https://raw.github.com/akinaru/socket-multiplatform/master/OK.png"/></td>
    </tr>
    <tr>
        <td colspan="2" rowspan="4">Server websocket</td>
        <td colspan="2" rowspan="2">blocking</td>
        <td colspan="2">no ssl</td>
        <td colspan="2"><img src="https://raw.github.com/akinaru/socket-multiplatform/master/OK.png"/></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2">ssl</td>
        <td colspan="2"><img src="https://raw.github.com/akinaru/socket-multiplatform/master/OK.png"/></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2" rowspan="2">non-blocking</td>
        <td colspan="2">no ssl</td>
        <td colspan="2"></td>
        <td colspan="2"><img src="https://raw.github.com/akinaru/socket-multiplatform/master/OK.png"/></td>
    </tr>
    <tr>
        <td colspan="2">ssl</td>
        <td colspan="2"></td>
        <td colspan="2"><img src="https://raw.github.com/akinaru/socket-multiplatform/master/OK.png"/></td>
    </tr>
    <tr>
        <td colspan="4" rowspan="2">HTTP Client socket</td>
        <td colspan="2">no ssl</td>
        <td colspan="2"><img src="https://raw.github.com/akinaru/socket-multiplatform/master/OK.png"/></td>
        <td colspan="2" rowspan="2"><img src="https://raw.github.com/akinaru/socket-multiplatform/master/OK.png"/></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2">ssl</td>
        <td colspan="2"><img src="https://raw.github.com/akinaru/socket-multiplatform/master/OK.png"/></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="4" rowspan="2">Client websocket</td>
        <td colspan="2">no ssl</td>
        <td colspan="2"><img src="https://raw.github.com/akinaru/socket-multiplatform/master/OK.png"/></td>
        <td colspan="2"><img src="https://raw.github.com/akinaru/socket-multiplatform/master/OK.png"/></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2">ssl</td>
        <td colspan="2"><img src="https://raw.github.com/akinaru/socket-multiplatform/master/OK.png"/></td>
        <td colspan="2"><img src="https://raw.github.com/akinaru/socket-multiplatform/master/OK.png"/></td>
        <td colspan="2"></td>
    </tr>
     <tr>
        <td colspan="2" rowspan="4">UDP server</td>
        <td colspan="2" rowspan="2">blocking</td>
        <td colspan="2">no ssl</td>
        <td colspan="2"><img src="https://raw.github.com/akinaru/socket-multiplatform/master/OK.png"/></td>
        <td colspan="2"></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2">ssl</td>
        <td colspan="2"></td>
        <td colspan="2"></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2" rowspan="2">non-blocking</td>
        <td colspan="2">no ssl</td>
        <td colspan="2"></td>
        <td colspan="2"></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2">ssl</td>
        <td colspan="2"></td>
        <td colspan="2"></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="4" rowspan="2">UDP Client</td>
        <td colspan="2">no ssl</td>
        <td colspan="2"><img src="https://raw.github.com/akinaru/socket-multiplatform/master/OK.png"/></td>
        <td colspan="2"></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2">ssl</td>
        <td colspan="2"></td>
        <td colspan="2"></td>
        <td colspan="2"></td>
    </tr>
        <tr>
        <td colspan="6"></td>
        <td colspan="2"><b>Java</b></td>
        <td colspan="2"><b>Browser</b></td>
        <td colspan="2"><b>C++ QT4</b></td>
    </tr>
</table>

<h3>Description of Java Websocket Server/Client</h3>

[JAVA Websocket server sub-project](http://akinaru.github.io/websocket-java/)

<hr/>

<h3>Description of C++ QT4 Non-blocking Websocket Server</h3>

[C++ QT4 Websocket server sub-project](http://akinaru.github.io/websocket-non-blocking-cpp/)

<hr/>

<h3>Description of Java HTTP Server Socket</h3>

[JAVA HTTP server socket sub-project](server/server-socket/blocking/java/)

<hr/>

<h3>Description of Java HTTP Client Socket</h3>

[JAVA HTTP client socket sub-project](client/socket-client/java/)

<hr/>

<h3>Description of Java UDP Server/Client Socket</h3>

[JAVA UDP client/server socket sub-project](udp/java/)

<hr/>

<h3>Description of C++ QT4 Non Blocking HTTP Server socket</h3>

[C++ Non Blocking HTTP Server socket](server/server-socket/non-blocking/cpp/)

<hr/>

<b>TroubleShooting</b>

<b>Keystore : public and private server certificates</b>

* To convert cert and key certs to p12 : 

``openssl pkcs12 -export -out server.p12 -inkey server.key -in server.crt``

Thus, you will have : ``String KEYSTORE_DEFAULT_TYPE = "PKCS12"``

* To convert your p12 (containing public and private cert) to jks : 

You have to know your alias (name matching your cert entry), if you dont have it retrieve it with : ``keytool -v -list -storetype pkcs12 -keystore server.p12``

``keytool -importkeystore -srckeystore server.p12 -srcstoretype PKCS12 -deststoretype JKS -destkeystore server.jks``

Thus, you will have : ``String KEYSTORE_DEFAULT_TYPE = "JKS"``

<b>Trustore : should contain only CA certificates</b>

convert ca cert to jks : 

```keytool -import -alias ca -file ca.crt -keystore cacerts.jks -storepass 123456```

Thus, you will have : ``String TRUSTORE_DEFAULT_TYPE = "JKS"``

<hr/>

<i>Bad certificate | Unknown CA errors</i>

This could mean you didn't import your not-trusted-CA certificate into your browser.

<i>The remote host closed the connection</i>

Usually happen when your browser closed the connection before the end of SSL handshake. If you already added your CA to your browser dont worry.
Both Chrome and Firefox need to MANUALLY add the certificate (in a popup) so putting it in parameter menu don't change anything.

Just load your URL with "https" : https://127.0.0.1:8443 . Browser will prompt you to accept the certificates and it will probably solve your connection error.

<i>CKR_DOMAIN_PARAMS_INVALID error using openjdk</i>

With openjdk-6-jdk and openjdk-7-jdk, I encountered java.security bug described in https://bugs.launchpad.net/ubuntu/+source/openjdk-7/+bug/1006776 triggering a ``CKR_DOMAIN_PARAMS_INVALID`` exception error. Solution was to edit java.security parameters in /etc/java-7-openjdk/security/java.security 

I replaced that : 
```
security.provider.9=sun.security.smartcardio.SunPCSC
security.provider.10=sun.security.pkcs11.SunPKCS11 ${java.home}/lib/security/nss.cfg
```

with that : 
```
#security.provider.9=sun.security.smartcardio.SunPCSC
security.provider.9=sun.security.ec.SunEC
#security.provider.10=sun.security.pkcs11.SunPKCS11 ${java.home}/lib/security/nss.cfg
```

<b>Browser tested</b>

This has been tested on following browser : 
* Chrome
* Chromium
* Firefox

<hr/>

<b>Debugging SSL connection error</b>

I recommmend using openssl command line tool to debug ssl connection : 

``openssl s_client -connect 127.0.0.1:8443``

You can also use vm argument debugging java ssl implementation : ``-Djavax.net.debug=ssl``

<hr/>

<b>Server-Client key/cert generation</b>

Certs are in libwesocket-test/certs folder, you will find server,client and ca cert build with easy-rsa :

https://github.com/OpenVPN/easy-rsa

With last release of easy-rsa, you can build your own key with the following : 

* ``./build-ca`` : generate a new CA for you
* ``./build-server-full myServer`` : will build for you public cert and private cert signed with CA for server
* ``./build-client-full myClient`` : will build for you public cert and private cert signed with CA for client

<hr/>

<b>Java</b>

* Projects are JRE 1.7 compliant
* You can build with ant => build.xml
* Development on Eclipse 

<b>C++ QT</b>

* Project are Qt4 compliant
* You can build with qmake
* Development on QtCreator
